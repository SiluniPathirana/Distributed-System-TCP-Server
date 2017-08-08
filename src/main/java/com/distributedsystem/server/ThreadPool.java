package com.distributedsystem.server;

/*
Thread pool class create thread executor instances
enqueue task to the queue
 */

public class ThreadPool {

    private ThreadStoreQueue<Runnable> queue;

    public ThreadPool(int queueSize, int nThread) {
        queue = new ThreadStoreQueue(queueSize);

        for (int count = 0; count < nThread; count++) {
            String threadName = "Thread-" + count;
            ThreadExecutor task = new ThreadExecutor(queue);
            Thread thread = new Thread(task, threadName);
            thread.start();
        }
    }

    /*
    method to submit new task to the queue
     */
    public void submitTask(Runnable task) throws InterruptedException {
        queue.enqueue(task);
    }

}

