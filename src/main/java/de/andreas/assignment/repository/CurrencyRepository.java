package de.andreas.assignment.repository;

import de.andreas.assignment.dto.Currency;
import de.andreas.assignment.dto.CurrencyShortName;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static de.andreas.assignment.dto.CurrencyShortName.*;

@Repository
public class CurrencyRepository {

    final List<Currency> currencies = List.of(
            new Currency(EUR, "Euro"),
            new Currency(USD, "US Dollar"),
            new Currency(HUF, "Hungarian forint"),
            new Currency(CZK, "Czech koruna"),
            new Currency(GBP, "Pound sterling")
    );

    public List<Currency> loadCurrencies()
    {
        return this.currencies;
    }

    public Optional<Currency> loadCurrency(CurrencyShortName shortName)
    {
        return this.currencies.stream().filter(currency -> currency.getShortName() == shortName).findFirst();
    }

    public void increaseCountTo(CurrencyShortName shortName)
    {
        currencies.stream().filter(currency -> currency.getShortName() == shortName).findFirst().ifPresent(Currency::increaseCountTo);
    }

    public void increaseCountFrom(CurrencyShortName shortName)
    {
        currencies.stream().filter(currency -> currency.getShortName() == shortName).findFirst().ifPresent(Currency::increaseCountFrom);
    }
}
