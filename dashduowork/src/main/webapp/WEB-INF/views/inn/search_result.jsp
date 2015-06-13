<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script>
	$(function() {
		$("#checkin, #checkout").datepicker({
			dateFormat : 'yy-mm-dd'
		});
	});
</script>
<div class="section">
	<div class="container">
	<form action="searchByCityDateNo.do" class="navbar-form navbar-left" role="search">
			<input type="text" class="form-control" name="innCity" placeholder="State" style="width: 300px;"><br>
			<input
			type="text" class="form-control" name="startDate" id="checkin" size="15"
			onfocus="this.value=''" placeholder="Checkin" style="width: 150px;"> <input type="text"
			class="form-control" name="endDate" size="15" id="checkout"
			onfocus="this.value=''" placeholder="Checkout" style="width: 150px;">
			</form>
	</div>
</div>
<div class="section">
	<div class="container">
			<form id="searchForm" action="selectInnByCheckedAmenity.do">	
				<input type="checkbox" name="amenityWiFi" value="Y">WiFi <input
					type="checkbox" name="amenityBed" value="Y">Bed <input
					type="checkbox" name="amenityTV" value="Y">TV <input
					type="checkbox" name="amenityKitchen" value="Y">주방 <input
					type="checkbox" name="amenityBBQ" value="Y">바베큐 <input
					type="button" id="searchBtn" value="검색">
			</form>
	</div>
</div>
<br>
<br>
<br>
<br>
<div class="section">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<table class="table">
					<thead>
						<tr>
							<th >숙소 번호</th>
							<th>숙소명</th>
							<th>지역(대)</th>
							<th>지역(중)</th>
							<th>상세 주소</th>
							<th>유형</th>
							<th>수용가능인원</th>
							<th>가격</th>
						</tr>
					</thead>
					<tbody>
					<c:choose>
					<c:when test="${requestScope.list.size()==0}" >
					<tr align="center"><td colspan="8">검색결과가 존재하지 않습니다.</td></tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="list" items="${requestScope.list}">
							<tr>
								<td><!-- <div id="showInnNo"></div> -->${list.innNo }</td>
								<td><!-- <div id="showInnName"></div> -->${list.innName }</td>
								<td><!-- <div id="showInnCity"></div> -->${list.innCity }</td>
								<td><!-- <div id="showInnArea"></div> -->${list.innArea }</td>
								<td><!-- <div id="showInnInfo"></div> -->${list.innAddress }</td>
								<td><!-- <div id="showInnType"></div> -->${list.innType }</td>
								<td><!-- <div id="showInnAcceptable"></div> -->${list.acceptableNo }</td>
								<td><!-- <div id="showInnPrice"></div> -->${list.innPrice }</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>

