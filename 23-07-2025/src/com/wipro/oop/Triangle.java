package com.wipro.oop;

public class Triangle extends Shape {
	
	String typeOfTriangle;

	public Triangle(int noOfSides, double area, double perimeter, boolean isQuadrilateral, String typeOfTriangle) {
		super(noOfSides, area, perimeter, isQuadrilateral);
		this.typeOfTriangle = typeOfTriangle;
	}

	public String getTypeOfTriangle() {
		return typeOfTriangle;
	}

	public void setTypeOfTriangle(String typeOfTriangle) {
		this.typeOfTriangle = typeOfTriangle;
	}

	@Override
	public String toString() {
		return "Triangle [typeOfTriangle=" + typeOfTriangle + ", noOfSides=" + noOfSides + ", area=" + area
				+ ", perimeter=" + perimeter + ", isQuadrilateral=" + isQuadrilateral + "]";
	}
}
