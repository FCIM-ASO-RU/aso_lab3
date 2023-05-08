package aso_lab3;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Store {
    private int messageCount = 0;

    private final Queue<Integer> queue = new LinkedList<>();
    private final Semaphore producerSemaphore = new Semaphore(1, true);
    private final Semaphore consumerSemaphore = new Semaphore(1, true);

    public int getMessageCount() {
        return messageCount;
    }

    public boolean isQueueEmpty() {
        return queue.isEmpty();
    }

    public void push(int n) {
        try {
            producerSemaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (queue.size() == 5) {
            System.out.println("Stock is full");
            producerSemaphore.release();
        }

        if (queue.size() < 5) {
            queue.add(n);
            messageCount++;
            System.out.println("new message count:" + messageCount);
            producerSemaphore.release();
        }
    }

    public Optional<Integer> consume() {
        try {
            consumerSemaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (isQueueEmpty()) {
            System.out.println("Stock is empty");
            consumerSemaphore.release();

            return Optional.empty();
        } else {
            Integer tempInt = queue.remove();
            consumerSemaphore.release();

            return Optional.of(tempInt);
        }
    }
}
