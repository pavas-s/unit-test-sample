package com.sample.unittestdemo;

import com.sample.unittestdemo.controller.SampleController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SmokeTest {

    @Autowired
    private SampleController controller;

    @Test
    void contextLoads() throws Exception {
        Assertions.assertNotNull(controller);
    }
}