package org.kosta.dashduowork.model.vo;

import java.util.List;

public class InnListVO {
	private List<InnVO> list;
	private PagingBean pagingBean;
	
	public InnListVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public InnListVO(List<InnVO> list, PagingBean pagingBean) {
		super();
		this.list = list;
		this.pagingBean = pagingBean;
	}
	public List<InnVO> getList() {
		return list;
	}
	public void setList(List<InnVO> list) {
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
