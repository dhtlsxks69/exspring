<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>상품 추가</h1>
<form action="${pageContext.request.contextPath}/prod/add.do" method="post">
이름 : <input type="text" name="prodName" /> <br />
가격 : <input type="text" name="prodPrice" /> <br />
<input type="submit" value="저장" />
<a href="${pageContext.request.contextPath}/prod/list.do"><input type="button" value="상품목록" /></a>
</form>
</body>
</html>