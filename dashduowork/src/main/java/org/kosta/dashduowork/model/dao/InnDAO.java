package org.kosta.dashduowork.model.dao;

import java.util.HashMap;
import java.util.List;

import org.kosta.dashduowork.model.vo.InnVO;
import org.kosta.dashduowork.model.vo.MemberVO;


public interface InnDAO {
	
	public abstract List<InnVO> getmyinnlist(HashMap<String,String> param);

	public abstract int getTotalPostingCount(MemberVO vo);

}