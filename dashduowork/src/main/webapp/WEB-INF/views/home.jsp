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
	/* function SearchInnCityListByAjax() {		 
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
	}//function SearchInnCityListByAjax() */
	$(function() {
		$("#checkin, #checkout").datepicker({
			dateFormat : 'yy-mm-dd'
		});
	});

</script>
<!--
<div class="section">
<div class="container">
  <div class="row"></div>
</div>
</div>
      <div class="container">
        <div class="row">
          <div class="col-md-12 text-center"  style="position: relative; top: 170px;">
            <h1 class="text-inverse">환영합니다!</h1>
            <p class="text-inverse">지역과 숙박인원을 선택해 주세요. 날짜는 선택사항 입니다.</p>
            </div>
            <div  style="position: relative; top: 400px;">
            <form action="searchByCityDateNo.do" class="navbar-form navbar-center text-center"
            role="search" id="searchInnCityAjaxForm">
              <input type="text" class="form-control" name="innCity" id="searchCity"
              placeholder="State" size="30" onkeyup="SearchInnCityListByAjax()">
              <input type="text" class="form-control" name="startDate" id="checkin"
              size="15" onfocus="this.value=''" placeholder="Checkin">
              <input type="text" class="form-control" name="endDate" size="15" id="checkout"
              onfocus="this.value=''" placeholder="Checkout">
              <select class="form-control" name="acceptableNo" id="select">
                <option value="1">게스트 1명</option>
                <option value="2">게스트 2명</option>
                <option value="3">게스트 3명</option>
                <option value="4">게스트 4명</option>
                <option value="5">게스트 5명</option>
                <option value="6">게스트 6명</option>
              </select>
              <button type="submit" class="btn btn-default">검색</button>
              <br>
            </form>
          </div>
        </div>
      </div>
-->

<video id="videobcg" preload="auto" autoplay="true" loop="loop" muted="muted" volume="0">
     <source src="https://a0.muscache.com/airbnb/static/Paris-P1-1.mp4" type="video/mp4">
     <source src="https://a0.muscache.com/airbnb/static/Paris-P1-0.webm" type="video/webm">
</video>
<div class="section">
	<div class="container">
		<div class="row"></div>
	</div>
</div>
<div class="container">
  <div class="row">
    <div class="col-md-12 text-center"  style="color: white; position: relative; top: 300px;">
      <h1 class="text-inverse">환영합니다!</h1>
      <p class="text-inverse">지역과 숙박인원을 선택해 주세요. 날짜는 선택사항 입니다.</p>
      </div>
      <div style="position: relative; top: 500px;">
      <form action="searchInnByWordDateNo.do" class="navbar-form navbar-center text-center"
      role="search" id="searchInnCityAjaxForm">

        <input type="text" class="form-control" name="searchWord" id="searchCity"
        placeholder="State" size="30" onkeyup="SearchInnCityListByAjax()">
        <input type="text" class="form-control" name="searchStartDate" id="checkin"
        size="15" onfocus="this.value=''" placeholder="Checkin">
        <input type="text" class="form-control" name="searchEndDate" size="15" id="checkout"
        onfocus="this.value=''" placeholder="Checkout">
        <select class="form-control" name="searchPeopleNo" id="select">
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
  </div>
</div>