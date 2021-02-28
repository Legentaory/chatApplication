package com.example.msghaha.controllers;

import com.example.msghaha.pojos.ChatForm;
import com.example.msghaha.services.MessageService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/chat")
public class ChatController {

    @Resource
    private MessageService messageService;

    @GetMapping
    public String getChatPage(ChatForm chatForm, Model model){
        model.addAttribute("chatMessages", messageService.getMessages());
        return "chat";
    }

    @PostMapping
    public String postChatPage(Authentication authentication, ChatForm chatForm, Model model){
        chatForm.setUserName(authentication.getName());
        messageService.addMessage(chatForm);
        chatForm.setMessageText("");
        model.addAttribute("chatMessages", messageService.getMessages());
        return "chat";
    }

    @ModelAttribute("allMessageTypes")
    public String[] allTypes(){
        return new String[]{"Say", "Shout", "Whisper"};
    }

}
