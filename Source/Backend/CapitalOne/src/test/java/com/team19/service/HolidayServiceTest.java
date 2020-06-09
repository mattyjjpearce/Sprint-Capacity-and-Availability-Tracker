package com.team19.service;

import com.team19.entity.Holiday;
import com.team19.repository.HolidayRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class HolidayServiceTest {

    @Mock
    HolidayRepository holidayRepository;

    HolidayService sut;

    @Before
    public void setSut() {
        MockitoAnnotations.initMocks(this);
        sut = new HolidayService(holidayRepository);
    }

    @Test
    public void addHolidayByRepositorySavingIt() throws ParseException {
        Holiday holiday = new Holiday.Builder(0)
                .withEmployeeID(0)
                .withLength(5)
                .withStartDate(new SimpleDateFormat("yyyy/MM/dd").parse("2020/04/15"))
                .build();

        sut.add(holiday);
        verify(holidayRepository, times(1)).save(holiday);
        verifyNoMoreInteractions(holidayRepository);
    }
}