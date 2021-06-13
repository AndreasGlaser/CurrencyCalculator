package de.andreas.assignment.service;

import de.andreas.assignment.dto.CurrencyShortName;
import de.andreas.assignment.dto.ExchangeRate;
import de.andreas.assignment.repository.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static de.andreas.assignment.dto.CurrencyShortName.EUR;

@Service
public class ExchangeRateService {

	@Autowired
	ExchangeRateRepository exchangeRateRepository;

	public Optional<ExchangeRate> getRateTo(CurrencyShortName toCurrency)
	{
		return exchangeRateRepository.findByExchangeTo(toCurrency);
	}

	public Optional<ExchangeRate> getRateFromTo(CurrencyShortName fromCurrency, CurrencyShortName toCurrency)
	{
		if (EUR.equals(fromCurrency))
		{
			return getRateTo(toCurrency);
		} else if (EUR.equals(toCurrency))
		{
			return reverseRate(getRateTo(fromCurrency));
		} else
		{
			return convertNonEurRates(fromCurrency, toCurrency);
		}
	}

	private Optional<ExchangeRate> convertNonEurRates(CurrencyShortName fromCurrency, CurrencyShortName toCurrency) {
		var createdRate = new ExchangeRate();
		createdRate.setExchangeFrom(fromCurrency);
		createdRate.setExchangeTo(toCurrency);
		var rateToEur = getRateTo(toCurrency);
		if (rateToEur.isEmpty())
		{
			return rateToEur;
		}
		var rateFromEur = reverseRate(getRateTo(fromCurrency));
		if (rateFromEur.isEmpty())
		{
			return rateFromEur;
		}

		createdRate.setRate(rateToEur.get().getRate() * rateFromEur.get().getRate());
		return Optional.of(createdRate);
	}

	private Optional<ExchangeRate> reverseRate(Optional<ExchangeRate> normalRate) {
		if (normalRate.isEmpty())
		{
			return normalRate;
		}
		return Optional.of(new ExchangeRate(
				normalRate.get().getId(),
				normalRate.get().getExchangeTo(),
				normalRate.get().getExchangeFrom(),
				normalRate.get().getReverseRate()));

	}

	public List<ExchangeRate> getExchangeRates()
	{
		return exchangeRateRepository.findAll();
	}
}
