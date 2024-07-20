package com.federal.holidays.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.federal.holidays.model.Holiday;
import com.federal.holidays.service.HolidayService;

@RestController
@RequestMapping("/api/holidays")
public class HolidayController {

	@Autowired
	private HolidayService holidayService;

	@GetMapping("/{country}")
	public ResponseEntity<Optional<Holiday>> getHolidaysByCountry(@PathVariable String country) {
		
		if (holidayService.getHolidaysByCountry(country).isEmpty()) {
			return new ResponseEntity<Optional<Holiday>>(HttpStatus.NOT_FOUND);
		}
		Optional<Holiday> result = holidayService.getHolidaysByCountry(country);
		return new ResponseEntity<Optional<Holiday>>(result, HttpStatus.OK);
	}

	@PostMapping
	public Holiday addHoliday(@RequestBody Holiday holiday) {
		return holidayService.addHoliday(holiday);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Holiday> updateHoliday(@PathVariable Long id, @RequestBody Holiday holiday) {
		if (null == holidayService.updateHoliday(id, holiday)) {
			return new ResponseEntity<Holiday>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Holiday>(HttpStatus.OK);
	}
}
