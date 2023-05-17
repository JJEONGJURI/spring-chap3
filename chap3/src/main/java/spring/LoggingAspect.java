package spring;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StopWatch;

public class LoggingAspect {
	public Object logging(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("[LA]로그 시작");
		StopWatch sw = new StopWatch();
		sw.start();
		Object ret = joinPoint.proceed();//위로는 실행전 밑은 실행후
		//joinpoint 는 순서를 가지고 있다
		// : logging() => writer() => logging() => return
		//내 다음 순서의 메서드를 호출해라 >> WriteImpl에 있는 write메서드 호출
		sw.stop();
		//스톱워치 멈춤
		System.out.println("[LA]메서드 실행 시간:" + sw.getTotalTimeMillis() + "밀리초");
		return ret;
		//원래 전달해줘야하는 데이터 전달
		//void 라서 return 값 없음
	}

}
