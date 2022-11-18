package advisor;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);

        String code = "";

        HttpServer server = HttpServer.create();
        server.bind(new InetSocketAddress(8080), 0);
        server.createContext("/", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                String query = exchange.getRequestURI().getQuery();
                if(query.contains("code")) {
                    code += exchange.getAttribute("code").toString();
                    String success = "Got the code. Return back to your program.";
                    exchange.sendResponseHeaders(200, success.length());
                    exchange.getResponseBody().write(success.getBytes());
                } else {
                    String fail = "Authorization code not found. Try again.";
                    exchange.sendResponseHeaders(401, fail.length());
                    exchange.getResponseBody().write(fail.getBytes());
                }
                exchange.getResponseBody().close();
            }
        });
        server.start();



        boolean isLogin = false;

        while(true) {
            String input = String.valueOf(scanner.nextLine());
            if(input.equals("new")) {
                if(isLogin) {
                    System.out.println("---NEW RELEASES---\n" +
                            "Mountains [Sia, Diplo, Labrinth]\n" +
                            "Runaway [Lil Peep]\n" +
                            "The Greatest Show [Panic! At The Disco]\n" +
                            "All Out Life [Slipknot]");
                } else {
                    System.out.println("Please, provide access for application.");
                }

            } else if(input.equals("featured")) {
                if(isLogin) {
                    System.out.println("---FEATURED---\n" +
                            "Mellow Morning\n" +
                            "Wake Up and Smell the Coffee\n" +
                            "Monday Motivation\n" +
                            "Songs to Sing in the Shower");
                } else {
                    System.out.println("Please, provide access for application.");
                }

            } else if (input.equals("categories")) {
                if(isLogin) {
                    System.out.println("---CATEGORIES---\n" +
                            "Top Lists\n" +
                            "Pop\n" +
                            "Mood\n" +
                            "Latin");
                } else {
                    System.out.println("Please, provide access for application.");
                }

            } else if(input.equals("playlists Mood")) {
                if(isLogin) {
                    System.out.println("---MOOD PLAYLISTS---\n" +
                            "Walk Like A Badass  \n" +
                            "Rage Beats  \n" +
                            "Arab Mood Booster  \n" +
                            "Sunday Stroll");
                } else {
                    System.out.println("Please, provide access for application.");
                }

            } else if (input.equals("auth")) {
                HttpClient client = HttpClient.newBuilder().build();

                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("https://accounts.spotify.com/api/token"))
                        .header("Content-Type","application/x-www-form-urlencoded")
                        .POST(HttpRequest.BodyPublishers.ofString(""))
                        .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                System.out.println(response.body());
                System.out.println("use this link to request the access code");
                System.out.println("https://accounts.spotify.com/authorize?client_id=07217a1c5fa14f79918583012a252cd6&redirect_uri=http://localhost:8080/&response_type=code");
            } else if (input.equals("exit")) {
                System.out.println("---GOODBYE!---");
                break;
            }
        }



    }
}
