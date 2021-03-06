package org.kosta.dashduowork.model.dao;

import java.util.HashMap;
import java.util.List;

import org.kosta.dashduowork.model.vo.DeleteVO;
import org.kosta.dashduowork.model.vo.InnVO;
import org.kosta.dashduowork.model.vo.MemberVO;
import org.kosta.dashduowork.model.vo.WishListVO;

public interface WishListDAO {

	public abstract int getTotalPostingCount(MemberVO vo);
	public abstract List<WishListVO> getmywishlist(HashMap<String, String> param);
	public abstract void wishListDelete(DeleteVO wdvo);
	public abstract void wishlistreg(WishListVO wvo);
	public abstract int wishCheck(WishListVO wvo);
	public abstract int getWishListNoByInnNo(InnVO ivo);
}