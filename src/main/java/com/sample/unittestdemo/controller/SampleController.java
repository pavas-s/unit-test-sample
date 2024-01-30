package com.sample.unittestdemo.controller;

import com.sample.unittestdemo.entity.Country;
import com.sample.unittestdemo.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/sample")
public class SampleController {

    private final SampleService sampleService;

    @Autowired
    public SampleController(SampleService sampleService) {
        this.sampleService = sampleService;
    }

    @GetMapping("/")
    public String getSampleMessage(){
        return sampleService.getSampleMessage();
    }

    @GetMapping("/year/{year}")
    public ResponseEntity<Set<Country>> getCountriesCreatedAfterYear(@PathVariable int year){
        return new ResponseEntity<>(sampleService.getCountriesCreatedAfterYear(year), HttpStatus.OK);
    }

    @GetMapping("/calculate")
    public ResponseEntity<Integer> calculate(@RequestParam int operand1, @RequestParam int operand2, @RequestParam String operator)
    {
        return new ResponseEntity<>(sampleService.calculate(operand1, operand2, operator), HttpStatus.OK);
    }

    @GetMapping("/{argument}")
    public ResponseEntity printArgument(@PathVariable int argument){
        sampleService.printArgument(argument);
        return new ResponseEntity(HttpStatus.OK);
    }
}
