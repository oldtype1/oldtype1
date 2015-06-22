<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:choose>
<c:when test="${requestScope.mvo == null}">
	<script type="text/javascript">
		alert("정답이나 질문이 잘못되었습니다!\n다시 확인해주세요");
		location.href="home.do"
	</script>
</c:when>

<c:otherwise>
<input type="hidden" id="password" name="password" value="${requestScope.mvo.memberPass}">
	<script type="text/javascript">
	alert("패스워드는 다음과 같습니다.\n"+$("#password").val());
	location.href="home.do"
	</script>
</c:otherwise>
</c:choose>