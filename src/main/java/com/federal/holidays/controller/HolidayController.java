package com.federal.holidays.controller;

import java.util.List;

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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/holidays")
@Tag(name = "Holiday Management System")
public class HolidayController {

	@Autowired
	private HolidayService holidayService;

	@Operation(summary = "List all holidays by country name.")
	@GetMapping("/findByCountry/{country}")
	public ResponseEntity<List<Holiday>> getHolidaysByCountry(@PathVariable String country) {
		
		List<Holiday> result = holidayService.getHolidaysByCountry(country);
		
		if (result.isEmpty()) {
			return new ResponseEntity<List<Holiday>>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<Holiday>>(result, HttpStatus.OK);
	}

	@Operation(summary = "Add a single record of holiday.")
	@PostMapping("/createHoliday")
	public Holiday addHoliday(@RequestBody Holiday holiday) {
		return holidayService.addHoliday(holiday);
	}

	@Operation(summary = "update a single record of holiday.")
	@PutMapping("/updateHoliday/{id}")
	public ResponseEntity<Holiday> updateHoliday(@PathVariable Long id, @RequestBody Holiday holiday) {

		Holiday result = holidayService.updateHoliday(id, holiday);
		if (null == result) {
			return new ResponseEntity<Holiday>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Holiday>(HttpStatus.OK);
	}
}
