package com.sample.unittestdemo.service;

import com.sample.unittestdemo.entity.Country;
import com.sample.unittestdemo.repository.SampleRepository;
import com.sample.unittestdemo.service.impl.SampleServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledForJreRange;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.condition.JRE.JAVA_11;
import static org.junit.jupiter.api.condition.JRE.JAVA_8;
import static org.junit.jupiter.api.condition.OS.LINUX;
import static org.junit.jupiter.api.condition.OS.WINDOWS;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class SampleServiceTest {

    @Mock
    SampleRepository sampleRepository;

    @InjectMocks
    SampleServiceImpl sampleService;

    @BeforeAll
    public static void init() {
        System.out.println("This method runs once before all unit test in this class.");
    }

    @BeforeEach
    public void doEach(){
        System.out.println("This method runs before every unit test in this class.");
    }

    @AfterAll
    public static void finish() {
        System.out.println("This method runs once after all unit test are executed in this class.");
    }

    @AfterEach
    public void doAfterEach() {
        System.out.println("This method runs after every unit test in this class.");
    }

    @Test
//    @DisplayName("Sample Test")
    public void test_getSampleMessage()
    {
        String sampleMessage =  sampleService.getSampleMessage();

        Assertions.assertNotNull(sampleMessage, "The Sample message from service layer is not null");
        Assertions.assertEquals(sampleMessage, "Sample Message from Service Layer");
    }

    @Test
//    @DisplayName("😱")
    public void test_getSampleMessageFromRepository(){
        given(sampleRepository.findCountriesByYearSinceActiveAfter(Mockito.anyInt())).willReturn(getCountries());
        Set<Country> countryList = sampleService.getCountriesCreatedAfterYear(10);

        Assertions.assertNotNull(countryList);
        Assertions.assertEquals(countryList.size(), 2);
        Assertions.assertIterableEquals(countryList, getCountries());
    }

    @Test
    @DisplayName("╯°□°）╯")
    public void test_calculate_whenOperationIsAddition(){
        int result = sampleService.calculate(3,6, "plus");

        Assertions.assertEquals(result, 9);
    }

    @Test
    public void test_calculate_whenOperationIsSubstraction(){
        int result = sampleService.calculate(3,6, "minus");

        Assertions.assertEquals(result, -3);
    }

    @Test
    public void test_printArgument(){
        sampleService.printArgument(6);
    }

    @Test
    @Disabled
    public void test_calculate_whenOperationIsUnknown(){
        int result = sampleService.calculate(3,6, "unk");

        Assertions.assertEquals(result, -1);
    }

    @Test
    @Disabled("Disabled until bug has been resolved")
    public void test_printArgument_whenArgumentIsLessThanZero()
    {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> sampleService.printArgument(-6));

        String expectedMessage = "Argument value should be greater than 0";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisabledOnOs(OS.MAC)
    void notOnMac() {
        //some unit test
    }

    @Test
    @DisabledForJreRange(min = JAVA_8, max = JAVA_11)
    void notFromJava8to11() {
        // ...
    }

    @Test
    @EnabledOnOs({ LINUX, WINDOWS })
    void onLinuxOrWindows() {
        // ...
    }

    @Test
    public void testBooleanAssumption() {
        Country country = getCountry1();

        assumeTrue(System.getProperty("os.name").contains("Windows"));
        assertEquals(country.getName(), "France");
        assertThat(country.getName(), equalToIgnoringCase("France"));
    }

    @Test
    void testOnlyOnDeveloperWorkstation() {
        assumeTrue("DEV".equals(System.getenv("ENV")),
                () -> "Aborting test: not on developer workstation");
        // unit test
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
