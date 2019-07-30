<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품관리</title>
</head>
<body>
<%@ include file="/WEB-INF/views/comm/menu.jsp" %>
<h1>상품목록</h1>
	<table border="1">
		<thead>
			<tr><th>상품번호</th><th>상품명</th><th>가격</th></tr>
		</thead>
		<tbody>
			<c:forEach items="${prodList}" var="vo">
				<tr>
					<td><a href="${pageContext.request.contextPath}/prod/edit.do?prodNo=${vo.prodNo}">${vo.prodNo}</a></td>
					<td>${vo.prodName}</td>
					<td>${vo.prodPrice}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
<hr />
<a href="${pageContext.request.contextPath}/prod/add.do">상품추가</a>
</body>
</html>