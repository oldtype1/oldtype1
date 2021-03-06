package org.kosta.dashduowork.model.dao;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.kosta.dashduowork.model.vo.DeleteVO;
import org.kosta.dashduowork.model.vo.InnVO;
import org.kosta.dashduowork.model.vo.MemberVO;
import org.kosta.dashduowork.model.vo.WishListVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
@Repository
public class WishListDAOImpl implements WishListDAO {
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;	
	
	/* (non-Javadoc)
	 * @see org.kosta.dashduowork.model.dao.WishListDAO#getTotalPostingCount(org.kosta.dashduowork.model.vo.MemberVO)
	 */
	@Override
	public int getTotalPostingCount(MemberVO vo) {
		return sqlSessionTemplate.selectOne("inn.getTotalWishlistCount",vo.getMemberId());
	}
	

	/* (non-Javadoc)
	 * @see org.kosta.dashduowork.model.dao.BookDAO#getmybooklist(java.util.HashMap)
	 */
	/* (non-Javadoc)
	 * @see org.kosta.dashduowork.model.dao.WishListDAO#getmywishlist(java.util.HashMap)
	 */
	@Override
	public List<WishListVO> getmywishlist(HashMap<String, String> param) {
		return sqlSessionTemplate.selectList("inn.getmywishlist",param);
	}


	@Override
	public void wishListDelete(DeleteVO wdvo) {
		System.out.println("wishListDelete DAOImpl   들어옴!!!!!!!!!!!!!!!!"+wdvo);
		sqlSessionTemplate.delete("delete.wishListDelete", wdvo);
	}
	@Override
	public void wishlistreg(WishListVO wvo) {
		System.out.println("dao에서 실행됨??" + wvo);
		sqlSessionTemplate.insert("inn.wishlistreg", wvo);

	}

	@Override
	public int wishCheck(WishListVO wvo) {
		System.out.println("daocheck" + wvo);
		return sqlSessionTemplate.selectOne("inn.wishCheck", wvo);
	}
	
	@Override
	public int getWishListNoByInnNo(InnVO ivo) {
		System.out.println("getWishListNoByInnNo    DAOImpl" + ivo);
		return sqlSessionTemplate.selectOne("inn.getWishListNoByInnNo", ivo);
	}
	
	
}
