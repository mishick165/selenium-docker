package com.newtours.test;

import com.newtours.page.*;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import tests.BaseTest;

public class BookFlightTest extends BaseTest {

    private String noOfPassengers;
    private String expectedPrice;

    @BeforeTest
    @Parameters({"noOfPassengers","expectedPrice"})
    public void setupParameters (String noOfPassengers, String expectedPrice){
        this.noOfPassengers = noOfPassengers;
        this.expectedPrice = expectedPrice;
    }

    @Test
    public void registrationPageTest(){
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.goTo();
        registrationPage.enterUserDetails("Selenium","Docker");
        registrationPage.enterUserCredentials("udemy","docker");
        registrationPage.submit();
    }

    @Test (dependsOnMethods = "registrationPageTest")
    public void registrationConfirmationPageTest(){
        RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver);
        registrationConfirmationPage.goToFlightDetailsPage();

    }

    @Test (dependsOnMethods = "registrationConfirmationPageTest")
    public void FlightDetailsPageTest(){
        FlightDetailsPage flightDetailsPage = new FlightDetailsPage(driver);
        //should be parameterized
        flightDetailsPage.selectPassengers(noOfPassengers);
        flightDetailsPage.goToFindFlightsPage();

    }

    @Test (dependsOnMethods = "FlightDetailsPageTest")
    public void FindFlightPageTest(){
        FindFlightPage findFlightPage = new FindFlightPage(driver);
        findFlightPage.submitFindFlightPage();
        findFlightPage.goToFLightConfirmationPage();
    }

    @Test (dependsOnMethods = "FindFlightPageTest")
    public void FlightConfirmationPageTest(){
        FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(driver);
        String actualPrice = flightConfirmationPage.getPrice();
        Assert.assertEquals(actualPrice,expectedPrice);
    }


}
