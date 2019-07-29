<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
</head>
<body>
	<h1>글쓰기</h1>
	<form action="${pageContext.request.contextPath}/bbs/add.do" method="post">
	<table border="1">
		<tbody>
		<tr><td>제목</td><td><input type="text" name="bbsTitle" /></td></tr>
		<tr><td>내용</td><td><textarea rows="20" cols="200" name="bbsContent"></textarea></td></tr>
		<tr><td>작성자</td><td><input type="text" name="bbsWriter" /></td></tr>
		</tbody>
	</table>
		<input type="submit" value="저장" />
		<a href="${pageContext.request.contextPath}/bbs/list.do"><input type="button" value="게시글목록" /></a>
	</form>
</body>
</html>