package annotation;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

// xml 을 annotation 형식으로 바꾼거임

@Component //객체화
@Aspect //AOP 클래스 설정 annotation.xml에서 확인가능함
@Order(3) //순서지정 //AOP가 한개라면 또는 순서 상관없으면 안적어도 무방함.
public class LoggingAspect {
	final String publicMethod = "execution(public * annotation..*(..))";
	//pointcut 설정 : annotation 패키지에 속한 클래스의 모든 public 메서드
	@Before(publicMethod) 
	public void before() {
		System.out.println("[LA] Before 메서드 실행 전 실행 : Before");
	}
	@AfterReturning(pointcut=publicMethod, returning="ret")
	public void afterReturning(Object ret) { //ret : 핵심메서드의 return 값
		System.out.println("[LA] AfterReturning 메서드 정상 종료 후 실행. 리턴값= " + ret);
	}
	@AfterThrowing(pointcut=publicMethod, throwing="ex")
	public void afterThrowing(Throwable ex) { //ex : 예외 객체
		System.out.println("[LA] AfterThrowing 메서드 예외 종료 후 실행. 예외메세지 " + ex.getMessage());
	}
	@After(publicMethod)
	public void afterFinally() { //afterFinally : 실행이 정상이든 비정상이듡 무조건 실행
		System.out.println("[LA] After 메서드 종료 후 실행");
	}

}
