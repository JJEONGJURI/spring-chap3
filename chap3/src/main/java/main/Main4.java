package main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import annotation.MemberService;
import annotation.ReadArticleService;
import config.AppCtx;
import xml.Article;
import xml.Member;
import xml.UpdateInfo;

public class Main4 {
	public static void main(String[] args) {
		ApplicationContext ctx = 
				new AnnotationConfigApplicationContext(AppCtx.class);
		ReadArticleService service = 
				ctx.getBean("readArticleService", ReadArticleService.class);
		//ReadArticleService :  annotation에 있는 클래스
		try {
			//getArticleAndReadCnt : 핵심 메서드1 호출
			Article a1 = service.getArticleAndReadCnt(1);
			System.out.println("[main] a1 :" + (a1));
			
			//getArticleAndReadCnt : 핵심 메서드2 호출
			Article a2 = service.getArticleAndReadCnt(1);
			System.out.println("[main] a1==a2 :" + (a1 == a2));
			
			//getArticleAndReadCnt : 핵심 메서드3 호출
			service.getArticleAndReadCnt(0); 
		} catch(Exception e) {
			System.out.println("[main] " + e.getMessage());
		}
		System.out.println("\n UpdateMemberInfoTraceAspect 연습");
		MemberService ms = ctx.getBean("memberService",MemberService.class);
		// xml 패키지에 속하는 대상 => aop2 의 <aop:aspect id="loggingAspect">
		ms.regist(new Member()); //order3의 loggingAspect의 영향만 받음 나머지 2개(order 1,2)의 영향은 받지 않는다
		ms.update(new Member(), "hong", new UpdateInfo());
		ms.delete("hong2", "test", new UpdateInfo());
		
	}

}
