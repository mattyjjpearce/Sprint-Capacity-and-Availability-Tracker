package com.team19.service;

import com.team19.entity.WorkPattern;
import com.team19.repository.WorkPatternRepository;
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

public class WorkPatternServiceTest {

    @Mock
    WorkPatternRepository repository;

    WorkPatternService sut;

    @Before
    public void setSut()
    {
        MockitoAnnotations.initMocks(this);
        sut = new WorkPatternService(repository);
    }

    @Test
    public void addPatternByRepositorySavingIt() throws ParseException
    {
        WorkPattern pattern = new WorkPattern.Builder(1)
                .withMondayHours(9)
                .withTuesdayHours(9)
                .withWednesdayHours(9)
                .withThursdayHours(9)
                .withFridayHours(4)
                .build();
        sut.add(pattern);
        verify(repository, times(1)).save(pattern);
        verifyNoMoreInteractions(repository);
    }

    @Test
    public void validateTest()
    {

    }

    @Test
    public void testIdExistsInTable()
    {

    }

    @Test
    public void deleteTest()
    {

    }
}
