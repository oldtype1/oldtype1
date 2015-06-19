package org.kosta.dashduowork.model.dao;

import org.kosta.dashduowork.model.vo.AmenityVO;

public interface AmenityDAO {
	public abstract void register(AmenityVO vo);
	public abstract void update(AmenityVO avo);
	public abstract AmenityVO selectAmenity(String innNo);
}