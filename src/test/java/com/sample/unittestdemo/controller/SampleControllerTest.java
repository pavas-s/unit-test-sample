package com.sample.unittestdemo.controller;

import com.sample.unittestdemo.entity.Country;
import com.sample.unittestdemo.service.SampleService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class SampleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SampleService sampleService;

    @Test
    void test_getSampleMessage() throws Exception {
        when(sampleService.getSampleMessage()).thenReturn("Sample Message from Service Layer");

        this.mockMvc.perform(get("/sample/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Sample Message from Service Layer")));
    }

    @Test
    void test_getCountriesCreatedAfterYear() throws Exception {
        when(sampleService.getCountriesCreatedAfterYear(Mockito.anyInt())).thenReturn(getCountries());

        this.mockMvc.perform(get("/sample/year/1950").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2))).andExpect(jsonPath("$[0].name", anyOf(is("France"), is("India"))));

    }

    @Test
    void test_calculate() throws Exception {
        when(sampleService.calculate(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString())).thenReturn(9);

        this.mockMvc.perform(get("/sample/calculate?operand1=4&operand2=5&operator=plus")).andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(9)));
    }

    @Test
    void test_printArgument_whenArgumentIsGreaterThanZero() throws Exception {
        doNothing().when(sampleService).printArgument(Mockito.anyInt());

        this.mockMvc.perform(get("/sample/4")).andExpect(status().isOk());
    }

    public Country getCountry1(){
        return new Country(2, "France", 1958);
    }
    public Country getCountry2(){
        return new Country(6, "India", 1947);
    }

    public Set<Country> getCountries(){
        return Set.of(getCountry1(), getCountry2());
    }
}
