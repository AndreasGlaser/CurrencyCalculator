package de.andreas.assignment.controller;

import de.andreas.assignment.dto.Currency;
import de.andreas.assignment.dto.CurrencyShortName;
import de.andreas.assignment.dto.ExchangeRate;
import de.andreas.assignment.repository.CurrencyRepository;
import de.andreas.assignment.repository.ExchangeRateRepository;
import de.andreas.assignment.service.CurrencyService;
import de.andreas.assignment.service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/exchange")
public class ExchangeController {

    final ExchangeRateService exchangeRateService;
    final
	CurrencyService currencyService;

    public ExchangeController(ExchangeRateService exchangeRateService, CurrencyService currencyService)
    {
        this.exchangeRateService = exchangeRateService;
		this.currencyService = currencyService;
	}

    @GetMapping
    public ResponseEntity<List<ExchangeRate>> getCurrencies()
    {
        return new ResponseEntity<>(exchangeRateService.getExchangeRates(), HttpStatus.OK);
    }

    @GetMapping("/{fromCurrency}/{toCurrency}")
    public ResponseEntity<Double> currencyRate(@PathVariable CurrencyShortName fromCurrency,
                                                   @PathVariable CurrencyShortName toCurrency)
    {
        var response = exchangeRateService.getRateFromTo(fromCurrency, toCurrency)
                .map(exchangeRate -> new ResponseEntity<>(exchangeRate.getRate(), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        currencyService.increaseCountFrom(fromCurrency);
		currencyService.increaseCountTo(fromCurrency);
        return response;
    }

    @GetMapping("/{fromCurrency}/{toCurrency}/{amount}")
    public ResponseEntity<Double> exchangeCurrency(@PathVariable CurrencyShortName fromCurrency,
                                                   @PathVariable CurrencyShortName toCurrency,
                                                   @PathVariable Double amount)
    {
        if(fromCurrency == toCurrency)
        {
            //probably a mistake, no importance for statistics, so no increase of count
            return new ResponseEntity<>(amount, HttpStatus.OK);
        }
        var response = exchangeRateService.getRateFromTo(fromCurrency, toCurrency)
                .map(exchangeRate -> new ResponseEntity<>(exchangeRate.getRate() * amount, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
		currencyService.increaseCountFrom(fromCurrency);
		currencyService.increaseCountTo(fromCurrency);
        return response;
    }

    @GetMapping("{fromCurrency}/{toCurrency}/chart")
    public ResponseEntity<String> currencyRateChart(@PathVariable CurrencyShortName fromCurrency,
                                                    @PathVariable CurrencyShortName toCurrency)
    {
		currencyService.increaseCountFrom(fromCurrency);
		currencyService.increaseCountTo(fromCurrency);
        // the https://www.ecb.europa.eu/stats/policy_and_exchange_rates/euro_reference_exchange_rates/html/index.en.html
        // site can only convert from and to EUR ;)
        return new ResponseEntity<>("https://www.xe.com/currencycharts/?from=" + fromCurrency +
                "&to=" + toCurrency, HttpStatus.OK);
    }


}
