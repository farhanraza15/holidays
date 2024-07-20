package com.federal.holidays.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.federal.holidays.model.Holiday;

@Service
public interface HolidayService {

    public List<Holiday> getHolidaysByCountry(String country);
    public Holiday addHoliday(Holiday holiday);
    public Holiday updateHoliday(Long id, Holiday holiday);
}
