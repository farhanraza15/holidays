package com.federal.holidays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.federal.holidays.model.Holiday;
import com.federal.holidays.service.HolidayService;

@SpringBootTest
@AutoConfigureMockMvc
public class HolidayControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@Mock
	private HolidayService holidayService;
	private Holiday holiday1;
	private Holiday holiday2;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		holiday1 = new Holiday();
		holiday1.setId(1L);
		holiday1.setName("New Year");
		holiday1.setDate(LocalDate.now());

		holiday2 = new Holiday();
		holiday2.setId(2L);
		holiday2.setName("Independence Day");
		holiday2.setDate(LocalDate.now());
	}

	@Test
	public void addHoliday() throws Exception {
		mockMvc.perform(post("/api/holidays").contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\":\"New Year Day\",\"date\":\"2024-01-01\",\"country\":\"CANADA\"}"))
				.andExpect(status().isOk());
	}

//	@Test
	public void getHolidaysByCountryNotFound() throws Exception {
		when(holidayService.getHolidaysByCountry(anyString())).thenReturn(Optional.empty());
		this.mockMvc.perform(get("/api/holidays/CANADA")).andExpect(status().isNotFound());
	}

//	@Test
	public void getHolidaysByCountry() throws Exception {
		when(holidayService.getHolidaysByCountry(anyString())).thenReturn(Optional.of(getHolidayTestData()));
		this.mockMvc.perform(get("/api/holidays/CANADA")).andExpect(status().isOk());
	}

//	@Test
	public void updateHolidaysWithInvalidId() throws Exception {
		when(holidayService.updateHoliday(any(Long.class), any(Holiday.class))).thenReturn(null);
		this.mockMvc.perform(get("/api/holidays/0")).andExpect(status().isNotFound());
	}

//	@Test
	public void updateHolidaysWithValidId() throws Exception {
		when(holidayService.updateHoliday(any(Long.class), any(Holiday.class))).thenReturn(holiday1);
		this.mockMvc.perform(get("/api/holidays/1")).andExpect(status().isOk());
	}

	public Holiday getHolidayTestData() {

		Holiday holiday = new Holiday();
		holiday.setCountry("CANADA");
		holiday.setId(1l);
		holiday.setName("New Year Day.");

		return holiday;
	}

}
