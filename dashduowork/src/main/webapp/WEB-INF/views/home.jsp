<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
					availableTags.push(result.innCity);
				}					
			});				
		}//success
	});//ajax
	
	
	$("#searchCity").autocomplete({//자동완성 기능을 searchCity id에 부여한다.
		source : availableTags //자동완성 소스는 availableTags 배열을 사용 한다.		
	});
	}//function SearchInnCityListByAjax()
	$(function() {
		$("#checkin, #checkout").datepicker({
			dateFormat : 'yy-mm-dd'
		});
	});

</script>
<img id="img1" src="${initParam.root}img/house.jpg" style="position: relative; top: 80px; left: 330px;">

<div style="position: relative; top: 150px; left: 230px; opacity: 0.7">
	<form action="searchByCityDateNo.do" class="navbar-form navbar-left" role="search" id="searchInnCityAjaxForm">
		<input type="text" class="form-control" name="innCity" id="searchCity" placeholder="State" size="30"	onkeyup="SearchInnCityListByAjax()">  <input
			type="text" class="form-control" name="startDate" id="checkin" size="15"
			onfocus="this.value=''" placeholder="Checkin"> <input type="text"
			class="form-control" name="endDate" size="15" id="checkout"
			onfocus="this.value=''" placeholder="Checkout"> <select
			class="form-control" name="acceptableNo" id="select">
			<option value="1">게스트 1명</option>
			<option value="2">게스트 2명</option>
			<option value="3">게스트 3명</option>
			<option value="4">게스트 4명</option>
			<option value="5">게스트 5명</option>
		</select>
		<button type="submit" class="btn btn-default">검색</button><br>
		<!-- <a href="inn_search_form.do">임시숙소검색페이지</a> -->
	</form>
</div>