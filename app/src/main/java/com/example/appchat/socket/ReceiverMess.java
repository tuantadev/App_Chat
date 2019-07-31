package com.example.appchat.socket;

import com.example.appchat.model.response.MessageChatResponse;

public interface ReceiverMess {
    void receieve(MessageChatResponse response);
}
