package com.example.appchat.socket;

import android.util.Log;
import com.example.appchat.interact.CommonData;
import com.example.appchat.interact.Constant;
import com.example.appchat.model.response.MessageChatResponse;
import com.google.gson.Gson;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class SocketManager {

    private static final String TAG = "SocketManager";
    private io.socket.client.Socket socket;
    private List<ReceiverMess> receiverMesses = new ArrayList<>();
    private static SocketManager instance;

    public static SocketManager getInstance() {
        if (instance == null) {
            instance = new SocketManager();
        }
        return instance;
    }

    private SocketManager() {

    }

    public void disconnect() {
        if (socket != null) {
            socket.disconnect();
            socket = null;
        }
    }

    public void connect() {
        try {
            socket = IO.socket(Constant.socket_duphong);
            socket.on(io.socket.client.Socket.EVENT_CONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    Log.d(TAG, "EVENT_CONNECT: " + args);
                    socket.emit("connected",
                            CommonData.getInstance()
                                    .getUserProfile()
                                    .getId() + "");
                }
            });
            socket.on(io.socket.client.Socket.EVENT_DISCONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    Log.d(TAG, "EVENT_DISCONNECT: " + args);
                }
            });
            socket.on(Socket.EVENT_CONNECT_ERROR, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    Log.d(TAG, "EVENT_CONNECT_ERROR: " + args);
                }
            });

            socket.on("message", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    receiveMessage((String) args[0]);
                }
            });
            socket.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void receiveMessage(String content) {
        MessageChatResponse messageChatResponse = new Gson().fromJson(content,MessageChatResponse.class);
        for(ReceiverMess receiverMess : receiverMesses){
            receiverMess.receieve(messageChatResponse);
        }
    }

    public void sendMessage(String toJson) {
        if ( socket != null){
            socket.emit("message", toJson);
        }
    }

    public void register(ReceiverMess reciverMessage){
        receiverMesses.add(reciverMessage);
    }

    public void unregister(ReceiverMess reciverMessage){
        receiverMesses.remove(reciverMessage);
    }
}
