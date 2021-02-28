package com.example.msghaha;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.boot.test.context.SpringBootTest;

public class SignupPage {

    @FindBy(id = "inputFirstName")
    private WebElement firstNameField;

    @FindBy(id = "inputLastName")
    private WebElement lastNameField;

    @FindBy(id = "inputUsername")
    private WebElement userNameField;

    @FindBy(id = "inputPassword")
    private WebElement passwordField;

    @FindBy(id = "submit-button")
    private WebElement submitButtonField;

    public SignupPage(WebDriver webDriver){
        PageFactory.initElements(webDriver, this);
    }

    public void signup(String firstName, String lastName, String userName, String password){
        this.firstNameField.sendKeys(firstName);
        this.lastNameField.sendKeys(lastName);
        this.userNameField.sendKeys(userName);
        this.passwordField.sendKeys(password);
        this.submitButtonField.click();
    }
}
