//Name: Robert Johnson
//UA ID: 010796992
//Assignment 4: Producer-Consumer Problem 

import java.util.Queue;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

class ProducerConsumer{

    public static void main (String[] args){
        // User input
        long seconds = Long.parseLong(args[0]);
        long nProducers = Long.parseLong(args[1]);
        long nConsumers = Long.parseLong(args[2]);
        System.out.println("Sleep time: " + seconds);
		System.out.println("Producer threads: " + nProducers);
		System.out.println("Consumer threads: " + nConsumers);
    
        // Initialize buffer
		Semaphore mutex = new Semaphore(1);
        Semaphore empty = new Semaphore(0);
        Semaphore full = new Semaphore(5);
        Queue<Long> buffer = new LinkedList<Long>();

        // Create producers
        for(int i = 0; i < nProducers; i++){
            Producer new_producer = new Producer(mutex, empty, full, buffer);
            new_producer.start();
        }
        
        // Create consumers
        for(int i = 0; i < nConsumers; i++){
            Consumer new_consumer = new Consumer(mutex, empty, full, buffer);
            new_consumer.start();
        }

        // Sleep for inputted seconds
        try{
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        // Exit program
        System.exit(0);
    }
}