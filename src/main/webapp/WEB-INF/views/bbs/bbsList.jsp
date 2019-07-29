<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
</head>
<body>
	<h1>게시글 목록</h1>
	<table border="1">
		<thead>
			<tr><th>번호</th><th>제목</th><th>작성자</th><th>작성일</th></tr>
		</thead>
		<tbody>
			<c:forEach items="${bbsList}" var="vo">
				<tr>
					<td>${vo.bbsNo}</td>
					<td><a href="${pageContext.request.contextPath}/bbs/edit.do?bbsNo=${vo.bbsNo}">${vo.bbsTitle}</a></td>
					<td>${vo.bbsWriter}</td>
					<td><fmt:formatDate value="${vo.bbsRegDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<!-- 날짜/시간 값을 원하는 형태의 문자열로 출력하고 싶을 때 fmt:formatDate 태그를 사용 -->
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<hr />
	<a href="${pageContext.request.contextPath}/bbs/add.do"><button>글쓰기</button></a>
</body>
</html>