package org.kosta.dashduowork.model.dao;

import java.util.List;

import org.kosta.dashduowork.model.vo.ReportVO;

public interface ReportDAO {
	public void insertReport(String word);
	public int updateReport(String word);
	public List<ReportVO> selectReport();
}
