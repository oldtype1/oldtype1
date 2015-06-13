package org.kosta.dashduowork.model.vo;

import java.util.List;

public class WishListListVO {
	private List<WishListVO> list;
	private PagingBean pagingBean;
	
	public WishListListVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public WishListListVO(List<WishListVO> list, PagingBean pagingBean) {
		super();
		this.list = list;
		this.pagingBean = pagingBean;
	}
	public List<WishListVO> getList() {
		return list;
	}
	public void setList(List<WishListVO> list) {
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
		return "ListVO [list=" + list + ", pagingBean=" + pagingBean + "]";
	}
	
	

}
