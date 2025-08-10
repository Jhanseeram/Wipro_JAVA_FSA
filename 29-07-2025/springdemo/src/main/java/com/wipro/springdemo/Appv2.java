//package com.wipro.springdemo;
//
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//
//import com.wipro.springdemo.beans.Mobile;
//
//public class Appv2 {
//
//	public static void main(String[] args) {
//		AnnotationConfigApplicationContext ctx= new AnnotationConfigApplicationContext();
//		ctx.scan("com.wipro.sprindgdemo");
//		Mobile mobile= ctx.getBean(Mobile.class);
//		System.out.println(mobile.getDisplay());
//	}
//
//}

package com.wipro.springdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.wipro.springdemo.beans.Mobile;

@Configuration
@ComponentScan(basePackages = "com.wipro.springdemo")
public class Appv2 {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Appv2.class);

        Mobile mobile = ctx.getBean(Mobile.class);
        System.out.println(mobile.getDisplay());
    }
}
