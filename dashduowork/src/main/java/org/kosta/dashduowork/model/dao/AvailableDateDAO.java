package org.kosta.dashduowork.model.dao;

import org.kosta.dashduowork.model.vo.AmenityVO;
import org.kosta.dashduowork.model.vo.AvailableDateVO;

public interface AvailableDateDAO {
	public abstract void register(AvailableDateVO vo);
	public abstract void update(AvailableDateVO vo);
	public abstract AvailableDateVO selectAvailableDate(int innNo);
}