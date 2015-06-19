<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
    <script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <link href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css"
    rel="stylesheet" type="text/css">
    
 <script  type="text/javascript">
	function deleteItem(tradeNo){
		if(confirm("해당 내역을 삭제하시겠습니까?")){
			location.href="tradedelete.do?tradeNo="+tradeNo;
		}
	}
</script> 
        <div class="container">
          <div class="row text-center">
            <div class="col-md-12 text-center">
              <ul class="lead nav nav-justified nav-tabs">
                <li>
                  <a href='member_myprofile.do?memberId=${sessionScope.mvo.memberId}'>내프로필</a>
                </li>
                <li>
                  <a href='get_myinnlist.do'>숙소등록</a>
                </li>
                <li>
                  <a href='get_mybooklist.do'>예약숙소</a>
                </li>
                <li>
                  <a href='get_mytradelist.do'>거래내역</a>
                </li>
                <li>
                  <a href='get_mywishlist.do'>위시리스트</a>
                </li>
                <li>
                  <a href="get_innReservation_list.do">예약관리</a>
                </li>
              </ul>
            </div>
          </div>
        </div>
      <div class="section">
        <div class="container">
          <div class="row">
            <div class="col-md-1">
              <center>
                <img src="${requestScope.member.profilePicVO.filePath}"
                class="img-circle img-responsive" style="margin-top: 60px; margin-bottom: 50px;" width="50" height="70" >
              </center>
            </div>
            <div class="col-md-5" style="margin-top: 50px; margin-bottom: 50px; ">
              <h2>${sessionScope.mvo.memberName}님의 거래내역 ${requestScope.tvo.pagingBean.totalContents}개</h2>
            </div>
          </div>
        </div>
      </div>
     <div class="section text-center">
        <div class="container">
          <div class="row">
            <div class="col-md-15 text-center">
              <table class="table">
                <tbody>         
       			  <thead>
            	<tr>
            	<td>구분</td>
            	<td>숙소 이름</td>
              	<td>체크인</td>
              	<td>체크아웃</td>
              	<td>가격</td>
              	<td>거래내역 삭제</td>
                </tr>
                   </thead>
                  <c:forEach var="tvo" items="${requestScope.tvo.list}">           
                  <tr>        
					<c:if test="${tvo.memberId==sessionScope.mvo.memberId}">           
					<td class="col-md-1 text-center" draggable="true">     
                      <h4 style="color:red;">예약-  ${tvo.master}님의   숙소 예약</h4>
                      </td>    
                      </c:if>
                      <c:if test="${tvo.master==sessionScope.mvo.memberId}">
                      <td class="col-md-1 text-center" draggable="true"> 
                      <h4 style="color: blue;">등록-  ${tvo.memberId} 님이 예약</h4>
                      </td> 
                      </c:if>                          
                    <td class="col-md-1">
                        <h4 class="text-center">${tvo.innName}</h4>
                    </td>        
                    <td class="col-md-1">
                      <h4 class="text-center">${tvo.bookCheckIn}</h4>
                    </td>
                     <td class="col-md-1">
                      <h4 class="text-center"> ${tvo.bookCheckOut}</h4>
                    </td>
                      <td class="col-md-1">
                      <h4 class="text-center"> ${tvo.price}</h4>
                    </td>
                     <td class="col-md-1">
                   <input type="button"  class="btn btn-default"  value="삭제" id="deleteBtn"
				    	onclick="deleteItem('${tvo.tradeNo}')">
                    </td>
                  </tr>          
    				</c:forEach>     
               </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
      <div class="section text-center">
        <div class="container">
          <div class="row">
            <div class="col-md-12 text-center">
              <ul class="pagination pagination-sm">
                  
                  <c:set var="pb" value="${requestScope.tvo.pagingBean}"></c:set>
						
						<c:if test="${pb.previousPageGroup}">
						<li><a
							href="get_mytradelist.do?pageNo=${pb.startPageOfPageGroup-1}">Prev</a>
						</li>
						</c:if>
						
						<c:forEach var="i" begin="${pb.startPageOfPageGroup}"
							end="${pb.endPageOfPageGroup}">
							<c:if test="${pb.nowPage!=i}">
								<li><a href="get_mytradelist.do?pageNo=${i}">${i}</a>
								<li>
							</c:if>
							<c:if test="${pb.nowPage==i}">
								<li><a href="#">${i}</a></li>
							</c:if>
						&nbsp;					
						</c:forEach>
			
					<c:if test="${pb.nextPageGroup}">
						<li>
							<a href="get_mytradelist.do?pageNo=${pb.endPageOfPageGroup+1}">Next</a>
						</li>
					</c:if>

              </ul>
            </div>
          </div>
        </div>
      </div>




