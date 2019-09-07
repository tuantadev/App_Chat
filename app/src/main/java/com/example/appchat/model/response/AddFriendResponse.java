package com.example.appchat.model.response;

public class AddFriendResponse {

    private int sender_id;
    private int receive_id;

    public int getSender_id() {
        return sender_id;
    }

    public void setSender_id(int sender_id) {
        this.sender_id = sender_id;
    }

    public int getReceiver_id() {
        return receive_id;
    }

    public void setReceiver_id(int receiver_id) {
        this.receive_id = receiver_id;
    }

}
