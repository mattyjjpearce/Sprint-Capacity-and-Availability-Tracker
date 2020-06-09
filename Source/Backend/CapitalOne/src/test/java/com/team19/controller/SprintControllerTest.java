package com.team19.controller;

import com.team19.entity.Sprint;
import com.team19.service.SprintService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class SprintControllerTest {

    @Mock
    SprintService sprintService;

    SprintController sut;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        sut = new SprintController(sprintService);
    }

    @Test
    public void addSprintSuccessfulWhenValidateIsSuccessful() throws ParseException {
        Sprint sprint = new Sprint.Builder(0)
                .withSprintDescription("Planning Sprint")
                .withStartDate(new SimpleDateFormat("yyyy/MM/dd").parse("2020/06/01"))
                .withSprintLength(1)
                .withTeamId(1)
                .withPointsPlanned(11)
                .withPointsCompleted(11)
                .build();

        given(sprintService.validate(any())).willReturn(true);
        sut.addSprint(sprint);
        verify(sprintService).add(sprint);
    }

    @Test
    public void addSprintFailsWhenValidateFails() throws ParseException {
        Sprint sprint = new Sprint.Builder(0)
                .withSprintDescription("Planning Sprint")
                .withStartDate(new SimpleDateFormat("yyyy/MM/dd").parse("2020/06/01"))
                .withSprintLength(1)
                .withTeamId(1)
                .withPointsPlanned(11)
                .withPointsCompleted(11)
                .build();

        given(sprintService.validate(any())).willReturn(false);
        sut.addSprint(sprint);
        verify(sprintService, never()).add(any());
    }

    @Test
    public void findAllByCallsSprintServiceAndReturnsResult() throws ParseException {
        List<Sprint> expected = Collections.singletonList(new Sprint("Planning Sprint",
                new SimpleDateFormat("yyyy/MM/dd").parse("2020/07/01"),
                1, 11, 11, 1));

        given(sprintService.findAllBy("Planning Sprint", 1,
                new SimpleDateFormat("yyyy/MM/dd").parse("2020/07/01"),
                1, 11, 11)).willReturn(expected);

        Iterable<Sprint> result = sut.findAllBy("Planning Sprint", 1,
                new SimpleDateFormat("yyyy/MM/dd").parse("2020/07/01"),
                1, 11, 11);

        assertThat(result).containsAll(expected);
    }

    @Test
    public void findByTeamIDCallsSprintServiceAndReturnsResult() throws ParseException {
        List<Sprint> expected = Collections.singletonList(new Sprint(null,
                null, null, null, null, 1));

        given(sprintService.findAllBy(null, 1, null, null,
                null, null )).willReturn(expected);

        Iterable<Sprint> result = sut.findAllBy(null, 1, null,
                null, null, null);

        assertThat(result).containsAll(expected);


    }

    @Test
    public void updateSuccessWhenValidateIsSuccessfulAndSprintIDExists() throws ParseException {
        Sprint sprint = new Sprint.Builder(0)
                .withSprintDescription("Planning Sprint")
                .withStartDate(new SimpleDateFormat("yyyy/MM/dd").parse("2020/06/01"))
                .withSprintLength(1)
                .withTeamId(1)
                .withPointsPlanned(11)
                .withPointsCompleted(11)
                .build();

        given(sprintService.validate(any())).willReturn(true);
        given(sprintService.sprintIdExistsInTable(any())).willReturn(true);
        sut.update(sprint, sprint.getSprintId());
        verify(sprintService).add(sprint);
    }

    @Test
    public void updateFailsWhenValidateFails() throws ParseException {
        Sprint sprint = new Sprint.Builder(0)
                .withSprintDescription("Planning Sprint")
                .withStartDate(new SimpleDateFormat("yyyy/MM/dd").parse("2020/06/01"))
                .withSprintLength(1)
                .withTeamId(1)
                .withPointsPlanned(11)
                .withPointsCompleted(11)
                .build();

        given(sprintService.validate(any())).willReturn(false);
        given(sprintService.sprintIdExistsInTable(any())).willReturn(true);
        sut.update(sprint, sprint.getSprintId());
        verify(sprintService, never()).add(sprint);
    }

    @Test
    public void deleteSuccessfulWhenSprintIdExistsInTable() throws ParseException {
        Sprint sprint = new Sprint.Builder(0)
                .withSprintDescription("Planning Sprint")
                .withStartDate(new SimpleDateFormat("yyyy/MM/dd").parse("2020/06/01"))
                .withSprintLength(1)
                .withTeamId(1)
                .withPointsPlanned(11)
                .withPointsCompleted(11)
                .build();

        given(sprintService.sprintIdExistsInTable(any())).willReturn(true);
        sut.delete(0);
        verify(sprintService).delete(0);
    }

    @Test
    public void deleteFailsWhenSprintIdDoesNotExistInTable() throws ParseException {
        Sprint sprint = new Sprint.Builder(0)
                .withSprintDescription("Planning Sprint")
                .withStartDate(new SimpleDateFormat("yyyy/MM/dd").parse("2020/06/01"))
                .withSprintLength(1)
                .withTeamId(1)
                .withPointsPlanned(11)
                .withPointsCompleted(11)
                .build();

        given(sprintService.sprintIdExistsInTable(any())).willReturn(false);
        sut.delete(0);
        verify(sprintService, never()).delete(0);
    }

}