package com.example.msghaha;

import com.example.msghaha.pojos.ChatMessage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ChatMessageAppBehaviorTest {

    @LocalServerPort
    public int port;

    public static WebDriver webDriver;

    public String baseURL;

    @BeforeAll
    public static void beforeAll(){
        WebDriverManager.edgedriver().setup();
        webDriver = new EdgeDriver();
    }

    @AfterAll
    public static void AfterAll(){
        webDriver.quit();
        webDriver = null;
    }

    @BeforeEach
    public void beforeEach() {
        baseURL = "http://localhost:" + port;
    }

    @Test
    public void testUserSignupLoginAndSubmitMessage(){

        String userName = "ShuaiBi";
        String firstName = "Shuai";
        String lastName = "Bi";
        String password = "taitamashuai";
        String way = "Shout";
        String text = "I am so shuai";

        webDriver.get(baseURL + "/signup");
        SignupPage signupPage = new SignupPage(webDriver);
        signupPage.signup(firstName, lastName, userName, password);

        webDriver.get(baseURL + "/login");
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login(userName, password);

        ChatPage chatPage = new ChatPage(webDriver);
        chatPage.sendMessage(text, way);
        ChatMessage chatMessage = chatPage.getFirstMessage();

        assert userName.equals(chatMessage.getUsername());
        assert text.equals(chatMessage.getMessageText());
    }
}
