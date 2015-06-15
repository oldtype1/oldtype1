package org.kosta.dashduowork.model.vo;

import java.util.List;

public class InnReservationListVO {
	private List<InnReservationVO> list;
	private PagingBean pagingBean;
	public InnReservationListVO() {
		super();
	}
	public InnReservationListVO(List<InnReservationVO> list,
			PagingBean pagingBean) {
		super();
		this.list = list;
		this.pagingBean = pagingBean;
	}
	public List<InnReservationVO> getList() {
		return list;
	}
	public void setList(List<InnReservationVO> list) {
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
		return "InnReservationListVO [list=" + list + ", pagingBean="
				+ pagingBean + "]";
	}
	
	
}
