package com.myproject.aspect;

import java.lang.reflect.Method;

import javax.annotation.Resource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.myproject.annotation.MethodLog;

//用mongodb 来存储日志

@Aspect
@Component
public class LogControllerAspect {
	
	@Resource
	@Qualifier(value="mongodatabase")
	private  MongoDatabase database;
	/* 			action 日志 入口  			*/	
	
	@Pointcut(value = "@annotation(com.myproject.annotation.MethodLog)")  
	public void pointControllerName() {  
	  
	}
	
	@Around("pointControllerName()")
	public Object aroundAction(ProceedingJoinPoint  joinPoint) throws Throwable{
		
		long start = System.currentTimeMillis();
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		String methodName = signature.getName();
		Method method = joinPoint.getTarget()
				.getClass().getMethod(signature.getName(), signature.getParameterTypes());
		MethodLog annotation = method.getAnnotation(MethodLog.class);
		String opertype = annotation.operType();
		String serviceName = annotation.serviceName();
		boolean flag = false;  //是否异常
		try {
			//记录 日志
		  return joinPoint.proceed();
		} catch (Throwable e) {
			//异常 日志
			flag = true;
			throw e;
		}finally {
			//完成日志
			long end = System.currentTimeMillis();
			long sumTime = end - start;
			
			MongoCollection<Document> collection = database.getCollection("logs");
			Document document = new Document()
					.append("methodName", methodName)
					.append("serviceName", serviceName)
					.append("opertype", opertype)
					.append("spendtime", sumTime)
					.append("flag", flag);
			//以后加 操作的用户
			collection.insertOne(document);
		}
	}
	
//	/*			service日志入口			*/
//	@Pointcut(value = "execution(* com.westsoft.kft.repairs.service.impl.*.*(..))")  
//	public void pointServiceName() {  
//	  
//	}
//	@Around("pointControllerName()")
//	public void aroundService(JoinPoint joinPoint){
//		long start = System.currentTimeMillis();
//		try {
//			//记录 日志
//			
//			((ProceedingJoinPoint) joinPoint).proceed();
//			
//		} catch (Throwable e) {
//			//异常 日志
//			
//		}finally {
//			//完成日志
//			long end = System.currentTimeMillis();
//			long sumTime = end - start;
//		}
//	}
	
//	@Before(value="pointCutName()")
//	public void beforeAction(JoinPoint joinPoint){
//		//前置通知
//	}
//	
//	@After(value="pointCutName()")
//	public void afterAction(JoinPoint joinPoint){
//		//后置通知
//	}
//	
//	@AfterThrowing(value="pointCutName()",throwing="ex")
//	public void  afterThrowAction() {
//		//异常通知
//	}
}
