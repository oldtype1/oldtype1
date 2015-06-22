package org.kosta.dashduowork.model.vo;

import java.util.List;

public class TradeListVO {
	private List<TradeVO> list;
	private PagingBean pagingBean;
	public TradeListVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TradeListVO(List<TradeVO> list, PagingBean pagingBean) {
		super();
		this.list = list;
		this.pagingBean = pagingBean;
	}
	public List<TradeVO> getList() {
		return list;
	}
	public void setList(List<TradeVO> list) {
		this.list = list;
	}
	public PagingBean getPagingBean() {
		return pagingBean;
	}
	public void setPagingBean(PagingBean pagingBean) {
		this.pagingBean = pagingBean;
	}
	@Override
	public String toString() {
		return "TradeListVO [list=" + list + ", pagingBean=" + pagingBean + "]";
	}
	
	

}
