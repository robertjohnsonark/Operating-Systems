This program is a solution to the Producer-Consumer Problem using semaphores and works as following:


	The main class: ProducerConsumer.java
     		The input is read in from the user in the following order: sleep time, number of producers, number of consumers
   		The buffer and semaphores for the buffer: empty, full, mutex are initializ
		A for loop that creates the inputted number of producers
		A for loop that creates the inputted number of consumers
		Sleeps for inputtted number of seconds
		Exits
		
	The Producer class: Producer.java
	 	A loop that runs 100 times and chooses a random time (between 0 and 0.5 seconds) to sleep
        	Sleeps, wakes up, and tries to get resources by calling full.acquire() which gets a permit from the full semaphore
            	and calls mutex.acquire() which gets permit to add to the buffer
        	If both of these resources are granted, then a random number (between 0-100,000) is chosen and added to the queue
        	After adding to the queue, empty.release() is called to add one to the empty semaphore
		Now it can call mutex.release() to allow other processes to access the buffer.

	The Consumer class: Producer.java
		A loop that runs 100 times and chooses a random time (between 0 and 0.5 seconds) to sleep
        	Sleeps, wakes up, tries to get resources by calling empty.acquire() which gets a permit from the empty semaphore  
            	and calls mutex.acquire() which gets a permit to add to the buffer
        	If both of these resources are granted, then the random number (between 0-100,000) is consumed from the queue
        	After consuming from the queue, full.release() is called to add one to the full semaphore
            	Now it can call mutex.release() to allow other processes to access the buffer.