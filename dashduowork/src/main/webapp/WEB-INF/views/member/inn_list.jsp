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
                  <a href="#">내프로필</a>
                </li>
                <li>
                  <a href="#">숙소등록</a>
                </li>
                <li>
                  <a href="#">예약숙소</a>
                </li>
                <li>
                  <a href="#">거래내역</a>
                </li>
                <li>
                  <a href="#">위시리스트</a>
                </li>
                <li>
                  <a href="#">쪽지</a>
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
                <img src="http://pingendo.github.io/pingendo-bootstrap/assets/user_placeholder.png"
                class="img-circle img-responsive" width="50" height="70">
              </center>
            </div>
            <div class="col-md-5">
              <h2>${sessionScope.mvo.memberName}님 등록한 숙소 ${requestScope.lvo.pagingBean.totalContents}개</h2>
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
                
                <c:forEach var="ivo" items="${requestScope.lvo.list}">
                  <tr>
                    <td class="col-md-1 text-center" draggable="true">
                      <h4>${ivo.innNo}</h4>
                    </td>
                    <td class="col-md-1">
                      <a class="pull-left" href="#"><img class="media-object" src="http://pingendo.github.io/pingendo-bootstrap/assets/placeholder.png" height="150" width="150">
                  </a>
                    </td>
                    <td class="col-md-7">
                      <h3 class="text-center">${ivo.innName}</h3>
                      <p class="text-center">${ivo.innInfo}</p>
                    </td>
                    <td class="col-md-1">
                      <h4 class="text-center"><a href="innupdate.do?innno=${ivo.innNo}">수정</a></h4>
                    </td>
                    <td class="col-md-1">
                      <h4 class="text-center"><a href="inndelete.do?innno=${ivo.innNo}">삭제</a></h4>
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
                  
                  <c:set var="pb" value="${requestScope.lvo.pagingBean}"></c:set>
						
						<c:if test="${pb.previousPageGroup}">
						<li><a
							href="get_myinnlist.do?pageNo=${pb.startPageOfPageGroup-1}">Prev</a>
						</li>
						</c:if>
						
						<c:forEach var="i" begin="${pb.startPageOfPageGroup}"
							end="${pb.endPageOfPageGroup}">
							<c:if test="${pb.nowPage!=i}">
								<li><a href="get_myinnlist.do?pageNo=${i}">${i}</a>
								<li>
							</c:if>
							<c:if test="${pb.nowPage==i}">
								<li><a href="#">${i}</a></li>
							</c:if>
						&nbsp;					
						</c:forEach>
			
					<c:if test="${pb.nextPageGroup}">
						<li>
							<a href="get_myinnlist.do?pageNo=${pb.endPageOfPageGroup+1}">Next</a>
						</li>
					</c:if>

              </ul>
            </div>
          </div>
        </div>
      </div>




