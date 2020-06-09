package com.team19.controller;

import com.team19.entity.Employee;
import com.team19.entity.Holiday;
import com.team19.entity.WorkPattern;
import com.team19.service.WorkPatternService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class WorkPatternControllerTest {

    @Mock
    WorkPatternService patternService;

    WorkPatternController sut;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
        sut = new WorkPatternController(patternService);
    }

    @Test
    public void addWorkPatternSuccessfullyAddToTableWhenValidationPassesAndEidDoesNotExist() {
        WorkPattern pattern = new WorkPattern.Builder(0)
                .withMondayHours(8)
                .withTuesdayHours(8)
                .withWednesdayHours(8)
                .withThursdayHours(8)
                .withFridayHours(8)
                .build();

        given(patternService.validate(any())).willReturn(true);
        given(patternService.employeeIDExistsInTable(any())).willReturn(false);
        sut.addWorkPattern(pattern);
        verify(patternService).add(pattern);
    }

    @Test
    public void addWorkPatternFailsToAddToTableWhenValidationPassesAndEidDoesExist() {
        WorkPattern pattern = new WorkPattern.Builder(10)
                .withMondayHours(8)
                .withTuesdayHours(8)
                .withWednesdayHours(8)
                .withThursdayHours(8)
                .withFridayHours(8)
                .build();

        given(patternService.validate(any())).willReturn(true);
        given(patternService.employeeIDExistsInTable(any())).willReturn(true);
        sut.addWorkPattern(pattern);
        verify(patternService, never()).add(any());
    }

    @Test
    public void addWorkPatternFailsToAddToTableWhenValidationFailsAndEidDoesNotExist() {
        WorkPattern pattern = new WorkPattern.Builder(0)
                .withMondayHours(8)
                .withTuesdayHours(8)
                .withWednesdayHours(8)
                .withThursdayHours(8)
                .withFridayHours(8)
                .build();

        given(patternService.validate(any())).willReturn(false);
        given(patternService.employeeIDExistsInTable(any())).willReturn(false);
        sut.addWorkPattern(pattern);
        verify(patternService, never()).add(any());
    }

    @Test
    public void addWorkPatternFailsToAddToTableWhenValidationFailsAndEidDoesExist() {
        WorkPattern pattern = new WorkPattern.Builder(0)
                .withMondayHours(8)
                .withTuesdayHours(8)
                .withWednesdayHours(8)
                .withThursdayHours(8)
                .withFridayHours(8)
                .build();

        given(patternService.validate(any())).willReturn(false);
        given(patternService.employeeIDExistsInTable(any())).willReturn(false);
        sut.addWorkPattern(pattern);
        verify(patternService, never()).add(any());
    }

    @Test
    public void findAllByCallsWorkPatternServiceAndReturnsResult()
    {
        List<WorkPattern> expected = Collections.singletonList(new WorkPattern(0, 8, 8, 8, 8, 8));
        given(patternService.findAllBy(0, 8, 8, 8, 8, 8)).willReturn(expected);
        Iterable<WorkPattern> result = sut.findAllBy(0, 8, 8, 8, 8, 8);
        assertThat(result).containsAll(expected);
    }

    @Test
    public void putUpdatesWhenValidateIsSuccessfulAndEmployeeIDExists() throws ParseException {
        WorkPattern pattern = new WorkPattern.Builder(10)
                .withMondayHours(8)
                .withTuesdayHours(8)
                .withWednesdayHours(8)
                .withThursdayHours(8)
                .withFridayHours(8)
                .build();

        given(patternService.validate(any())).willReturn(true);
        given(patternService.workPatternIdExistsInTable(any())).willReturn(true);
        sut.put(10, pattern);
        verify(patternService).update(10, pattern);
    }

    @Test
    public void putDoesNotUpdateWhenValidateFailsAndEmployeeIDExists() throws ParseException {
        WorkPattern pattern = new WorkPattern.Builder(0)
                .withMondayHours(8)
                .withTuesdayHours(8)
                .withWednesdayHours(8)
                .withThursdayHours(8)
                .withFridayHours(8)
                .build();

        given(patternService.validate(any())).willReturn(false);
        given(patternService.employeeIDExistsInTable(any())).willReturn(true);
        sut.put(0, pattern);
        verify(patternService, never()).add(any());
        verify(patternService, never()).update(any(), any());
    }

    @Test
    public void putAddsNewRecordWhenValidateIsSuccessfulAndEmployeeIDDoesNotExist() throws ParseException {
        WorkPattern pattern = new WorkPattern.Builder(0)
                .withMondayHours(8)
                .withTuesdayHours(8)
                .withWednesdayHours(8)
                .withThursdayHours(8)
                .withFridayHours(8)
                .build();

        given(patternService.validate(any())).willReturn(true);
        given(patternService.employeeIDExistsInTable(any())).willReturn(false);
        sut.put(0, pattern);
        verify(patternService).add(pattern);
        verify(patternService, never()).update(any(), any());
    }

    @Test
    public void putDoesNotUpdateWhenValidateFailsAndEmployeeIDDoesNotExist() throws ParseException {
        WorkPattern pattern = new WorkPattern.Builder(0)
                .withMondayHours(8)
                .withTuesdayHours(8)
                .withWednesdayHours(8)
                .withThursdayHours(8)
                .withFridayHours(8)
                .build();

        given(patternService.validate(any())).willReturn(false);
        given(patternService.employeeIDExistsInTable(any())).willReturn(false);
        sut.put(0, pattern);
        verify(patternService, never()).add(any());
        verify(patternService, never()).update(any(), any());
    }

    @Test
    public void deleteSuccessfulWhenEmployeeIDExistsInTable() throws ParseException {
        WorkPattern pattern = new WorkPattern.Builder(0)
                .withMondayHours(8)
                .withTuesdayHours(8)
                .withWednesdayHours(8)
                .withThursdayHours(8)
                .withFridayHours(8)
                .build();

        given(patternService.employeeIDExistsInTable(any())).willReturn(true);
        sut.delete(0);
        verify(patternService).delete(0);
    }

    @Test
    public void deleteFailsWhenEmployeeIDDoesNotExistInTable() throws ParseException {
        WorkPattern pattern = new WorkPattern.Builder(0)
                .withMondayHours(8)
                .withTuesdayHours(8)
                .withWednesdayHours(8)
                .withThursdayHours(8)
                .withFridayHours(8)
                .build();

        given(patternService.employeeIDExistsInTable(any())).willReturn(false);
        sut.delete(0);
        verify(patternService, never()).delete(0);
    }

}
