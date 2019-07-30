<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원관리</title>
</head>
<body>
<%@ include file="/WEB-INF/views/comm/menu.jsp" %>
<h1>회원가입</h1>
<form action="${pageContext.request.contextPath}/member/add.do" method="POST">
아이디 : <input type="text" name="memId" /> <br /> 
비밀번호 : <input type="password" name="memPass" /> <br /> 
이름 : <input type="text" name="memName" /> <br /> 
포인트 : <input type="text" name="memPoint" /> <br /> 
<input type="submit" />
</form>
<hr />
<a href="${pageContext.request.contextPath}/member/list.do">목록으로 이동</a>
</body>
</html>