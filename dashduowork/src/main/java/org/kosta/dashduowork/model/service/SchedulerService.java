package org.kosta.dashduowork.model.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.kosta.dashduowork.model.dao.BookDAO;
import org.kosta.dashduowork.model.dao.TradeDAO;
import org.kosta.dashduowork.model.vo.TradeVO;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SchedulerService { 
	@Resource(name="tradeDAOImpl")
	private TradeDAO tradeDAO;
	@Resource(name="bookDAOImpl")
	private BookDAO bookDAO;
	
	//매일 오후 2시 마다 예약테이블에서 체크아웃 시간 지난목록 불러와서(select)
	//거래내역에 인서트 시켜준후!!(insert) 예약 테이블에서 삭제(delete)-- 6/19 주형
	@Transactional 
	@Scheduled(cron = "0 38 15 * * *")
	 public void insertTradeSchedule() {
	 List<TradeVO> tlist = bookDAO.selectBookOutDate();
	 System.out.println("지난예약갯수: "+tlist.size());
	 for(int i=0; i<tlist.size(); i++){
	 tradeDAO.insertTradeBookOutDate(tlist.get(i));	 
	 }
	 System.out.println("현재시간: "+ new Date()+"거래내역에 "+tlist+" 추가");	  
	 //그리고 예약 테이블에서 삭제
	 bookDAO.bookDeleteOutDate();
     System.out.println("현재시간: "+ new Date()+"예약테이블에서 날짜지난 목록 삭제");
	 } 
 /**
  * second, minute, hour, day, month, weekday를 의미 0 0 15 * * * : 매일 15시
  */
 @Scheduled(cron = "0 30 14 * * *")
 public void printWithCron() {
  System.out.println("매일 한번씩 실행 " + new Date());
 }
 /*
  * 참고 
  * 0 0 * * * * : 매일 매시 시작 시점
  * 
  * 10 * * * * * : 10초 간격
  * 
  * 0 0 8-10 * * * : 매일 8,9,10시
  * 
  * 0 0/30 8-10 * * * : 매일 8:00, 8:30, 9:00, 9:30, 10:00
  * 
  * 0 0 9-17 * * MON-FRI : 주중 9시부터 17시까지
  * 
  * 0 0 0 25 12 ? : 매년 크리스마스 자정
  */
 
}