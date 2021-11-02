//Name: Robert Johnson
//UA ID: 010796992
//Assignment 4: Producer-Consumer Problem

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.Queue;

class Producer extends Thread{
	
	Semaphore mutex;
	Semaphore empty;
    Semaphore full;
	Queue<Long> buffer;

    public Producer(Semaphore mutex, Semaphore empty, Semaphore full, Queue<Long> buffer){
		
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

            // Produce if possible
            try{
                full.acquire();
                mutex.acquire();

                int produced = rand.nextInt(100000);
                buffer.add((long)produced);    
                System.out.println("Produced: " + produced); 

            } catch (InterruptedException e){
                System.out.println("Produce not possible.");
            }

            empty.release();
            mutex.release();

            i++;
        }
    }
}