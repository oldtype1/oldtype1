package org.kosta.dashduowork.model.vo;
/**
 * 페이징 처리를 위한 비즈니스 클래스
 * @author KOSTA-00-KANGSA
 * PagingBean method 구현 순서 
	getTotalPage()
	getTotalPageGroup()
	getNowPageGroup()
	getStartPageOfPageGroup()
	getEndPageOfPageGroup()
	isPreviousPageGroup()
	isNextPageGroup()	
 *
 */
public class PagingBean {
	/**
	 * 현재 페이지
	 */
	private int nowPage=1;
	/**
	 * 페이지 당 보여줄 게시물 수 
	 */
	private int contentNumberPerPage=CommonConstants.CONTENT_NUMBER_PER_PAGE;
	/**
	 * 페이지 그룹 당 페이지 수 
	 */
	private int pageNumberPerPageGroup=CommonConstants.PAGE_NUMBER_PER_PAGEGROUP;
	/**
	 * 현재 db에 저장된 총 게시물 수 
	 */
	private int totalContents;
	/**
	 * 총게시물수와 현재 페이지 정보를 할당 
	 * @param totalContents
	 * @param nowPage
	 */
	public PagingBean(int totalContents,int nowPage){
		this.totalContents=totalContents;
		this.nowPage=nowPage;
	}
	/**
	 * 현재 페이지 리턴
	 * @return
	 */
	public int getNowPage(){
		return nowPage;
	}
	/**
	 * 총 페이지 수를 return한다.
		1. 전체 데이터(게시물) % 한 페이지에 보여줄 데이터 개수 
		=> 0 이면 둘을 / 값이 총 페이지 수
		2. 전체 데이터(게시물) % 한 페이지에 보여줄 데이터 개수 
		=> 0보다 크면 둘을 / 값에 +1을 한 값이 총 페이지 수
		게시물수 1 2 3 4 5 6 7 8 9 10 11 12
		1페이지 1~5
		2페이지 6~10
		3페이지 11 
		ex) 게시물 32 개 , 페이지당 게시물수 5개 
		-> 7 페이지
	 * @return
	 */
	public int getTotalPage(){
		int num=this.totalContents%this.contentNumberPerPage;
		int totalPage=0;
		if(num==0){
			totalPage=this.totalContents/this.contentNumberPerPage;
		}else{
			totalPage=this.totalContents/this.contentNumberPerPage+1;
		}
		return totalPage;
	}
	/**
	 * 총 페이지 그룹의 수를 return한다.
		1. 총 페이지수 % Page Group 내 Page 수. 
		=> 0 이면 둘을 / 값이 총 페이지 수
		2. 총 페이지수 % Page Group 내 Page 수. 
		=> 0보다 크면 둘을 / 값에 +1을 한 값이 총 페이지 수
		ex) 총 게시물 수 23 개 
		총 페이지 ? 총 페이지 그룹수 ? 
		페이지 1 2 3 4 5
		페이지그룹 1234(1그룹) 5(2그룹) 
	 * @return
	 */
	public int getTotalPageGroup(){
		int num=this.getTotalPage()%this.pageNumberPerPageGroup;
		int totalPageGroup=0;
		if(num==0){
			totalPageGroup=this.getTotalPage()/this.pageNumberPerPageGroup;
		}else{
			totalPageGroup=this.getTotalPage()/this.pageNumberPerPageGroup+1;
		}
		return totalPageGroup;
	}
	/**
	 * 현재 페이지가 속한 페이지 그룹 번호(몇 번째 페이지 그룹인지) 을
	 *  return 하는 메소드 <br>
	 *  1. 현재 페이지 % Page Group 내 Page 수 => 0 이면 <br>
	 *      둘을 / 값이 현재 페이지 그룹. <br>
	 *  2. 현재 페이지 % Page Group 내 Page 수 => 0 크면 <br>
	 *      둘을 / 값에 +1을 한 값이 현재 페이지 그룹<br>
	 *     페이지  1 2 3 4 /5 6 7 8/ 9 10
	 *     				1그룹   2그룹   3그룹 
	 * @return
	 */
	public int getNowPageGroup(){
		int num=this.nowPage%this.pageNumberPerPageGroup;
		int nowPageGroup=0;
		if(num==0){
			nowPageGroup=this.nowPage/this.pageNumberPerPageGroup;
		}else{
			nowPageGroup=this.nowPage/this.pageNumberPerPageGroup+1;
		}
		return nowPageGroup;
	}
	/**
	 * 현재 페이지가 속한 페이지 그룹의 시작 페이지 번호를 return 한다.<br>
       Page Group 내 Page 수*(현재 페이지 그룹 -1) + 1을 한 값이 첫 페이지이다.<br>
           (페이지 그룹*페이지 그룹 개수, 그룹의 마지막 번호이므로)    <br>
      페이지 그룹 <br>
      1 2 3 4 -> 5 6 7 8 -> 9 10 <br>
	 * @return
	 */
	public int getStartPageOfPageGroup(){
		int num=this.pageNumberPerPageGroup*(this.getNowPageGroup()-1)+1;
		return num;
	}
	/**
	 * 현재 페이지가 속한 페이지 그룹의 마지막 페이지 번호를 return 한다.<br>
       1. 현재 페이지 그룹 * 페이지 그룹 개수 가 마지막 번호이다. <br>
       2. 그 그룹의 마지막 페이지 번호가 전체 페이지의 마지막 페이지 번호보다 <br>
           큰 경우는 전체 페이지의 마지막 번호를 return 한다.<br>
       1 2 3 4 -> 5 6 7 8 -> 9 10     
	 * @return
	 */
	public int getEndPageOfPageGroup(){
		int num=this.getNowPageGroup()*this.pageNumberPerPageGroup;
		if(this.getTotalPage()<num){
			num=this.getTotalPage();
		}
		return num;
	}
	/**
	 * 이전 페이지 그룹이 있는지 체크하는 메서드 <br>
	 * 현재 페이지가 속한 페이지 그룹이 1보다 크면 true<br>
	 * ex )  페이지  
	 *			1 2 3 4 / 5 6 7 8 / 9 10 <br>
	 *			    1        2           3 group
	 * @return
	 */
	public boolean isPreviousPageGroup(){
		boolean flag=false;
		if(this.getNowPageGroup()>1){
			flag=true;
		}
		return flag;
	}
	/**
	 * 다음 페이지 그룹이 있는지 체크하는 메서드 <br>
	 * 현재 페이지 그룹이 마지막 페이지 그룹(<br>
	 * 마지막 페이지 그룹 == 총 페이지 그룹 수) 보다 작으면 true<br>
	 *  * ex )  페이지  <br>
	 *			1 2 3 4 / 5 6 7 8 / 9 10 <br>
	 *			    1        2           3 group
	 * @return
	 */
	public boolean isNextPageGroup(){
		boolean flag=false;
		if(this.getNowPageGroup()<this.getTotalPageGroup()){
			flag=true;
		}		
		return flag;
	}
	
	
	public int getTotalContents() {
		return totalContents;
	}
	public void setTotalContents(int totalContents) {
		this.totalContents = totalContents;
	}
	public static void main(String args[]){         
		  PagingBean p=new PagingBean(47,1);
		  // 전체 페이지 수 : 10
		  System.out.println("getTotalPage:"+p.getTotalPage());    
		  // 전체 페이지 그룹 수 : 3
		  System.out.println("getTotalPageGroup:"+p.getTotalPageGroup()); 
		  p=new PagingBean(31,6);// 게시물수 31 현재 페이지 6
		  // 게시물수 31 -> 총페이지수 7 -> 총페이지그룹->2 
		  // 현재 페이지 그룹 : 2 
		  System.out.println("getNowPageGroup:"+p.getNowPageGroup());
		  // 페이지 그룹의 시작 페이지 : 5
		  System.out.println("getStartPageOfPageGroup:"+p.getStartPageOfPageGroup());
		  // 페이지 그룹의 마지막 페이지 : 7
		  System.out.println("getEndPageOfPageGroup:"+p.getEndPageOfPageGroup());
		  // 이전 페이지 그룹이 있는 지 : true
		  System.out.println("isPreviousPageGroup:"+p.isPreviousPageGroup());
		  // 다음 페이지 그룹이 있는 지 : false
		  System.out.println("isNextPageGroup:"+p.isNextPageGroup());/**/
		 }
	
}











