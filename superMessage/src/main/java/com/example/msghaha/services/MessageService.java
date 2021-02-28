package com.example.msghaha.services;

import com.example.msghaha.mappers.MessageMapper;
import com.example.msghaha.pojos.ChatForm;
import com.example.msghaha.pojos.ChatMessage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MessageService {

    @Resource
    private MessageMapper messageMapper;
    public List<ChatMessage> getMessages() {
        return messageMapper.getAllMessages();
    }

    public void addMessage(ChatForm chatForm) {
        ChatMessage newMessage = new ChatMessage();
        newMessage.setUsername(chatForm.getUserName());
        switch (chatForm.getMessageType()) {
            case "Say":
                newMessage.setMessageText(chatForm.getMessageText());
                break;
            case "Shout":
                newMessage.setMessageText(chatForm.getMessageText().toUpperCase());
                break;
            case "Whisper":
                newMessage.setMessageText(chatForm.getMessageText().toLowerCase());
                break;
        }
        messageMapper.addMessage(newMessage);
    }
}
