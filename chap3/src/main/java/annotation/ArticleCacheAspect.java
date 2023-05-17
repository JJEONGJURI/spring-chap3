package annotation;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import xml.Article;

@Component //Component 스캔에 의해서 객체화 됨
@Aspect	   //AOP 클래스 aop사용가능하게
@Order(2)  //순서
public class ArticleCacheAspect {
	//cache : {1,new Article(1)}
	
	private Map<Integer,Article> cache = new HashMap<Integer,Article>();

	@Around("execution(public * *..ReadArticleService.*(..))")
	//pointcut : ReadArticleService 클래스의 모든 public 메서드 실행 전후
	//around : 실행 전후
	//핵심메서드 설정?
	
	public Object cache(ProceedingJoinPoint joinPoint) throws Throwable {
	
		Integer id = (Integer)joinPoint.getArgs()[0];
		//joinPoint.getArgs() : 핵심 메서드의 매개변수 목록
		
		System.out.println("[ACA] " + joinPoint.getSignature().getName() + "(" + id + ") 메서드 호출 전");
		//joinPoint.getSignature().getName() : 핵심 메서드 명 (getArticleAndReadCnt)
		Article article = cache.get(id);
		if(article != null) {
			System.out.println("[ACA] cache 에서 Article[" + id + "] 가져옴");
			return article;
		}
		Object ret = joinPoint.proceed(); //LogingAspect.before()
		System.out.println("[ACA] "
				+ joinPoint.getSignature().getName() + "(" + id + ") 메서드 호출 후");
		if(ret != null && ret instanceof Article) {
			cache.put(id, (Article)ret);
			System.out.println("[ACA] cache에 Article[" + id + "] 추가함");
		}
		return ret;
	}
}
