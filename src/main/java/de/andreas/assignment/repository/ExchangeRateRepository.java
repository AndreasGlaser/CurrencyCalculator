package de.andreas.assignment.repository;

import de.andreas.assignment.dto.Currency;
import de.andreas.assignment.dto.CurrencyShortName;
import de.andreas.assignment.dto.ExchangeRate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExchangeRateRepository extends CrudRepository<ExchangeRate, String> {

	@Override
	List<ExchangeRate> findAll();

	Optional<ExchangeRate> findByExchangeTo(CurrencyShortName shortName);
}
