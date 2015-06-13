package org.kosta.dashduowork.model.dao;

import java.util.HashMap;
import java.util.List;

import org.kosta.dashduowork.model.vo.MemberVO;
import org.kosta.dashduowork.model.vo.WishListDeleteVO;
import org.kosta.dashduowork.model.vo.WishListVO;

public interface WishListDAO {

	public abstract int getTotalPostingCount(MemberVO vo);
	public abstract List<WishListVO> getmywishlist(HashMap<String, String> param);
	public void wishListDelete(WishListDeleteVO wdvo);
}