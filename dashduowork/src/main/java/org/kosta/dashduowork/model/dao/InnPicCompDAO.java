package org.kosta.dashduowork.model.dao;

import java.util.List;

import org.kosta.dashduowork.model.vo.InnPicCompVO;

public interface InnPicCompDAO {
	public abstract void register(InnPicCompVO vo);
	public abstract InnPicCompVO getMyPicList(int innNo);
	public abstract List<InnPicCompVO> selectByInnNo(String innNo);
}