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
	<form class="navbar-form navbar-left" role="search">
		<input type="text" class="form-control" placeholder="State"> <input
			type="text" class="form-control" id="checkin" size="15"
			onfocus="this.value=''" value="체크인"> <input type="text"
			class="form-control" size="15" id="checkout"
			onfocus="this.value=''" value="체크아웃"> <select
			class="form-control" id="select">
			<option>숙박인원</option>
			<option>1</option>
			<option>2</option>
			<option>3</option>
			<option>4</option>
			<option>5</option>
		</select>
		<button type="submit" class="btn btn-default">검색</button>
	</form>
</div>