package server;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Session extends Thread{
    private final Socket socket;

    private Boolean closeServer = false;

    public Session(Socket socketForClient) {
        this.socket = socketForClient;
    }

    public void run() {
        try (
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        ){

            String receivedMsg = input.readUTF();
            String[] parts = receivedMsg.split("-");
            String response = "";

            if(parts[0].equals("exit")) {
                response += "exit";
                System.out.println("Received cmd: " + receivedMsg);
                output.writeUTF(response);
                System.out.println("Sent: "+response);
                this.setCloseServer(true);
            } else {
                this.setCloseServer(false);
                String directory = "D:\\HyperSkill\\File Server\\File Server\\task\\src\\server\\data\\";

                directory += parts[1];
                File file = new File(directory);

                if(parts[0].equals("GET")) {
                    if(file.exists()) {
                        response += "The content of the file is: ";

                        Scanner scanner = new Scanner(file);
                        while (scanner.hasNext()) {
                            response += scanner.nextLine();
                        }
                    } else {
                        response += "The response says that the file was not found!";
                    }
                } else if(parts[0].equals("PUT")) {
                    if(file.exists()) {
                        response += "The response says that creating the file was forbidden!";
                    } else {
                        PrintWriter writer = new PrintWriter(file);
                        writer.println(parts[2]);
                        writer.flush();
                        response += "The response says that the file was created!";
                    }
                } else if (parts[0].equals("DELETE")) {
                    if(file.exists()) {
                        if (file.delete()) {
                            response += "The response says that the file was successfully deleted!";
                        }
                    } else {
                        response += "The response says that the file was not found!";
                    }
                }

                System.out.println("Received cmd: " + receivedMsg);
                output.writeUTF(response);
                System.out.println("Sent: "+response);
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Boolean getCloseServer() {
        return closeServer;
    }

    public void setCloseServer(Boolean closeServer) {
        this.closeServer = closeServer;
    }
}
