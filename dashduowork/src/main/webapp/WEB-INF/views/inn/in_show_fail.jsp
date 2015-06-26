<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<input type="hidden" value="${requestScope.message}" id="error">
<script type="text/javascript">
	alert($("#error").val());
	location.href="get_mytradelist.do";
</script>