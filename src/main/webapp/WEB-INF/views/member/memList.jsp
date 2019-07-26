<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원관리</title>
</head>
<body>
<h1>회원목록</h1>
<c:forEach items="${memberList}" var="vo">
	<a href="${pageContext.request.contextPath}/member/edit.do?memId=${vo.memId}">${vo.memId}</a> : ${vo.memName} <br />
</c:forEach>
</body>
</html>