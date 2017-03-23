package spring.oshare.aspectj;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class SessionCheckAdvice { 
	
	/* @Around("execution(public * spring.oshare.controller.*.*(..))")
	 public Object around(ProceedingJoinPoint point )throws Throwable{
		 //세션유무를 체크한다!!
		 Object methodParams [] = point.getArgs();
		 HttpServletRequest request =
				  (HttpServletRequest)methodParams[0];
		 
		 HttpSession session = request.getSession();
		 
		 if(session.getAttribute("loginUser")==null){
			 //오류발생!!!
			 request.setAttribute("errorMsg", "로그인하고 오세요!!!");
			 throw new Exception();
		 }
		 
		Object obj = point.proceed();//실제타켓대상을 호출
		
		return obj;
		 
	 }*/
}







