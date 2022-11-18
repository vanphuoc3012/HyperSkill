package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static int PORT = 26345;

    private static String SERVER_ADDRESS = "127.0.0.1";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Client started!");
        boolean exit = false;
        while (!exit) {
            try (
                    Socket socket = new Socket(SERVER_ADDRESS, PORT);
                    DataInputStream input = new DataInputStream(socket.getInputStream());
                    DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            ) {
                    System.out.println("Enter action (1 - get a file, 2 - create a file, 3 - delete a file):");
                    String userInput = String.valueOf(scanner.nextLine());
                String request = "";

                    if(userInput.equals("exit")) {
                        request += "exit";
                        exit = true;
                    } else if(userInput.equals("1")) {
                        System.out.print("Enter filename: ");
                        String fileName = String.valueOf(scanner.nextLine());
                        request += "GET-"+fileName;
                    } else if (userInput.equals("2")) {
                        System.out.print("Enter filename: ");
                        String fileName = String.valueOf(scanner.nextLine());
                        System.out.println();
                        System.out.print("Enter file content: ");
                        String content = String.valueOf(scanner.nextLine());
                        request += "PUT-" +fileName+"-"+content;
                    } else if (userInput.equals("3")){
                        System.out.print("Enter filename: ");
                        String fileName = String.valueOf(scanner.nextLine());
                        request += "DELETE-" +fileName;
                    }

                    output.writeUTF(request);
                    System.out.println("The request was sent");
                    String serverResp = input.readUTF();
                    if(serverResp.equals("exit")) {
                        socket.close();
                        break;
                    }
                    System.out.println(serverResp);
                    System.out.println();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

}
