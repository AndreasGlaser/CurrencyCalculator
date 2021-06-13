package de.andreas.assignment.dto;

import lombok.*;

import javax.persistence.*;

@RequiredArgsConstructor
@NoArgsConstructor
@Entity
public class Currency {

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

    @Getter
    @Setter
    @NonNull
	@Enumerated(EnumType.STRING)
    private CurrencyShortName shortName;
    @Getter
    @Setter
    @NonNull
    private String name;
    @Getter
    @Setter
    private Long countOfExchangedTo = 0L;
    @Getter
    @Setter
    private Long countOfExchangedFrom = 0L;

	public void increaseCountTo()
    {
        this.countOfExchangedTo += 1;
    }

    public void increaseCountFrom()
    {
        this.countOfExchangedFrom += 1;
    }
}
