<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
$(document).ready(function(){
alert($("#message").val()+$("#checkIn").val()+$("#checkOut").val());
location.href="inn_in_show.do?innNo="+${requestScope.innNo};
	
});
</script>
<input type="hidden" value="${requestScope.result.message}" id="message">
<input type="hidden" value="${requestScope.result.checkIn}" id="checkIn">
<input type="hidden" value="${requestScope.result.checkOut}" id="checkOut">