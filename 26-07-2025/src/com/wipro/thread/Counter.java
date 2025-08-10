package com.wipro.thread;

public class Counter {

	int count = 0;

	public Counter() {
		// TODO Auto-generated constructor stub
	}

	public synchronized void increment() {
        count++;
    }

	public Counter(int count) {
		super();
		this.count = count;
	}

	public int getCounter() {
		return count;
	}


	public void setCounter(int count) {
		this.count = count;
	}
}
