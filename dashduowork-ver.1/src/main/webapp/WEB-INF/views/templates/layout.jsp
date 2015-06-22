<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="${initParam.root}css/styles.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
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
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"
	type="text/javascript"></script>
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"
	type="text/javascript"></script>


<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css"
	type="text/css" media="all" />
	<!-- 달력 자바스크립트로 추정 -->
</head>
<body>
<div id="container">
	<div id="header"><tiles:insertAttribute name="header"/></div>
	<div id="main"><tiles:insertAttribute name="main"/></div>
	<div id="footer"><tiles:insertAttribute name="footer"/></div>
</div>
<%-- table layout 방식을 css 로 변경 --%>
<%-- <table width="100%" border="1" cellpadding="5">
<tr height="100">
 <td colspan="2">
 	<tiles:insertAttribute name="header"></tiles:insertAttribute>
 </td> 
</tr>
<tr height="400">
 <td width="200" valign="top">
 	<tiles:insertAttribute name="left"></tiles:insertAttribute>
 </td>
 <td valign="top">
 	<tiles:insertAttribute name="main"></tiles:insertAttribute>
 </td>
</tr>
<tr height="70">
 <td colspan="2">
 	<tiles:insertAttribute name="footer"></tiles:insertAttribute>
 </td>
</tr>
</table> --%>
</body>
</html>