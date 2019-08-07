<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
</head>
<body>
<%-- <%@ include file="/WEB-INF/views/comm/menu.jsp" %> --%>
	<h1>글 상세정보</h1>
	<form action="${pageContext.request.contextPath}/bbs/edit.do" method="post">
	<input type="hidden" name="bbsNo" value="${bbsVo.bbsNo}" />
	<table border="1">
		<tbody>
		<tr><td>제목</td><td><input type="text" name="bbsTitle" value="${bbsVo.bbsTitle}" ${loginUser.memId != bbsVo.bbsWriter ? 'disabled' : ''} /></td></tr>
		<tr><td>내용</td><td><textarea rows="20" cols="80" name="bbsContent" ${loginUser.memId != bbsVo.bbsWriter ? 'disabled' : ''} >${bbsVo.bbsContent}</textarea></td></tr>
		<tr><td>작성자</td><td><input type="text" name="bbsWriter" value="${bbsVo.bbsWriter}" disabled="disabled" /></td></tr>
		<!-- c:forEach의 현재 반복 상태를 저장할 변수 이름을 varStatus 속성값으로 지정 -->
		<c:forEach var="avo" items="${bbsVo.attList}" varStatus="stat">
			<tr><td>첨부파일${stat.count}</td><td><a href="${pageContext.request.contextPath}/bbs/down.do?attNo=${avo.attNo}">${avo.attOrgName}</a></td></tr>
		</c:forEach>
		</tbody>
	</table>
	<c:if test="${loginUser.memId == bbsVo.bbsWriter}">
		<input type="submit" value="저장" />
		<a href="${pageContext.request.contextPath}/bbs/del.do?bbsNo=${bbsVo.bbsNo}"><input type="button" value="게시글삭제" /></a>
	</c:if>
		<a href="${pageContext.request.contextPath}/bbs/list.do"><input type="button" value="게시글목록" /></a>
	</form>
	
	<hr />
	<form action="" id="repForm">
		<textarea rows="5" cols="100" name="repContent"></textarea>
		<input type="button" value="저장" id="repAddBtn" />
		<input type="hidden" name="repBbsNo" value="${bbsVo.bbsNo}" />
	</form>
	<script src="${pageContext.request.contextPath}/resources/js/jquery-3.4.1.js"></script>
	<script>
	$('#repAddBtn').on('click', function () {
		$.ajax({
	        url: '${pageContext.request.contextPath}/reply/add.do', //요청주소
	        method: 'POST', //요청방식(GET,POST,PUT,DELETE,...)
	        data : 'repBbsNo=' + $('[name="repBbsNo"]').val() + '&repContent=' + $('[name="repContent"]').val(), //요청시 함께 보낼 데이터(파라미터)
	        dataType: 'json' //요청의 응답으로 받을 데이터의 타입(text,html,json,xml,...)
	    })
	    .done(function (data, status, jqXHR) {
	        //요청을 보내고 성공적으로 응답을 받았을 때 실행될 함수
	        //data: 응답으로 받은 데이터, status: 응답의 상태, jqXHR: 요청객체
	        console.log('요청성공 : ');
	        // dataType을 'json'으로 지정하면, jQuery가 응답을 JSON으로 파싱하여 data인자에 전달
	        // data = JSON.parse(data); //JSON 문자열을 자바스크립트 객체로 변환
	        console.log(data)
	    })
	    .fail(function (jqXHR, status, error) {
	        //응답을 받는데 실패했을 때 실행될 함수
	        //jqXHR: 요청객체, status: 응답상태, error: 발생한 오류
	        console.log('요청실패 : ');
	    });
	});
	</script>
</body>
</html>