package com.wipro.oop;

public class Circle extends Shape{
	
	int radius;
	int diameter;
	
	public Circle(int noOfSides, double area, double perimeter, boolean isQuadrilateral, int radius, int diameter) {
		super(noOfSides, area, perimeter, isQuadrilateral);
		this.radius = radius;
		this.diameter = diameter;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public int getDiameter() {
		return diameter;
	}

	public void setDiameter(int diameter) {
		this.diameter = diameter;
	}

	@Override
	public String toString() {
		return "Circle [radius=" + radius + ", diameter=" + diameter + ", noOfSides=" + noOfSides + ", area=" + area
				+ ", perimeter=" + perimeter + ", isQuadrilateral=" + isQuadrilateral + "]";
	}
}
