package org.kosta.dashduowork.model.service;

import java.util.List;

import javax.annotation.Resource;

import org.kosta.dashduowork.model.dao.ReportDAO;
import org.kosta.dashduowork.model.vo.ReportVO;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService{
	@Resource(name="reportDAOImpl")
	private ReportDAO reportDAO;
	
	@Override
public void saveReport(String word){	
		System.out.println(word+"Service에 들어온 word");
	int result=reportDAO.updateReport(word);
	if(result==0)
		reportDAO.insertReport(word);			
}
@Override
public List<ReportVO> selectReport(){
return reportDAO.selectReport();
}

	
}
