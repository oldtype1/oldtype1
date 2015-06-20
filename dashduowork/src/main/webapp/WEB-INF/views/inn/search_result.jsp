<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script>
$(document).ready(function(){
	$("#searchInnCityAjaxForm").submit(function(){
		if($("#searchCity").val()==""){
			alert("지역을 입력해주세요.");
			return false;
		}
	});
});
function SearchInnCityListByAjax() {		 
	var availableTags = []; //자동 완성 해당 단어 저장할 배열(객체) 선언 / 항상 초기화 되어야함
	$.ajax({ //db에 저장되어있는 값들 ajax로 반환
		type : "post",
		url : "searchCityAuto.do",
		data : $("#searchInnCityAjaxForm").serialize(),
		dataType : "json",
		success : function(data) {
			$.each(data,function(index,result){
				if(data.length!=0){
					availableTags.push(result.innArea);
				}					
			});				
		}//success
	});//ajax
	
	
	$("#searchCity").autocomplete({//자동완성 기능을 searchCity id에 부여한다.
		source : availableTags //자동완성 소스는 availableTags 배열을 사용 한다.		
	});
	}//function SearchInnCityListByAjax()
$(function() {
	$( "#slider-range" ).slider({
	      range: true,
	      min: 0,
	      max: 10000,
	      values: [ 1000, 8000 ],
	      slide: function( event, ui ) {
	        $( "#amount" ).val( "₩" + ui.values[ 0 ] + " - ₩" + ui.values[ 1 ] );
	        $("#minPrice").val($( "#slider-range" ).slider( "values", 0 ));
	        $("#maxPrice").val($( "#slider-range" ).slider( "values", 1 ));
	      }
	    });
	    $( "#amount" ).val( "₩" + $( "#slider-range" ).slider( "values", 0 ) + " - ₩" + $( "#slider-range" ).slider( "values", 1 ) );
	    
	    $("#slider-range").mouseup(function(){
	    	/* alert($("#minPrice").val()); */
	    	$("#resultViewSearch").html("");
			//alert();list.getInnN
			$.ajax({
				type : "Post",
				url : "selectInnByCheckedAmenity.do",
				data : $("#filterForm").serialize(),
				dataType : "json",
				success : function(innInfoList) {
					var tableInfo="<table class='table'><thead><tr><th >숙소 번호</th><th>숙소 사진</th><th>숙소명</th><th>위치</th><th>유형</th><th>수용가능인원</th><th>가격</th></tr></thead>";
					tableInfo+="<tbody>";
					if (innInfoList.innList.length != 0) {			
						$.each(innInfoList.innList, function(index, info) {
							if(info.innMainPic==null){
								tableInfo+="<tr><td>"+info.innNo+"</td><td><img class='media-object' src='${initParam.root}img/no_img.gif' height='150' width='150'></td><td><a href='inn_in_show.do?innNo="+info.innNo+"'>"+info.innName+"</td><td>"+info.innArea+"</td><td>"+info.innType+"</td><td>"+info.acceptableNo+"</td><td>"+info.innPrice+"</td></tr>";
							}else{
								tableInfo+="<tr><td>"+info.innNo+"</td><td><img class='media-object' src='"+info.innMainPic.filePath+"' height='150' width='150'></td><td><a href='inn_in_show.do?innNo="+info.innNo+"'>"+info.innName+"</td><td>"+info.innArea+"</td><td>"+info.innType+"</td><td>"+info.acceptableNo+"</td><td>"+info.innPrice+"</td></tr>";
							}
						}); //each
					}//if
					else if(innInfoList.innList.length == 0){
						tableInfo+="<tr><td colspan='9' align='center'>검색 및 필터조건에 해당하는 숙소가 존재하지 않습니다.</td></tr>"
					}
					tableInfo+="</tbody></table>";
					$("#resultViewSearch").html(tableInfo);
				}
			});//ajax
	    });//mouseup
	    
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
					var tableInfo="<table class='table'><thead><tr><th >숙소 번호</th><th>숙소 사진</th><th>숙소명</th><th>위치</th><th>유형</th><th>수용가능인원</th><th>가격</th></tr></thead>";
					tableInfo+="<tbody>";
					if (innInfoList.innList.length != 0) {
						$.each(innInfoList.innList, function(index, info) {
							if(info.innMainPic==null){
								tableInfo+="<tr><td>"+info.innNo+"</td><td><img class='media-object' src='${initParam.root}img/no_img.gif' height='150' width='150'></td><td><a href='inn_in_show.do?innNo="+info.innNo+"'>"+info.innName+"</td><td>"+info.innArea+"</td><td>"+info.innType+"</td><td>"+info.acceptableNo+"</td><td>"+info.innPrice+"</td></tr>";
							}else{
								tableInfo+="<tr><td>"+info.innNo+"</td><td><img class='media-object' src='"+info.innMainPic.filePath+"' height='150' width='150'></td><td><a href='inn_in_show.do?innNo="+info.innNo+"'>"+info.innName+"</td><td>"+info.innArea+"</td><td>"+info.innType+"</td><td>"+info.acceptableNo+"</td><td>"+info.innPrice+"</td></tr>";
							}
						}); //each
					}//if
					else if(innInfoList.innList.length == 0){
						tableInfo+="<tr><td colspan='7' align='center'>검색 및 필터조건에 해당하는 숙소가 존재하지 않습니다.</td></tr>"
					}
					tableInfo+="</tbody></table>";
					$("#resultViewSearch").html(tableInfo);
				}
			});//ajax
		}); //change
	});
