package com.sample.unittestdemo.service;

import com.sample.unittestdemo.entity.Country;

import java.util.Set;


public interface SampleService {
    String getSampleMessage();

    Set<Country> getCountriesCreatedAfterYear(int year);

    Integer calculate(int operand1, int operand2, String operator);

    void printArgument(int argument);
}
