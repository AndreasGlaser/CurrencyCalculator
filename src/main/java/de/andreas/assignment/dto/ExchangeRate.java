package de.andreas.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@RequiredArgsConstructor
public class ExchangeRate {

    @Getter
    @Setter
    private CurrencyShortName exchangeFrom;
    @Getter
    @Setter
    private CurrencyShortName exchangeTo;
    @Getter
    @Setter
    private Double rate;

    public Double getReverseRate()
    {
        return 1 / rate;
    }

}
