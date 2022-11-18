package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static final int PORT = 26345;

    public static void main(String[] args) {
        MyServer myServer = new MyServer(PORT);

        myServer.start();
    }


}