package com.team19.controller;

import java.text.ParseException;
import java.util.List;
import com.team19.entity.EmployeeLeaveInfo;
import com.team19.service.EmployeeLeaveInfoService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class EmployeeLeaveInfoControllerTest {

    @Mock
    EmployeeLeaveInfoService employeeLeaveInfoService;

    EmployeeLeaveInfoController sut;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        sut = new EmployeeLeaveInfoController(employeeLeaveInfoService);
    }


    @Test
    public void addSuccessfulWhenValidateSuccessful() {
        EmployeeLeaveInfo employeeLeaveInfo = new EmployeeLeaveInfo.Builder(1)
                .build();

        given(employeeLeaveInfoService.validate(any())).willReturn(true);
        sut.add(employeeLeaveInfo);
        verify(employeeLeaveInfoService).add(employeeLeaveInfo);
    }

    @Test
    public void addFailsWhenValidateFails() {
        EmployeeLeaveInfo employeeLeaveInfo = new EmployeeLeaveInfo.Builder(1)
                .build();

        given(employeeLeaveInfoService.validate(any())).willReturn(false);
        sut.add(employeeLeaveInfo);
        verify(employeeLeaveInfoService, never()).add(employeeLeaveInfo);
    }

    @Test
    public void findAllByCallsServiceAndReturnsResult() {
        List<EmployeeLeaveInfo> expected = Collections.singletonList(new EmployeeLeaveInfo(1));
        given(employeeLeaveInfoService.findAllBy(1)).willReturn(expected);
        Iterable<EmployeeLeaveInfo> result = sut.findAllBy(1);
        assertThat(result).containsAll(expected);
    }

    @Test
    public void findByEmployeeIDCallsServiceAndReturnsResult() {
        List<EmployeeLeaveInfo> expected = Collections.singletonList(new EmployeeLeaveInfo(1));
        given(employeeLeaveInfoService.findAllBy(1)).willReturn(expected);
        Iterable<EmployeeLeaveInfo> result = sut.findAllBy(1);
        assertThat(result).containsAll(expected);
    }

    @Test
    public void updateSuccessfulWhenValidateIsSuccessfulAndEmployeeIdExists() {
        EmployeeLeaveInfo employeeLeaveInfo = new EmployeeLeaveInfo.Builder(1).build();
        given(employeeLeaveInfoService.validate(any())).willReturn(true);
        given(employeeLeaveInfoService.eidExistsInTable(any())).willReturn(true);
        sut.update(employeeLeaveInfo, employeeLeaveInfo.getEID());
        verify(employeeLeaveInfoService).add(employeeLeaveInfo);
    }

    @Test
    public void updateFailsWhenValidateFails() {
        EmployeeLeaveInfo employeeLeaveInfo = new EmployeeLeaveInfo.Builder(1).build();
        given(employeeLeaveInfoService.validate(any())).willReturn(false);
        given(employeeLeaveInfoService.eidExistsInTable(any())).willReturn(true);
        sut.update(employeeLeaveInfo, employeeLeaveInfo.getEID());
        verify(employeeLeaveInfoService, never()).add(employeeLeaveInfo);
    }

    @Test
    public void deleteSuccessfulWhenEmployeeIdExistsInTable() {
        EmployeeLeaveInfo employeeLeaveInfo = new EmployeeLeaveInfo.Builder(1).build();
        given(employeeLeaveInfoService.eidExistsInTable(any())).willReturn(true);
        sut.delete(1);
        verify(employeeLeaveInfoService).delete(1);
    }

    @Test
    public void deleteFailsWhenEmployeeIdDoesNotExistInTable() {
        EmployeeLeaveInfo employeeLeaveInfo = new EmployeeLeaveInfo.Builder(1).build();
        given(employeeLeaveInfoService.eidExistsInTable(any())).willReturn(false);
        sut.delete(1);
        verify(employeeLeaveInfoService, never()).delete(1);
    }
}