package org.kosta.dashduowork.aop.common;

import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.kosta.dashduowork.model.service.ReportService;
import org.kosta.dashduowork.model.vo.FilterVO;
import org.kosta.dashduowork.model.vo.InnVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class AspectUsingAnnotation {
	private Logger log = LoggerFactory.getLogger(getClass());
	@Resource
	private ReportService reportService;
@Around("execution(public * org.kosta.dashduowork.model..*DAOImpl.selectInnBy*(..))")
public Object logging(ProceedingJoinPoint joinPoint) throws Throwable {
	/*List<InnVO> resultList=null;
	Object value = null;
	FilterVO fvo=null;
	String searchWord=null;
	String className = joinPoint.getTarget().getClass().getName();
	String methodName = joinPoint.getSignature().getName();
	log.info(className+"   "+methodName);
	try{
		Object param[]=joinPoint.getArgs();// 메서드 인자값 - 매개변수
		fvo=(FilterVO) param[0];
		searchWord=fvo.getSearchWord();
		System.out.println("AOP에서 검색어 확인 : "+searchWord);
		value = joinPoint.proceed();
		resultList=(List<InnVO>)value;
		if(resultList.size()!=0){
			reportService.saveReport(searchWord);
			log.info("검색 메서드 인자값 db에 적재: {}",searchWord);
		}
	}catch(Exception e){
		e.printStackTrace();
	}*/
	List<InnVO> resultList=null;
	Object value = null;
	FilterVO fvo=null;
	HashMap<String,Object> wordMap=null;
	String searchWord=null;
	String className = joinPoint.getTarget().getClass().getName();
	String methodName = joinPoint.getSignature().getName();
	log.info(className+"   "+methodName);
	try{
		Object param[]=joinPoint.getArgs();// 메서드 인자값 - 매개변수
		if(param[0] instanceof HashMap){
			wordMap=(HashMap<String, Object>)param[0];
			searchWord=(String) wordMap.get("address");
		}else{
			fvo=(FilterVO) param[0];
			searchWord=fvo.getSearchWord();
		}
		System.out.println("AOP에서 검색어 확인 : "+searchWord);
		log.debug("검색 메서드 인자값 db에 적재: {}",searchWord);
		reportService.saveReport(searchWord);	
		value = joinPoint.proceed();
		resultList=(List<InnVO>)value;
		if(resultList.size()!=0){
			reportService.saveReport(searchWord);
			log.info("검색 메서드 인자값 db에 적재: {}",searchWord);
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	return value;
}


}
