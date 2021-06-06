package de.andreas.assignment.repository;

import de.andreas.assignment.dto.CurrencyShortName;
import de.andreas.assignment.dto.ExchangeRate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static de.andreas.assignment.dto.CurrencyShortName.*;

@Repository
public class ExchangeRateRepository {

    List<ExchangeRate> exchangeRates = List.of(
            new ExchangeRate(EUR, USD, 1.2188),
            new ExchangeRate(EUR, HUF, 349.11),
            new ExchangeRate(EUR, CZK, 25.452),
            new ExchangeRate(EUR, GBP, 0.85870));



    public Optional<ExchangeRate> getRateTo(CurrencyShortName toCurrency)
    {
        return exchangeRates.stream().filter(rate -> rate.getExchangeTo() == toCurrency)
                .findFirst();
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
                normalRate.get().getExchangeTo(),
                normalRate.get().getExchangeFrom(),
                normalRate.get().getReverseRate()));

    }

    public List<ExchangeRate> getExchangeRates()
    {
        return exchangeRates;
    }
}
