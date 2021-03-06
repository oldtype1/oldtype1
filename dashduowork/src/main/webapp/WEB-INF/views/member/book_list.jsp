<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
    <script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <link href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css"
    rel="stylesheet" type="text/css">
<!--     <link href="http://pingendo.github.io/pingendo-bootstrap/themes/default/bootstrap.css"
    rel="stylesheet" type="text/css">
  -->
    <script  type="text/javascript">
	function deleteItem(bookNo){
		if(confirm("해당 숙소를 예약 취소하시겠습니까?")){
			location.href="bookdelete.do?bookNo="+bookNo;
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
                class="img-circle img-responsive" style="margin-top: 60px; margin-bottom: 50px; " width="50" height="70" >
              </center>
            </div>
            <div class="col-md-5" style="margin-top: 50px; margin-bottom: 50px; ">
              <h2>${sessionScope.mvo.memberName}님  예약한 숙소 ${requestScope.blvo.pagingBean.totalContents}개</h2>
            </div>
          </div>
        </div>
      </div>
      
      
      
      <div class="section text-center">
        <div class="container">
          <div class="row">
            <div class="col-md-15 text-center">
              <table class="table">
              <thead>
              <tr>
              <td>예약 번호</td>
              <td>숙소 사진</td>
              <td>숙소 이름</td>
              <td>체크인 날짜</td>
              <td>체크아웃 날짜</td>
              <td>인원수</td>
              <td>예약 취소</td>
              </tr>
              </thead>
                <tbody>
                
                <c:forEach var="bvo" items="${requestScope.blvo.list}">
                  <tr>
                    <td class="col-md-1 text-center" draggable="true">
                      <h4>${bvo.bookNo}</h4>
                    </td>
                    <td class="col-md-1">
                        <c:if test="${bvo.bookMainPic!=null}">
                      <a class="pull-left" href="#"><img class="media-object" src="${bvo.bookMainPic.filePath}" height="150" width="150">
                  </a>
                  </c:if>
                  <c:if test="${bvo.bookMainPic==null}">
                      <a class="pull-left" href="#"><img class="media-object" src="${initParam.root}img/no_img.gif"  height="150" width="150">
                  </a>
                  </c:if> 
                    </td>
                    <td class="col-md-1">
                     <a href="inn_in_show.do?innNo=${bvo.innNo}"><h4 class="text-center">${bvo.innName}</h4></a>
                    </td>
                    <td class="col-md-1">
                      <h4 class="text-center">${bvo.bookCheckIn}</h4>
                    </td>
                    <td class="col-md-1">
                      <h4 class="text-center">${bvo.bookCheckOut}</h4>
                    </td>
                      <td class="col-md-1">
                      <h4 class="text-center">${bvo.bookCount}</h4>
                    </td>
                     <td class="col-md-1">
                   	<input type="button"  class="btn btn-default" value="예약 취소" id="deleteBtn"
				    	onclick="deleteItem('${bvo.bookNo}')">
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
                  
                  <c:set var="pb" value="${requestScope.blvo.pagingBean}"></c:set>
						
						<c:if test="${pb.previousPageGroup}">
						<li><a
							href="get_mybooklist.do?pageNo=${pb.startPageOfPageGroup-1}">Prev</a>
						</li>
						</c:if>
						
						<c:forEach var="i" begin="${pb.startPageOfPageGroup}"
							end="${pb.endPageOfPageGroup}">
							<c:if test="${pb.nowPage!=i}">
								<li><a href="get_mybooklist.do?pageNo=${i}">${i}</a>
								<li>
							</c:if>
							<c:if test="${pb.nowPage==i}">
								<li><a href="#">${i}</a></li>
							</c:if>
						&nbsp;					
						</c:forEach>
			
					<c:if test="${pb.nextPageGroup}">
						<li>
							<a href="get_mybooklist.do?pageNo=${pb.endPageOfPageGroup+1}">Next</a>
						</li>
					</c:if>

              </ul>
            </div>
          </div>
        </div>
      </div>




