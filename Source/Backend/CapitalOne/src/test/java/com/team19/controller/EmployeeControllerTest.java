package com.team19.controller;

import com.team19.entity.Employee;
import com.team19.service.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;


public class EmployeeControllerTest {

    @Mock
    EmployeeService employeeService;

    EmployeeController sut;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        sut = new EmployeeController(employeeService);

    }

    @Test
    public void findAllByCallsEmployeeServiceAndReturnsResult() {
        List<Employee> expected = Collections.singletonList(new Employee(0, "Dave", "Berkerly", "0", "dave@email.com", 0));
        given(employeeService.findAllBy(0, "Dave", "Berkerly", "0", "dave@email.com", 0)).willReturn(expected);
        Iterable<Employee> result = sut.findAllBy(0, "Dave", "Berkerly", "0", "dave@email.com", 0);
        assertThat(result).containsAll(expected);
    }

    @Test
    public void addEmployeeSuccessfullyAddToTableWhenValidationPassesAndEidDoesNotExist() {
        Employee employee = new Employee.Builder(0)
                .withFirstName("A")
                .withLastName("B")
                .withTeamId(0)
                .withPosition("Scrum Master")
                .withEmail("ab@email.com")
                .build();

        given(employeeService.validate(any())).willReturn(true);
        given(employeeService.eidExistsInTable(any())).willReturn(false);
        sut.add(employee);
        verify(employeeService).add(employee);
    }

    @Test
    public void addEmployeeFailsWhenValidationFailsAndEidDoesNotExist() {
        Employee employee = new Employee.Builder(0)
                .withFirstName("A")
                .withLastName("B")
                .withTeamId(0)
                .withPosition("Scrum Master")
                .withEmail("ab@email.com")
                .build();

        given(employeeService.validate(any())).willReturn(false);
        given(employeeService.eidExistsInTable(any())).willReturn(false);
        sut.add(employee);
        verify(employeeService, never()).add(any());
    }

    @Test
    public void addEmployeeFailsWhenValidationFailsAndEidDoesExist()
    {
        Employee employee = new Employee.Builder(0)
                .withFirstName("A")
                .withLastName("B")
                .withTeamId(0)
                .withPosition("Scrum Master")
                .withEmail("ab@email.com")
                .build();

        given(employeeService.validate(any())).willReturn(false);
        given(employeeService.eidExistsInTable(any())).willReturn(true);
        sut.add(employee);
        verify(employeeService, never()).add(any());

    }

    @Test
    public void addEmployeeFailsWhenValidationSucceedsAndEidDoesExist()
    {
        Employee employee = new Employee.Builder(0)
                .withFirstName("A")
                .withLastName("B")
                .withTeamId(0)
                .withPosition("Scrum Master")
                .withEmail("ab@email.com")
                .build();

        given(employeeService.validate(any())).willReturn(true);
        given(employeeService.eidExistsInTable(any())).willReturn(true);
        sut.add(employee);
        verify(employeeService, never()).add(any());

    }
}