package com.example.appchat.socket;

import com.example.appchat.interact.Constant;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketIO {
    private static  String CHAT_SERVER_URL = "https://socket-io-chat.now.sh/";

    private void  openSocket() {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            Socket socket = serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
