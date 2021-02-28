package com.example.msghaha;

import com.example.msghaha.pojos.ChatMessage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ChatPage {

    @FindBy(id = "messageText")
    private WebElement messageTextField;

    @FindBy(id = "messageType")
    private Select messageTypeField;

    @FindBy(id = "submitMessage")
    private WebElement submitButton;

    @FindBy(className = "chatMessageUsername")
    private WebElement chatMessageUsername;

    @FindBy(className = "chatMessageText")
    private WebElement chatMessageText;

    public ChatPage(WebDriver webDriver){
        PageFactory.initElements(webDriver, this);
    }

    public void sendMessage(String text, String value){
        this.messageTextField.sendKeys(text);
        this.messageTypeField.selectByValue(value);
        this.submitButton.click();
    }

    public ChatMessage getFirstMessage(){
        ChatMessage message = new ChatMessage();
        message.setMessageText(chatMessageText.getText());
        message.setUsername(chatMessageUsername.getText());
        return message;
    }
}
