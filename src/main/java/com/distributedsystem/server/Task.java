package com.distributedsystem.server;

import java.util.logging.Level;
import java.util.logging.Logger;

/*
implementation of the task to be submitted to thread pool.
 */
public class Task implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(Task.class.getName());
    private String number;

    public Task(String taskNumber) {
        this.number = taskNumber;
    }

    public void run() {
        System.out.println("Start executing of task number :" + number);
        try {
            //Simulating processing time
            //perform tasks
            Thread.sleep(500);
        } catch (InterruptedException exception) {
            LOGGER.log(Level.SEVERE, exception.toString(), exception);
        }
        System.out.println("End executing of task number :" + number);
    }
}
