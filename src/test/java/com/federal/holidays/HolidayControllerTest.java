package com.federal.holidays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.federal.holidays.model.Holiday;
import com.federal.holidays.service.HolidayService;

@SpringBootTest
@AutoConfigureMockMvc
public class HolidayControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private HolidayService holidayService;
	private Holiday holiday1;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		holiday1 = new Holiday();
		holiday1.setId(1L);
		holiday1.setName("New Year");
		holiday1.setCountry("CANADA");

	}

	@Test
	public void addHoliday() throws Exception {
		mockMvc.perform(post("/api/holidays/createHoliday").contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\":\"New Year Day\",\"date\":\"2024-01-01\",\"country\":\"CANADA\"}"))
				.andExpect(status().isOk());
	}

	@Test
	public void getHolidaysByCountryNotFound() throws Exception {
		when(holidayService.getHolidaysByCountry(anyString())).thenReturn(Collections.emptyList());
		this.mockMvc.perform(get("/api/holidays/findByCountry/CANADA").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	public void getHolidaysByCountry() throws Exception {
		when(holidayService.getHolidaysByCountry(anyString())).thenReturn(Arrays.asList(holiday1));
		this.mockMvc.perform(get("/api/holidays/findByCountry/CANADA").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void updateHolidaysWithInvalidId() throws Exception {
		when(holidayService.updateHoliday(any(Long.class), any(Holiday.class))).thenReturn(null);
		this.mockMvc.perform(put("/api/holidays/updateHoliday/0").contentType(MediaType.APPLICATION_JSON).content(asJsonString(holiday1))).andExpect(status().isNotFound());
	}

	@Test
	public void updateHolidaysWithValidId() throws Exception {
		when(holidayService.updateHoliday(any(Long.class), any(Holiday.class))).thenReturn(holiday1);
		this.mockMvc.perform(put("/api/holidays/updateHoliday/1").contentType(MediaType.APPLICATION_JSON).content(asJsonString(holiday1))).andExpect(status().isOk());
	}
	
    // Utility method to convert object to JSON string
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
