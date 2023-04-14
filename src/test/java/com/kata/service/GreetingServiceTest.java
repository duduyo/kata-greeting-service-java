package com.kata.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.kata.customers.CustomerRespository;
import com.kata.notification.Mailer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

class GreetingServiceTest {

    public static final String NAME = "World";
    private CustomerRespository repositoryStub;

    private Mailer mailerMock;

    private GreetingService greetingService;

    @BeforeEach
    void setUp() {
        repositoryStub = Mockito.mock(CustomerRespository.class);
        mailerMock = Mockito.mock(Mailer.class);
        greetingService = new GreetingService(repositoryStub, mailerMock);
    }

    @Test
    void should_send_greeting_when_birthay_is_today() {
        // given
        when(repositoryStub.birthdayIsToday(NAME)).thenReturn(true);
        // when
        greetingService.greeting(NAME);
        // then
        verify(mailerMock).send("Happy birthday " + NAME + "!");
    }

    @Test
    void should_send_greeting_when_birthay_is_not_today() {
        // given
        when(repositoryStub.birthdayIsToday(NAME)).thenReturn(false);
        // when
        greetingService.greeting(NAME);
        // then
        verify(mailerMock).send("Good morning " + NAME + ".");
    }

    @Test
    void should_say_happy_birthday_when_birthday_with_captors() {
        //given
        when(repositoryStub.birthdayIsToday(NAME)).thenReturn(true);
        //when
        greetingService.greeting(NAME);
        //then
        ArgumentCaptor<String> greetingCaptor = ArgumentCaptor.forClass(String.class);
        verify(mailerMock).send(greetingCaptor.capture());
        assertThat(greetingCaptor.getValue()).isEqualTo("Happy birthday " + NAME + "!");
    }

}