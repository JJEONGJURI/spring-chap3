package main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import xml.Article;
import xml.Member;
import xml.MemberService;
import xml.ReadArticleService;
import xml.UpdateInfo;

public class Main2 {

	public static void main(String[] args) {
		//환경만듬
		String[] config = {"di.xml","aop2.xml"};//di-> 핵심메서드 aop2->aop관련된내용 aop2 빼면 aop 실행안됨
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
		System.out.println("\n UpdateMemberInfoTraceAspect 연습");
		MemberService ms = ctx.getBean("memberService",MemberService.class);
		// xml 패키지에 속하는 대상 => aop2 의 <aop:aspect id="loggingAspect">
		ms.regist(new Member()); //order3의 loggingAspect의 영향만 받음 나머지 2개(order 1,2)의 영향은 받지 않는다
		ms.update("hong", new UpdateInfo());
		ms.delete("hong2", "test");
		/*
		  UpdateMemberInfoTraceAspect 연습
		[LA]메서드 실행 전 전처리 수행 기능
		MemberService.regist() 메서드 실행
		[LA]메서드 정상 처리 후 수행 함. ret=null
		[LA]메서드 실행 후 수행함
		[LA]메서드 실행 전 전처리 수행 기능
		MemberService.update() 메서드 실행
		[LA]메서드 정상 처리 후 수행 함. ret=true
		[LA]메서드 실행 후 수행함
		[LA]메서드 실행 전 전처리 수행 기능
		MemberService.delete() 메서드 실행
		[LA]메서드 정상 처리 후 수행 함. ret=false
		[LA]메서드 실행 후 수행함
		 */
	}

}
