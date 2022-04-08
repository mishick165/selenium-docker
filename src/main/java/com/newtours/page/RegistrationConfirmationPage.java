package com.newtours.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationConfirmationPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(partialLinkText = "sign-in")
    private WebElement signingLink;

    @FindBy(id="flight-link")
    private WebElement flightsLink;

    public RegistrationConfirmationPage (WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
    }

    public void goToFlightDetailsPage () {
        this.wait.until(ExpectedConditions.visibilityOf(this.signingLink));
        this.flightsLink.click();
    }


}
