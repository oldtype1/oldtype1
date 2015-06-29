package org.kosta.dashduowork.model.dao;

import java.util.HashMap;
import java.util.List;

import org.kosta.dashduowork.model.vo.AmenityVO;

public interface AmenityDAO {
	public abstract void update(AmenityVO avo);
	public List<AmenityVO> selectAmenity(String innNo);
	public void delete(String innNo);
	public abstract void register(AmenityVO vo);
}