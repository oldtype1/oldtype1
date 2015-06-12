<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="http://code.jquery.com/jquery-1.11.3.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#searchBtn").click(function() {
			$("#searchForm").submit();
		});

		$("#searchForm").change(function() {
			$.ajax({
				type : "Post",
				url : "selectInnByCheckedAmenity.do",
				data : $("#searchForm").serialize(),
				dataType : "json",
				success : function(innInfoList) {
					if (innInfoList.length != 0) {
						$.each(innInfoList, function(index, info) {
							$("#showInnNo").html(info.innNo);
							$("#showInnName").html(info.innName);
							$("#showInnCity").html(info.innCity);
							$("#showInnArea").html(info.innArea);
							$("#showInnInfo").html(info.innInfo);
							$("#showInnType").html(info.innType);
							$("#showInnAcceptable").html(info.innAvailability);
							$("#showInnPrice").html(info.innPrice);
						}); //each
					}//if
					else if(innInfoList.length == 0){
						$("#showInnNo").html("");
						$("#showInnName").html("");
						$("#showInnCity").html("");
						$("#showInnArea").html("");
						$("#showInnInfo").html("");
						$("#showInnType").html("");
						$("#showInnAcceptable").html("");
						$("#showInnPrice").html("");s
					}
				}
			});//ajax 			 	 			
		}); //change

	});
</script>
<div class="section">
	<div class="container">
		<div class="row">
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
</div>
<br>
<br>
<br>
<br>
<span id="resultView">
	<html>
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
						<tr>
							<td><div id="showInnNo"></div></td>
							<td><div id="showInnName"></div></td>
							<td><div id="showInnCity"></div></td>
							<td><div id="showInnArea"></div></td>
							<td><div id="showInnInfo"></div></td>
							<td><div id="showInnType"></div></td>
							<td><div id="showInnAcceptable"></div></td>
							<td><div id="showInnPrice"></div></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
	</html>

</span>