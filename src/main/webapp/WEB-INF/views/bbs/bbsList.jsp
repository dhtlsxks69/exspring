<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 날짜나 숫자 데이터의 파싱/포맷팅을 담당하는 JSTL 태그 라이브러리 사용 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- EL (${}) 내에서 사용가능한 함수들을 제공해주는 JSTL 태그 라이브러리 사용 --%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
</head>
<body>
<%@ include file="/WEB-INF/views/comm/menu.jsp" %>
	<h1>게시글 목록</h1>
	<table border="1">
		<thead>
			<tr><th>번호</th><th>제목</th><th>작성자</th><th>작성일</th></tr>
		</thead>
		<tbody>
			<c:forEach items="${bbsList}" var="vo">
				<tr>
					<td>${vo.bbsNo}</td>
					<td>
<%-- <a href="${pageContext.request.contextPath}/bbs/edit.do?bbsNo=${vo.bbsNo}">${vo.bbsTitle}</a> --%>
						<a href="${pageContext.request.contextPath}/bbs/edit.do?bbsNo=${vo.bbsNo}">${vo.bbsTitle}
							<c:out value="${vo.bbsTitle}" />
							<%-- ${fn:escapeXml(vo.bbsTitle)} --%>
						</a>
<%-- <c:out value="${xxx}" /> 또는 ${fn:escapeXml(vo.bbsTitle)} 를 이용하여 출력하면, --%>
<!-- 기본적으로 태그 괄호문자를 특수문자로 변환하여 출력해준다. -->
					</td>
					<td><c:out value="${vo.bbsWriter}" /></td>
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