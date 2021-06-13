package de.andreas.assignment.controller;

import de.andreas.assignment.dto.Currency;
import de.andreas.assignment.dto.CurrencyShortName;
import de.andreas.assignment.service.CurrencyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {

    final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService)
    {
        this.currencyService = currencyService;
    }

    @GetMapping
    public ResponseEntity<List<Currency>> getCurrencies()
    {
        return new ResponseEntity<>(currencyService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{currencyShortName}")
    public ResponseEntity<Currency> currencyRate(@PathVariable CurrencyShortName currencyShortName)
    {
        return currencyService.findByShortName(currencyShortName)
                .map(currency -> new ResponseEntity<>(currency, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
