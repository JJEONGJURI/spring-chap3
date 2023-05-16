package main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import xml.Article;
import xml.ReadArticleService;

public class Main2 {

	public static void main(String[] args) {
		//환경만듬
		String[] config = {"di.xml","aop2.xml"};
		ApplicationContext ctx = new ClassPathXmlApplicationContext(config);
		
		// 객체가져와
		ReadArticleService service = //ReadArticleServiceImpl 객체 / xml 에 속해있음
				ctx.getBean("readArticleService", ReadArticleService.class);
		try {
			Article a1 = service.getArticleAndReadCnt(1);
			System.out.println("[main] a1 :" + (a1));
			Article a2 = service.getArticleAndReadCnt(1);
			System.out.println("[main] a1==a2 :" + (a1 == a2));
			service.getArticleAndReadCnt(0); //ReadArticleServiceImpl / 예외발생
		} catch(Exception e) {
			System.out.println("[main] " + e.getMessage());
		}
	}

}
