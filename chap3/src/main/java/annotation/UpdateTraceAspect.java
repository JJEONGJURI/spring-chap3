package annotation;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import xml.UpdateInfo;

@Component
@Aspect
@Order(1)
public class UpdateTraceAspect {
	@AfterReturning(pointcut="args(..,id,info)",
	//args(..,id,info) : 마지막의 매개변수가 id,info 인 메서드 선택
			
			argNames="ret,id,info", returning="ret")
	public void traceReturn(Object ret, String id, UpdateInfo info) {
		System.out.println("[TA] 정보 수정 결과: " + ret +", 대상ID:" + id
				+ ", 수정정보:" + info);
	}
}
