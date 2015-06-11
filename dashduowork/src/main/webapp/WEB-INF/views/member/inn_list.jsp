<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
    <script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <link href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css"
    rel="stylesheet" type="text/css">
    <link href="http://pingendo.github.io/pingendo-bootstrap/themes/default/bootstrap.css"
    rel="stylesheet" type="text/css">
  </head>
  
  <body>
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

    <div class="section text-center">
      <div class="container">
        <div class="row">
          <div class="col-md-12 text-center">
            <table class="table ">
              <thead>
                <tr>
                  <th class="active text-center">숙소번호</th>
                  <th class="active text-center">숙소명</th>
                  <th class="active text-center">수정</th>
                  <th class="active text-center">삭제</th>
                </tr>
              </thead>
              <tbody>
              <c:forEach var="ivo" items="${requestScope.lvo.list}">
                <tr>
                  <td class="text-center">${ivo.innNo}</td>
                  <td class="text-center"><a href="innshow.do?innno=${ivo.innNo}">${ivo.innName}</a></td>
                  <td class="text-center"><a href="innupdate.do?innno=${ivo.innNo}">수정</a></td>
                  <td class="text-center"><a href="inndelete.do?innno=${ivo.innNo}">삭제</a></td>
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

						<!-- 페이징빈 -->
						<%--  스타트페이지오브페이지그룹 : ${pb.startPageOfPageGroup}<br>
            엔드트페이지오브페이지그룹 : ${pb.endPageOfPageGroup} --%>

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
</body>

</html>