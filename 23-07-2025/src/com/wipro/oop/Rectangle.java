package com.wipro.oop;

public class Rectangle extends Shape{

	int length;
	int breadth;
	
	public Rectangle(int noOfSides, double area, double perimeter, boolean isQuadrilateral, int length, int breadth) {
		super(noOfSides, area, perimeter, isQuadrilateral);
		this.length = length;
		this.breadth = breadth;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getBreadth() {
		return breadth;
	}

	public void setBreadth(int breadth) {
		this.breadth = breadth;
	}

	@Override
	public String toString() {
		return "Rectangle [length=" + length + ", breadth=" + breadth + ", noOfSides=" + noOfSides + ", area=" + area
				+ ", perimeter=" + perimeter + ", isQuadrilateral=" + isQuadrilateral + "]";
	}
	
	
}
