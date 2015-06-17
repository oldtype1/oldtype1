<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
    <script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <link href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css"
    rel="stylesheet" type="text/css">
    <link href="http://pingendo.github.io/pingendo-bootstrap/themes/default/bootstrap.css"
    rel="stylesheet" type="text/css">
 
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
                class="img-circle img-responsive" width="50" height="70">
              </center>
            </div>
            <div class="col-md-5">
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
              	<td>유효여부</td>
              	<td>거래내역 삭제</td>
                </tr>
                   </thead>
                  <c:forEach var="tvo" items="${requestScope.tvo.list}">               
                  <tr>
                    <td class="col-md-1 text-center" draggable="true">
					<c:if test="${tvo.innorbook=='등록'}">                    
                      <h4 style="color:pink;">${tvo.innorbook}</h4>
                      </c:if>
                      <c:if test="${tvo.innorbook=='예약'}">
                      <h4 style="color: orange;">${tvo.innorbook}</h4>
                      </c:if>
                    </td>                
                    <td class="col-md-1">
                        <h4 class="text-center">${tvo.innName}</h4>
                    </td>
                    <c:if test="${tvo.innorbook=='예약'}">
                    <td class="col-md-1">
                      <h4 class="text-center">체크인: ${tvo.bookCheckIn}<br>체크아웃: ${tvo.bookCheckOut}</h4>
                    </td>
                    </c:if>
                    <c:if test="${tvo.innorbook=='등록'}">
                     <c:if test="${tvo.innAvailability=='Y'}">
                      <td class="col-md-1" style="color: green">
                      <h4 class="text-center">이용가능</h4>
                    </td>
                    </c:if>
                    <c:if test="${tvo.innAvailability=='N'}">
                      <td class="col-md-1" style="color: gray">
                      <h4 class="text-center">등록만료</h4>
                    </td>
                    </c:if>
                     <c:if test="${tvo.innAvailability==null}">
                      <td class="col-md-1">
                      <h4 class="text-center">등록이면 널넣지 말라고</h4>
                   		 </td>
                    </c:if>
                    </c:if>
                     <td class="col-md-1">
                   <h4 class="text-center"><a href="tradedelete.do?tradeNo=${tvo.tradeNo}">삭제</a></h4>
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




