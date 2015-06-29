package org.kosta.dashduowork.model.service;

import java.util.List;

import org.kosta.dashduowork.model.vo.ReportVO;


public interface ReportService {
public void saveReport(String word);
public List<ReportVO> selectReport();
}
