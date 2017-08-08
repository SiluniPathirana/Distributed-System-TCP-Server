package com.distributedsystem.server;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
/*
TCP Server implementation accept multiple client requests
 */

public class TCPServerMain {

    private static Socket socket;

    public static void main(String[] args) {

        //get user inputs to initialize thread pool
        Scanner scanner = new Scanner(System.in, "UTF-8");
        System.out.println("Enter no of threads in thread pool");
        int noOfThreads = scanner.nextInt();
        System.out.println("Enter maximum no of task handle by queue");
        int maxNoOfTaskInQueue = scanner.nextInt();
        ThreadPool threadPool = new ThreadPool(noOfThreads, maxNoOfTaskInQueue);


        try {

            int port = 25000;
            System.out.println("Server Started and listening to the port" + port);
            ServerSocket serverSocket = new ServerSocket(port);

            //Server is running always. This is done using this while(true) loop
            //Reading the message from the client

            while (true) {
                try {

                    socket = serverSocket.accept();
                    System.out.println("Server reading messages" + port);
                    InputStream inputStream = socket.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String message = bufferedReader.readLine();
                    System.out.println("Server reading messages : " + message);

                    Task task = new Task(message);
                    threadPool.submitTask(task);


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}