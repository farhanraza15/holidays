package com.federal.holidays.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.federal.holidays.model.Holiday;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday, Long> {
	Optional<Holiday> findByCountry(String country);
}
