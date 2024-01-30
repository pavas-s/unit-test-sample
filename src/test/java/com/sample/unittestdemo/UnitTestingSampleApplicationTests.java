package com.sample.unittestdemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class UnitTestingSampleApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testMain() {
        UnitTestingSampleApplication.main(new String[] {});
        assertTrue(true);
    }
}
