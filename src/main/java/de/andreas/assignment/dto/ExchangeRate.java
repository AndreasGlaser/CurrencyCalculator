package de.andreas.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Entity
public class ExchangeRate {

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    @Getter
    @Setter
	@Enumerated(EnumType.STRING)
    private CurrencyShortName exchangeFrom;
    @Getter
    @Setter
	@Enumerated(EnumType.STRING)
    private CurrencyShortName exchangeTo;
    @Getter
    @Setter
    private Double rate;


	public Double getReverseRate()
    {
        return 1 / rate;
    }

}
