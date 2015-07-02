package org.kosta.dashduowork.model.dao;

import java.util.List;

import javax.annotation.Resource;

import org.kosta.dashduowork.model.vo.ReportVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
@Repository
public class ReportDAOImpl implements ReportDAO{
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;	
	
	public void insertReport(String word) {
		sqlSessionTemplate.insert("report.insertReport", word);
	}

	public int updateReport(String word) {	
		return sqlSessionTemplate.update("report.updateReport",word);
	}

	public List<ReportVO> selectReport() {
		return sqlSessionTemplate.selectList("report.selectReport");
	}
	
}
