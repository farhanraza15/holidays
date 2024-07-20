package com.federal.holidays.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.federal.holidays.model.Holiday;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday, Long> {
	List<Holiday> findByCountry(String country);
}
