package com.federal.holidays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.federal.holidays.model.Holiday;
import com.federal.holidays.repository.HolidayRepository;
import com.federal.holidays.service.HolidayServiceImpl;

public class HolidayServiceTest {

	@Mock
	private HolidayRepository holidayRepository;

	@InjectMocks
	private HolidayServiceImpl holidayService;

	public HolidayServiceTest() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetHolidayByName() {

		List<Holiday> holidays = new ArrayList<Holiday>();
		holidays.add(getHolidayTestData());
		
		when(holidayRepository.findByCountry("CANADA")).thenReturn(holidays);

		List<Holiday> result = holidayService.getHolidaysByCountry("CANADA");
		assertTrue(!result.isEmpty());
		assertEquals("New Year Day.", result.get(0).getName());
	}

	@Test
	void testAddHoliday() {
		when(holidayRepository.save(any(Holiday.class))).thenReturn(getHolidayTestData());

		Holiday savedHoliday = holidayService.addHoliday(getHolidayTestData());
		assertNotNull(savedHoliday);
		assertEquals("New Year Day.", savedHoliday.getName());
	}

	@Test
	void testUpdateHoliday() {

		when(holidayRepository.findById(1l)).thenReturn(Optional.of(getHolidayTestData()));

		when(holidayRepository.save(any(Holiday.class))).thenReturn(getHolidayTestData());

		Holiday savedHoliday = holidayService.updateHoliday(1l, getHolidayTestData());
		assertNotNull(savedHoliday);
		assertEquals(1l, savedHoliday.getId());
	}

	public Holiday getHolidayTestData() {

		Holiday holiday = new Holiday();
		holiday.setCountry("CANADA");
		holiday.setId(1l);
		holiday.setName("New Year Day.");
		holiday.setDate(LocalDate.now());

		return holiday;
	}

}
