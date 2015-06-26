<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
    <script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <link href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css"
    rel="stylesheet" type="text/css">

  
<script type="text/javascript">
$(document).ready(function(){

})


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
                  <a href="get_mytradelist.do">거래내역</a>
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
               align="center" class="img-circle img-responsive" style="margin-top: 60px; margin-bottom: 50px; " width="50" height="70" >
              </center>
            </div>
            <div class="col-md-5" style="margin-top: 50px; margin-bottom: 50px; ">
            <c:choose>
            <c:when test="${requestScope.masterVO==null}">
            <h2>${sessionScope.mvo.memberName}님 결제 완료</h2>
                                <div class="section text-center">
        <div class="container">
          <div class="row">
            <div class="col-md-15">
            			<div class="col-md-12">
				<table class="table">
						<thead colspan="2">
							<tr>
								<td>결제한 숙소 정보</td>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td class="col-md-1 " colspan="2">
								${requestScope.ivo.innName} 
								</td>
								</tr>
								<tr>
								<td  class="col-md-1"> <a class="pull-left" href="#"><img class="media-object" src="${requestScope.innMainPic.filePath}" height="150" width="150"></td>
							</tr>
							<tr>
								<td class="col-md-3">체크인 날짜</td>
								<td class="col-md-4">
								${requestScope.bookInfo.bookCheckIn}
								</td>	
							</tr>
							<tr>
							<td class="col-md-3">체크아웃 날짜</td>
								<td class="col-md-4">
								${requestScope.bookInfo.bookCheckOut}
								</td>
								</tr>
								<tr>
									<tr>
							<td class="col-md-3">숙박 인원</td>
								<td class="col-md-4">
								${requestScope.bookInfo.bookCount}
								</td>
								</tr>
								<td class="col-md-3">총 금액</td>
								<td class="col-md-4">
								${requestScope.payTotalPrice}
								</td>
							</tr>
						</tbody>
					</table>
					</div>

			</div>
		</div>
	</div>
</div>
</c:when>
<c:otherwise>
   <h2>${sessionScope.mvo.memberName}님 예약 완료</h2>
      <div class="section text-center">
        <div class="container">
          <div class="row">
            <div class="col-md-15">
            			<div class="col-md-6">
				<table class="table">
						<thead colspan="2">
							<tr>
								<td>예약한 숙소 정보</td>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td class="col-md-1 " colspan="2">
								${requestScope.ivo.innName} 
								</td>
								</tr>
								<tr>
								<td  class="col-md-1"> <a class="pull-left" href="#"><img class="media-object" src="${requestScope.innMainPic.filePath}" height="150" width="150"></td>
							</tr>
							<tr>
								<td class="col-md-3">체크인 날짜</td>
								<td class="col-md-4">
								${requestScope.bookInfo.bookCheckIn}
								</td>	
							</tr>
							<tr>
							<td class="col-md-3">체크아웃 날짜</td>
								<td class="col-md-4">
								${requestScope.bookInfo.bookCheckOut}
								</td>
								</tr>
								<tr>
									<tr>
							<td class="col-md-3">숙박 인원</td>
								<td class="col-md-4">
								${requestScope.bookInfo.bookCount}
								</td>
								</tr>
								<td class="col-md-3">총 금액</td>
								<td class="col-md-4">
								${requestScope.payTotalPrice}
								</td>
							</tr>
						</tbody>
					</table>
					</div>
					<div class="col-md-6">
					<c:choose>
		<c:when test="${requestScope.masterVO==null}">
</c:when>
<c:otherwise>
<table class="table">
<tr>
<td>은행</td><td>계좌명</td><td>계좌번호</td>
</tr>
<tr><td>${requestScope.masterVO.memberBank}</td><td>${requestScope.masterVO.memberName}</td><td>${requestScope.masterVO.memberBankAcount}</td></tr>
</table>

</c:otherwise>
					
					
					</c:choose>
					</div>

			</div>
		</div>
	</div>
</div>
   
            </c:otherwise>
            </c:choose>

            </div>
          </div>
        </div>
      </div>
   