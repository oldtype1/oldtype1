<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	alert("이미 즐겨찾기에 올라와있어요.");
location.href="inn_in_show.do?innNo=${requestScope.innNo}";
</script>
<%-- ${requestScope.innNo}
<input type="hidden" id="innNumber" value="${requestScope.innNo}"> --%>