package com.wipro.test;

import com.wipro.oop.Circle;
import com.wipro.oop.Rectangle;
import com.wipro.oop.Shape;
import com.wipro.oop.Triangle;

public class ShapeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Shape sp = new Circle(0, 40.0,120,false,15,20);
		System.out.println(sp);
		
		Rectangle rt = new Rectangle(4, 50, 25, true, 10, 20);
		System.out.println(rt);
		
		Triangle t = new Triangle(3, 150, 20, false, "RightAngleTriangle");
		System.out.println(t);
	}

}
