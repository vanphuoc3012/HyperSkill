package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MyServer {

    private int PORT;

    public MyServer(int PORT) {
        this.PORT = PORT;
    }

    public void start() {
        System.out.println("Server started!");
        try (
                ServerSocket server = new ServerSocket(PORT)) {
            while (true) {
                Session session = new Session(server.accept());
                Boolean clientSendStop = session.getCloseServer();
                session.start();
                if(clientSendStop) {
                    server.close();
                    break;
                }
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }



}
