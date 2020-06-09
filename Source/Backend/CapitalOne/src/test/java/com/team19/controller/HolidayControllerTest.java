package com.team19.controller;

import com.team19.entity.Holiday;
import com.team19.service.HolidayService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;


import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;


public class HolidayControllerTest {

    @Mock
    HolidayService holidayService;

    HolidayController sut;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        sut = new HolidayController(holidayService);
    }


    @Test
    public void addHolidaySuccessfulWhenValidateIsSuccessful() throws ParseException {
        Holiday holiday = new Holiday.Builder(0)
                .withEmployeeID(0)
                .withLength(5)
                .withStartDate(new SimpleDateFormat("yyyy/MM/dd").parse("2020/04/15"))
                .build();

        given(holidayService.validate(any())).willReturn(true);
        sut.addHoliday(holiday);
        verify(holidayService).add(holiday);
    }

    @Test
    public void addHolidayFailsWhenValidateFails() throws ParseException {
        Holiday holiday = new Holiday.Builder(0)
                .withEmployeeID(0)
                .withLength(5)
                .withStartDate(new SimpleDateFormat("yyyy/MM/dd").parse("2020/04/15"))
                .build();

        given(holidayService.validate(any())).willReturn(false);
        sut.addHoliday(holiday);
        verify(holidayService, never()).add(any());
    }

    @Test
    public void findAllByCallsHolidayServiceAndReturnsResult() throws ParseException {
        List<Holiday> expected = Collections.singletonList(new Holiday(0,
                new SimpleDateFormat("yyyy/MM/dd").parse("2020/04/15"),
                3));

        given(holidayService.findAllBy(0,
                new SimpleDateFormat("yyyy/MM/dd").parse("2020/04/15"),
                3)).willReturn(expected);

        Iterable<Holiday> result = sut.findAllBy(0,
                new SimpleDateFormat("yyyy/MM/dd").parse("2020/04/15"),
                3);

        assertThat(result).containsAll(expected);
    }


    @Test
    public void findByEmployeeIDCallsHolidayServiceAndReturnsResult() {
        List<Holiday> expected = Collections.singletonList(new Holiday(0, null, null));
        given(holidayService.findAllBy(0, null, null)).willReturn(expected);
        Iterable<Holiday> result = sut.findAllBy(0, null, null);
        assertThat(result).containsAll(expected);
    }

    @Test
    public void putUpdatesWhenValidateIsSuccessfulAndHolidayIDExists() throws ParseException {
        Holiday holiday = new Holiday.Builder(0)
                .withEmployeeID(0)
                .withLength(5)
                .withStartDate(new SimpleDateFormat("yyyy/MM/dd").parse("2020/04/15"))
                .build();

        given(holidayService.validate(any())).willReturn(true);
        given(holidayService.employeeIDExistsInTable(any())).willReturn(true);
        sut.put(holiday);
        verify(holidayService).add(holiday);
    }


    public void putFailsWhenValidateFails() throws ParseException {
        Holiday holiday = new Holiday.Builder(0)
                .withEmployeeID(0)
                .withLength(5)
                .withStartDate(new SimpleDateFormat("yyyy/MM/dd").parse("2020/04/15"))
                .build();

        given(holidayService.validate(any())).willReturn(false);
        given(holidayService.employeeIDExistsInTable(any())).willReturn(true);
        sut.put(holiday);
        verify(holidayService, never()).add(holiday);
    }

    @Test
    public void deleteSuccessfulWhenHolidayIDExistsInTable() throws ParseException {
        Holiday holiday = new Holiday.Builder(0)
                .withEmployeeID(0)
                .withLength(5)
                .withStartDate(new SimpleDateFormat("yyyy/MM/dd").parse("2020/04/15"))
                .build();

        given(holidayService.holidayIDExistsInTable(any())).willReturn(true);
        sut.delete(0);
        verify(holidayService).delete(0);
    }

    @Test
    public void deleteFailsWhenHolidayIDDoesNotExistInTable() throws ParseException {
        Holiday holiday = new Holiday.Builder(0)
                .withEmployeeID(0)
                .withLength(5)
                .withStartDate(new SimpleDateFormat("yyyy/MM/dd").parse("2020/04/15"))
                .build();

        given(holidayService.holidayIDExistsInTable(any())).willReturn(false);
        sut.delete(0);
        verify(holidayService, never()).delete(0);
    }
}