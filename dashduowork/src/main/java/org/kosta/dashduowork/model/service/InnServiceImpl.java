package org.kosta.dashduowork.model.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.kosta.dashduowork.model.dao.InnDAO;
import org.kosta.dashduowork.model.vo.AmenityVO;
import org.kosta.dashduowork.model.vo.InnListVO;
import org.kosta.dashduowork.model.vo.InnVO;
import org.kosta.dashduowork.model.vo.MemberVO;
import org.kosta.dashduowork.model.vo.PagingBean;
import org.kosta.dashduowork.model.vo.SearchVO;
import org.springframework.stereotype.Service;
@Service
public class InnServiceImpl implements InnService {
	@Resource(name="innDAOImpl")
	private InnDAO innDAO;
	
	@Override
	public InnListVO getmyinnlist(MemberVO vo, String pageNo) {
		int pn=1;
		if(pageNo!=null){
			pn=Integer.parseInt(pageNo);
		}
		System.out.println("pageNo?"+ pn);
		
		HashMap<String,String> param = new HashMap<String,String>();
		param.put("pageNo",Integer.toString(pn));
		param.put("member_id",vo.getMemberId());
		
		List<InnVO> list = innDAO.getmyinnlist(param);
		int total=innDAO.getTotalPostingCount(vo);
		PagingBean pagingBean=new PagingBean(total,pn);
		
		return new InnListVO(list,pagingBean);
	}
	
	@Override //은식, 동원
	public List<InnVO> findInnByCheckedAmenity(AmenityVO vo) {
		System.out.println("Service의 findInnByCheckedAmenity");
		List<InnVO> list =  innDAO.selectInnByCheckedAmenity(vo);
		System.out.println(list);
		return list;
	}
	//plus+++++++++++++++++++++++++++++++++++++++++++
	public List<InnVO> selectInnByCityAndDate(SearchVO vo) { //은식, 동원
		System.out.println("Service의 selectInnByCityAndDate");
		List<InnVO> list =  innDAO.selectInnByCityAndDate(vo);
		System.out.println(list);
		return list;
	}
	public List<InnVO> selectInnByCityAndDateAndAcceptableNo(SearchVO vo){ //은식, 동원
		System.out.println("Service의 selectInnByCityAndDateAndAcceptableNo");
		List<InnVO> list =  innDAO.selectInnByCityAndDateAndAcceptableNo(vo);
		System.out.println(list);
		return list;
	}

}
