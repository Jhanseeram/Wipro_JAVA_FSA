package Test;

import com.Wipro.oops.Box;

public class BoxTest {
	
public static void main(String[] args) {
        
        Box<Integer> intBox = new Box<>();
        intBox.add(06);
        System.out.println("Int: " + intBox.getItem());

        Box<String> strBox = new Box<>();
        strBox.add("Generic Class");
        System.out.println("String: " + strBox.getItem());

        
        Box<Float> floatBox = new Box<>();
        floatBox.add(9649.16f);
        System.out.println("Float: " + floatBox.getItem());
    }
}
