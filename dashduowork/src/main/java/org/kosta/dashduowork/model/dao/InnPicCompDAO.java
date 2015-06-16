package org.kosta.dashduowork.model.dao;

import org.kosta.dashduowork.model.vo.InnPicCompVO;

public interface InnPicCompDAO {
	public abstract void register(InnPicCompVO vo);
	public abstract InnPicCompVO getMyPicList(int innNo);
}