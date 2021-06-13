package de.andreas.assignment.repository;

import de.andreas.assignment.dto.Currency;
import de.andreas.assignment.dto.CurrencyShortName;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static de.andreas.assignment.dto.CurrencyShortName.*;

@Repository
public interface CurrencyRepository extends CrudRepository<Currency, String> {

	@Override
	List<Currency> findAll();

	Optional<Currency> findCurrencyByShortName(CurrencyShortName shortName);
}
