package de.andreas.assignment.service;

import de.andreas.assignment.dto.Currency;
import de.andreas.assignment.dto.CurrencyShortName;
import de.andreas.assignment.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {

	final CurrencyRepository currencyRepository;

	public CurrencyService(CurrencyRepository currencyRepository) {
		this.currencyRepository = currencyRepository;
	}

	public List<Currency> findAll()
	{
		return currencyRepository.findAll();
	}

	public Optional<Currency> findByShortName(CurrencyShortName shortName)
	{
		return currencyRepository.findCurrencyByShortName(shortName);
	}

	public void increaseCountTo(CurrencyShortName fromCurrency) {
		this.currencyRepository.findCurrencyByShortName(fromCurrency).ifPresent(Currency::increaseCountTo);
	}

	public void increaseCountFrom(CurrencyShortName fromCurrency) {
		this.currencyRepository.findCurrencyByShortName(fromCurrency).ifPresent(Currency::increaseCountFrom);
	}
}
