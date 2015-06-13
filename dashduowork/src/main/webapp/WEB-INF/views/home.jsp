<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
	$(function() {
		$("#checkin, #checkout").datepicker({
			dateFormat : 'yy-mm-dd'
		});
	});
</script>
<img id="img1" src="${initParam.root}img/house.jpg" style="position: relative; top: 80px; left: 330px;">

<div style="position: relative; top: 150px; left: 230px; opacity: 0.7">
	<form action="searchByCityDateNo.do" class="navbar-form navbar-left" role="search">
		<input type="text" class="form-control" name="innCity" placeholder="State"> <input
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
		<a href="inn_search_form.do">임시숙소검색페이지</a>
	</form>
</div>