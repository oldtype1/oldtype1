<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="${initParam.root}css/styles.css">

<script src="http://code.jquery.com/jquery-1.11.3.js"></script>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<!-- 헤더에 마우스 오버시 효과주는 자바스크립트로 추정 -->
<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
   <script src="${initParam.root}script.js"></script>

<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css"
	type="text/css" media="all" />
	<!-- 달력 자바스크립트로 추정 -->
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"
	type="text/javascript"></script>
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"
	type="text/javascript"></script>

<script>
	$(function() {
		$("#checkin, #checkout").datepicker({
			dateFormat : 'yy-mm-dd'
		});
	});
</script>
</head>
<body>
	<c:import url="template/header.jsp"></c:import>

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
</body>
</html>
