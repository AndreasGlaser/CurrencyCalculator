package de.andreas.assignment.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class Currency {

    @Getter
    @Setter
    @NonNull
    private CurrencyShortName shortName;
    @Getter
    @Setter
    @NonNull
    private String name;
    @Getter
    @Setter
    private long countOfExchangedTo = 0;
    @Getter
    @Setter
    private long countOfExchangedFrom = 0;

    public void increaseCountTo()
    {
        this.countOfExchangedTo += 1;
    }

    public void increaseCountFrom()
    {
        this.countOfExchangedFrom += 1;
    }
}
