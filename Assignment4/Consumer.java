//Name: Robert Johnson
//UA ID: 010796992
//Assignment 4: Producer-Consumer Problem

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.Queue;

class Consumer extends Thread{
    
    Semaphore mutex;
    Semaphore empty;    
    Semaphore full;
	Queue<Long> buffer;

    public Consumer(Semaphore mutex, Semaphore empty, Semaphore full, Queue<Long> buffer){
        
        this.mutex = mutex;
        this.empty = empty;
        this.full = full;
		this.buffer = buffer;
		
    }

    public void run(){
        int i = 0;
        while(i < 100){
            // Sleep time
            Random rand = new Random();
            long sleep_time = rand.nextInt(500);

            try{
                Thread.sleep(sleep_time);
            } catch(InterruptedException e){
                e.printStackTrace();
            }

            // Consume if possible
            try{
                empty.acquire();
                mutex.acquire();

                long consumed = buffer.remove();
                System.out.println("Consumed: " + consumed);
                  
            } catch (InterruptedException e){
                System.out.println("Consume not possible.");
            }

            full.release();
            mutex.release();

            i++;
        }
    }
}