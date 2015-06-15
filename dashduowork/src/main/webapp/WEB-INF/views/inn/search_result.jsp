<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script>
	$(function() {
		$("#checkin, #checkout").datepicker({
			dateFormat : 'yy-mm-dd'
		});
		$("#filterForm").change(function() {
			$("#resultViewSearch").html("");
			//alert();
			$.ajax({
				type : "Post",
				url : "selectInnByCheckedAmenity.do",
				data : $("#filterForm").serialize(),
				dataType : "json",
				success : function(innInfoList) {
					var tableInfo="<table class='table'><thead><tr><th >숙소 번호</th><th>숙소명</th><th>지역(대)</th><th>지역(중)</th><th>상세 주소</th><th>유형</th><th>수용가능인원</th><th>가격</th></tr></thead>";
					tableInfo+="<tbody>";
					if (innInfoList.length != 0) {
						$.each(innInfoList, function(index, info) {
							tableInfo+="<tr><td>"+info.innNo+"</td><td>"+info.innName+"</td><td>"+info.innCity+"</td><td>"+info.innArea+"</td><td>"+info.innAddress+"</td><td>"+info.innType+"</td><td>"+info.acceptableNo+"</td><td>"+info.innPrice+"</td></tr>";
						}); //each
					}//if
					else if(innInfoList.length == 0){
						tableInfo+="<tr><td colspan='8'>검색결과가 존재하지 않습니다.</td></tr>"
					}
					tableInfo+="</tbody></table>";
					$("#resultViewSearch").html(tableInfo);
				}
			});//ajax 			 	 			
		}); //change
	});
</script>
<div class="section">
	<div class="container">
	<form action="searchByCityDateNo.do" id="searchForm" class="navbar-form navbar-left" role="search">
			<input type="text" class="form-control" name="innCity" placeholder="State" style="width: 300px;"><br>
			<input
			type="text" class="form-control" name="startDate" id="checkin" size="15"
			onfocus="this.value=''" placeholder="Checkin" style="width: 150px;"> <input type="text"
			class="form-control" name="endDate" size="15" id="checkout"
			onfocus="this.value=''" placeholder="Checkout" style="width: 150px;"><select
			class="form-control" name="acceptableNo" id="select">
			<option value="1">게스트 1명</option>
			<option value="2">게스트 2명</option>
			<option value="3">게스트 3명</option>
			<option value="4">게스트 4명</option>
			<option value="5">게스트 5명</option>
		</select>
			<button type="submit" class="btn btn-default">검색</button>
			</form>
	</div>
</div>
<div class="section">
	<div class="container">
			<form id="filterForm" action="selectInnByCheckedAmenity.do">	
				<input type="checkbox" name="amenityWiFi" value="Y">WiFi <input
					type="checkbox" name="amenityBed" value="Y">Bed <input
					type="checkbox" name="amenityTV" value="Y">TV <input
					type="checkbox" name="amenityKitchen" value="Y">주방 <input
					type="checkbox" name="amenityBBQ" value="Y">바베큐
					<input type="hidden" name="firstSearchCity" value="${requestScope.searchVO.innCity }">
					<input type="hidden" name="firstSearchStartDate" value="${requestScope.searchVO.startDate }">
					<input type="hidden" name="firstSearchEndDate" value="${requestScope.searchVO.endDate }">
					<input type="hidden" name="firstSearchPeopleNo" value="${requestScope.searchVO.acceptableNo }">
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
			지역 : ${requestScope.searchVO.innCity} / 인원 : ${requestScope.searchVO.acceptableNo } 명 에 대한 검색결과
			<div id="resultViewFilter"></div>
				<div id="resultViewSearch">
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
						<c:forEach var="list" items="${requestScope.list}">
								<tr>
									<td>${list.innNo }</td>
									<td>${list.innName }</td>
									<td>${list.innCity }</td>
									<td>${list.innArea }</td>
									<td>${list.innAddress }</td>
									<td>${list.innType }</td>
									<td>${list.acceptableNo }</td>
									<td>${list.innPrice }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>

