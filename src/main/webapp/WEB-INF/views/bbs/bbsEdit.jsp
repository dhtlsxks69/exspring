<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
</head>
<body>
<%@ include file="/WEB-INF/views/comm/menu.jsp" %>
	<h1>글 상세정보</h1>
	<form action="${pageContext.request.contextPath}/bbs/edit.do" method="post">
	<input type="hidden" name="bbsNo" value="${bbsVo.bbsNo}" />
	<table border="1">
		<tbody>
		<tr><td>제목</td><td><input type="text" name="bbsTitle" value="${bbsVo.bbsTitle}" ${loginUser.memId != bbsVo.bbsWriter ? 'disabled' : ''} /></td></tr>
		<tr><td>내용</td><td><textarea rows="20" cols="80" name="bbsContent" ${loginUser.memId != bbsVo.bbsWriter ? 'disabled' : ''} >${bbsVo.bbsContent}</textarea></td></tr>
		<tr><td>작성자</td><td><input type="text" name="bbsWriter" value="${bbsVo.bbsWriter}" disabled="disabled" /></td></tr>	
		</tbody>
	</table>
	<c:if test="${loginUser.memId == bbsVo.bbsWriter}">
		<input type="submit" value="저장" />
		<a href="${pageContext.request.contextPath}/bbs/del.do?bbsNo=${bbsVo.bbsNo}"><input type="button" value="게시글삭제" /></a>
	</c:if>
		<a href="${pageContext.request.contextPath}/bbs/list.do"><input type="button" value="게시글목록" /></a>
	</form>
</body>
</html>