</script>

<div class="section" style="position: relative; top: 30px;">
	<div class="container">
		<div class="row">
		
		
			<div class="col-md-6" style="top: 45px;">
				<form action="searchByCityDateNo.do" class="navbar-form navbar-left" role="search" id="searchInnCityAjaxForm">
					<input type="text" class="form-control" name="innCity" id="searchCity" placeholder="State" size="54"	onkeyup="SearchInnCityListByAjax()"><br>
						<input
						type="text" class="form-control" name="startDate" id="checkin" size="15"
						onfocus="this.value=''" placeholder="Checkin" style="width: 150px;"> <input type="text"
						class="form-control" name="endDate" size="15" id="checkout"
						onfocus="this.value=''" placeholder="Checkout" style="width: 150px;">
						<select class="form-control" name="acceptableNo" id="select">
						<option value="1">게스트 1명</option>
						<option value="2">게스트 2명</option>
						<option value="3">게스트 3명</option>
						<option value="4">게스트 4명</option>
						<option value="5">게스트 5명</option>
						<option value="6">게스트 6명</option>
					</select>
					<button type="submit" class="btn btn-default">검색</button>
				</form>
			</div>
			
			<div class="col-md-6" style="top: 45px;">
				<form id="filterForm" action="selectInnByCheckedAmenity.do">	
					<p>
					  <label for="amount">가격 범위:</label>
					  <input type="text" id="amount" readonly style="border:0; color:black; font-weight:bold;">
					</p>
					<div id="slider-range" style="width: 500px;"></div>
					<input type="checkbox" name="amenityWiFi" value="Y">WiFi <input
						type="checkbox" name="amenityBed" value="Y">Bed <input
						type="checkbox" name="amenityTV" value="Y">TV <input
						type="checkbox" name="amenityKitchen" value="Y">주방 <input
						type="checkbox" name="amenityBBQ" value="Y">바베큐
						<input type="hidden" name="firstSearchCity" value="${requestScope.searchVO.innCity }">
						<input type="hidden" name="firstSearchStartDate" value="${requestScope.searchVO.startDate }">
						<input type="hidden" name="firstSearchEndDate" value="${requestScope.searchVO.endDate }">
						<input type="hidden" name="firstSearchPeopleNo" value="${requestScope.searchVO.acceptableNo }">
						<input type="hidden" name="minPrice" id="minPrice" value="0">
						<input type="hidden" name="maxPrice" id="maxPrice" value="999999">
				</form>			
			</div>
			
			
		</div>
	</div>
</div>
<br>
<br>
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
								<th>숙소 사진</th>
								<th>숙소명</th>
								<th>위치</th>
								<th>유형</th>
								<th>수용가능인원</th>
								<th>가격</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${requestScope.list.size()==0}" >
										<tr><td colspan='7' align="center">검색결과가 존재하지 않습니다.</td></tr>
								</c:when>
								<c:otherwise>
									<c:forEach var="list" items="${requestScope.innListVO.innList}">
										<tr valign="middle">
											<td>${list.innNo }</td>
											<td>
											<c:choose>
												<c:when test="${list.innMainPic.filePath!=null}"><img class="media-object" src="${list.innMainPic.filePath}" height="150" width="150"></c:when>
												<c:otherwise><img class="media-object" src="${initParam.root}img/no_img.gif" height="150" width="150"></c:otherwise>
											</c:choose>
											</td>
											<td><a href="inn_in_show.do?innNo=${list.innNo}">${list.innName }</a></td>
											<td>${list.innArea }</td>
											<td>${list.innType }</td>
											<td>${list.acceptableNo }</td>
											<td>${list.innPrice }</td>
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
</div>

