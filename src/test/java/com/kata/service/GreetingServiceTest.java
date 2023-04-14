package com.kata.service;

import static org.junit.jupiter.api.Assertions.*;

import com.kata.customers.CustomerRespository;
import com.kata.notification.Mailer;
import org.junit.jupiter.api.Test;

class GreetingServiceTest {

    class MailerSpy extends Mailer {
        public String greetingValue = "";

        @Override
        public void send(String greeting) {
            greetingValue = greeting;
        }
    }

    @Test
    void should_send_greeting_when_birthay_is_today() {
        // given
        CustomerRespository customerRespositoryStub = createCustomerRespositoryStub(true);
        MailerSpy mailerSpy = new MailerSpy();
        GreetingService greetingService = new GreetingService(customerRespositoryStub, mailerSpy);
        // when
        greetingService.greeting("World");
        // then
        assertEquals("Happy birthday World!", mailerSpy.greetingValue);
    }

    @Test
    void should_send_greeting_when_birthay_is_not_today() {
        // given
        CustomerRespository customerRespositoryStub = createCustomerRespositoryStub(false);
        MailerSpy mailerSpy = new MailerSpy();
        GreetingService greetingService = new GreetingService(customerRespositoryStub, mailerSpy);
        // when
        greetingService.greeting("World");
        // then
        assertEquals("Good morning World.", mailerSpy.greetingValue);
    }

    private static CustomerRespository createCustomerRespositoryStub(boolean isBirthdayToday) {
        return new CustomerRespository() {
            @Override
            public boolean birthdayIsToday(String name) {
                return isBirthdayToday;
            }
        };
    }
}