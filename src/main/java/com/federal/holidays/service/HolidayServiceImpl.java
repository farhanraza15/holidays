package com.federal.holidays.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.federal.holidays.model.Holiday;
import com.federal.holidays.repository.HolidayRepository;

@Service
public class HolidayServiceImpl implements HolidayService {

	@Autowired
	private HolidayRepository holidayRepository;

	public Optional<Holiday> getHolidaysByCountry(String country) {
		return holidayRepository.findByCountry(country);
	}

	public Holiday addHoliday(Holiday holiday) {
		return holidayRepository.save(holiday);
	}

	public Holiday updateHoliday(Long id, Holiday holidayDetails) {

		Optional<Holiday> holidayOptional = holidayRepository.findById(id);

		if (holidayOptional.isPresent()) {
			Holiday holiday = holidayOptional.get();
			holiday.setName(holidayDetails.getName());
			holiday.setDate(holidayDetails.getDate());
			holiday.setCountry(holidayDetails.getCountry());

			return holidayRepository.save(holiday);
		}

		return null;
	}
}
