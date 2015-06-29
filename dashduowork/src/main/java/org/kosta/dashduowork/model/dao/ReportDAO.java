package org.kosta.dashduowork.model.dao;

import java.util.List;

public interface ReportDAO {
	public void insertReport(String word);
	public int updateReport(String word);
	public List selectReport();
}
