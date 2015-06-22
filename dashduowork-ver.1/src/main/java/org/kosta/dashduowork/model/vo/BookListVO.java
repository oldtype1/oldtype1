package org.kosta.dashduowork.model.vo;

import java.util.List;

public class BookListVO {
	private List<BookVO> list;
	private PagingBean pagingBean;
	
	public BookListVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BookListVO(List<BookVO> list, PagingBean pagingBean) {
		super();
		this.list = list;
		this.pagingBean = pagingBean;
	}
	public List<BookVO> getList() {
		return list;
	}
	public void setList(List<BookVO> list) {
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
