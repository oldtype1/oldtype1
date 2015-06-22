package org.kosta.dashduowork.model.dao;

import java.util.HashMap;
import java.util.List;

import org.kosta.dashduowork.model.vo.InnReservationVO;
import org.kosta.dashduowork.model.vo.MemberVO;

public interface InnReservationDAO {
	public abstract int getTotalPostingCount(MemberVO vo);
	public abstract List<InnReservationVO> getMyInnReservationList(HashMap<String, String> param);

}