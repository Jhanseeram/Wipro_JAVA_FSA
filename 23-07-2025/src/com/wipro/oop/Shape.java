package com.wipro.oop;

public class Shape {
	
	int noOfSides;
	double area;
	double perimeter;
	boolean isQuadrilateral;
	
	public Shape(int noOfSides, double area, double perimeter, boolean isQuadrilateral) {
		super();
		this.noOfSides = noOfSides;
		this.area = area;
		this.perimeter = perimeter;
		this.isQuadrilateral = isQuadrilateral;
	}
	
	public Shape() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getNoOfSides() {
		return noOfSides;
	}
	public void setNoOfSides(int noOfSides) {
		this.noOfSides = noOfSides;
	}
	public double getArea() {
		return area;
	}
	public void setArea(double area) {
		this.area = area;
	}
	public double getPerimeter() {
		return perimeter;
	}
	public void setPerimeter(double perimeter) {
		this.perimeter = perimeter;
	}
	public boolean isQuadrilateral() {
		return isQuadrilateral;
	}
	public void setQuadrilateral(boolean isQuadrilateral) {
		this.isQuadrilateral = isQuadrilateral;
	}
	
	@Override
	public String toString() {
		return "Shape [noOfSides=" + noOfSides + ", area=" + area + ", perimeter=" + perimeter + ", isQuadrilateral="
				+ isQuadrilateral + "]";
	}
	
	
}
