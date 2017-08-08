package com.distributedsystem.server;

/*
implementation of thread executor class
which  dequeu tasks from queue and  execute the tasks
 */
public class ThreadExecutor implements Runnable {
    private ThreadStoreQueue<Runnable> queue;

    public ThreadExecutor(ThreadStoreQueue<Runnable> threadQueue) {
        this.queue = threadQueue;
    }


    public void run() {
        try {
            while (true) {
                String name = Thread.currentThread().getName();
                Runnable task = queue.dequeue();
                System.out.println("Task Started by Thread :" + name);
                task.run();
                System.out.println("Task Finished by Thread :" + name);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
