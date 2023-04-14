package com.kata.service;

import static org.junit.jupiter.api.Assertions.*;

import com.kata.customers.CustomerRespository;
import com.kata.notification.Mailer;
import org.junit.jupiter.api.Test;

class GreetingServiceTest {

    @Test
    void should_send_greeting_when_birthay_is_today() {
        CustomerRespository customerRespositoryStub = createCustomerRespositoryStub(true);
        GreetingService greetingService = new GreetingService(customerRespositoryStub, new Mailer());
        greetingService.greeting("World");
    }

    @Test
    void should_send_greeting_when_birthay_is_not_today() {
        CustomerRespository customerRespositoryStub = createCustomerRespositoryStub(false);
        GreetingService greetingService = new GreetingService(customerRespositoryStub, new Mailer());
        greetingService.greeting("World");
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