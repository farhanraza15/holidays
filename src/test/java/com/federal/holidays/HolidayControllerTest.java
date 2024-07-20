package com.federal.holidays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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
	

	@Test
	public void addHoliday() throws Exception {
		when(holidayService.getHolidaysByCountry("CANADA")).thenReturn(Optional.of(getHolidayTestData()));
		mockMvc.perform(post("/api/holidays").contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\":\"New Year Day\",\"date\":\"2024-01-01\",\"country\":\"CANADA\"}"))
				.andExpect(status().isOk());
	}

	@Test
	public void getHolidaysByCountryNotFound() throws Exception {
		when(holidayService.getHolidaysByCountry("CANADA")).thenReturn(Optional.of(new Holiday()));
		this.mockMvc.perform(get("/api/holidays/CANADA")).andExpect(status().isNotFound());
	}
	
	@Test
	public void getHolidaysByCountry() throws Exception {
		when(holidayService.getHolidaysByCountry("CANADA")).thenReturn(Optional.of(getHolidayTestData()));
		this.mockMvc.perform(get("/api/holidays/CANADA")).andExpect(status().isOk());
	}

	public Holiday getHolidayTestData() {
	
		Holiday holiday = new Holiday();
		holiday.setCountry("CANADA");
		holiday.setId(1l);
		holiday.setName("New Year Day.");
		
		return holiday;
	}

}
