<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
<c:when test="${requestScope.list.size()==0}" >
검색실패
</c:when>
<c:otherwise>
<c:forEach var="aa" items="${requestScope.list}">
${aa}
</c:forEach>
</c:otherwise>
</c:choose>


