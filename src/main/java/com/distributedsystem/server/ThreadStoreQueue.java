package com.distributedsystem.server;

import java.util.LinkedList;
import java.util.Queue;

/*
implementation of queue to store tasks
 */
public class ThreadStoreQueue<Type> {
    private Queue<Type> queue = new LinkedList<Type>();
    private int queusize = -1;

    public ThreadStoreQueue(int size) {
        this.queusize = size;
    }

    /*
    insert threads to the queue
    check queue is empty notify all threads and add threads to the queue
    queue is full wait until queue is free
     */
    public synchronized void enqueue(Type task) throws InterruptedException {

        while (this.queue.size() == this.queusize) {
            wait();
        }
        if (this.queue.size() == 0) {
            notifyAll();
        }
        this.queue.offer(task);
    }
/*
remove threads from the queue
check queue is empty wait to retreive threads ,if queue is full retrieve threads from the queue
queue
 */

    public synchronized Type dequeue() throws InterruptedException {

        while (this.queue.size() == 0) {
            wait();
        }
        if (this.queue.size() == this.queusize) {
            notifyAll();
        }
        return this.queue.poll();
    }
}