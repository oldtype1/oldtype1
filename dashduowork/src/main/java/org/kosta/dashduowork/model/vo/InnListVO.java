package org.kosta.dashduowork.model.vo;

import java.util.List;

public class InnListVO {
	private List<InnVO> innList;
	private PagingBean pagingBean;
	public InnListVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public InnListVO(List<InnVO> innList, PagingBean pagingBean) {
		super();
		this.innList = innList;
		this.pagingBean = pagingBean;
	}
	public List<InnVO> getInnList() {
		return innList;
	}
	public void setInnList(List<InnVO> innList) {
		this.innList = innList;
	}
	public PagingBean getPagingBean() {
		return pagingBean;
	}
	public void setPagingBean(PagingBean pagingBean) {
		this.pagingBean = pagingBean;
	}
	@Override
	public String toString() {
		return "InnListVO [innList=" + innList + ", pagingBean=" + pagingBean
				+ "]";
	}
	
	
	
}
