package com.sample.unittestdemo.service.impl;

import com.sample.unittestdemo.entity.Country;
import com.sample.unittestdemo.repository.SampleRepository;
import com.sample.unittestdemo.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public class SampleServiceImpl implements SampleService {

    private SampleRepository sampleRepository;

    @Autowired
    public SampleServiceImpl(SampleRepository sampleRepository) {
        this.sampleRepository = sampleRepository;
    }

    @Override
    public String getSampleMessage() {
        return "Sample Message from Service Layer";
    }

    @Override
    public Set<Country> getCountriesCreatedAfterYear(int year) {
        return sampleRepository.findCountriesByYearSinceActiveAfter(year);
    }

    @Override
    public Integer calculate(int operand1, int operand2, String operator) {
        Integer result = getResult(operand1, operand2, operator);
        if (result != null) return result;
        return -1;

    }

    private static Integer getResult(int operand1, int operand2, String operator) {
        if(operator.equalsIgnoreCase("plus"))
            return operand1 + operand2;
        if(operator.equalsIgnoreCase("minus"))
            return operand1 - operand2;
        return null;
    }

    @Override
    public void printArgument(int argument){
        if(argument > 0)
            System.out.printf("Argument: %d%n", argument);
        else
            throw new IllegalArgumentException("Argument value should be greater than 0");
    }

}
