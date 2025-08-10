package com.Wipro.oops;

public class Box<T> 
{
    private T item;

    public void add(T item) {
        this.item = item;
    }
   
    public T getItem() {
		return item;
	}

	public void setItem(T item) {
		this.item = item;
	}
}
