package com.kata.service;

import com.kata.customers.CustomerRespository;
import com.kata.notification.Mailer;

class GreetingService {

    private final CustomerRespository customerRepository;

    private final Mailer mailer;

    GreetingService(CustomerRespository customerRespository, Mailer mailer) {
        this.customerRepository = customerRespository;
        this.mailer = mailer;
    }

    void greeting(String customerName) {
        String greeting;

        if (customerRepository.birthdayIsToday(customerName)) {
            greeting = "Happy birthday " + customerName + "!";
        } else {
            greeting = "Good morning " + customerName + ".";
        }

        mailer.send(greeting);
    }


}
