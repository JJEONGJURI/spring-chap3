package main;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import spring.WriteImpl;

public class Main1 {
	public static void main(String[] args) {
		AbstractApplicationContext ctx = 
				new GenericXmlApplicationContext("classpath:aop.xml");
		WriteImpl bean = ctx.getBean("write", WriteImpl.class);
		//bean.write() 실행전에 LoggingAspect.loggin() 호출함
		bean.write(); //호출. 핵심메서드? 주된메서드
	}

}
