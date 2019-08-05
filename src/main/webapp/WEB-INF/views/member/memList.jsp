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
<!-- 다른 JSP 파일의 내용을 삽입 3가지방법 -->

<!-- 1. %@ include 는 외부 JSP 파일의 내용을 현재 위치에 복사해서 넣은 후 하나의 JSP 파일로 실행 -->
<%-- <%@ include file="/WEB-INF/views/comm/menu.jsp" %> --%>

<!-- 2. jsp:include 는 별도의 독립된 JSP파일로 요청을 보내서 출력 결과를 현재 위치에 포함 -->
<%-- <jsp:include page="/WEB-INF/views/comm/menu.jsp"></jsp:include> --%>

<!-- 3. c:import 는 다른 웹 사이트(서비스)의 자원도 포함 가능 -->
<%-- <c:import url="/WEB-INF/views/comm/menu.jsp"></c:import> --%>
<h1>회원목록</h1>

	<form id="sform" action="${pageContext.request.contextPath}/member/list.do">
		<select name="searchType">
			<option value="Id">아이디</option>
			<option value="name">이름</option>
			<option value="total">아이디+이름</option>
		</select>
		<input type="text" name="searchWord" value="${searchInfo.searchWord}" />
		<input type="hidden" name="page" value="1" />
		<input type="submit" value="검색" />
	</form>
	<script>
		//searchType 셀렉트의 기본값을 설정하기 위해서
		if('${searchInfo.searchType}') //searchType 값이 존재하는 경우에만 (자바스크리트에서 빈문자열은 false 취급)
		document.querySelector('[name="searchType"]').value = '${searchInfo.searchType}';
	</script>
	
<c:forEach items="${memberList}" var="vo">

	<c:if test="${loginUser.memId == vo.memId}">
	<a href="${pageContext.request.contextPath}/member/edit.do?memId=${vo.memId}">${vo.memId}</a>
	</c:if>
	
	<c:if test="${loginUser.memId != vo.memId}">
	${vo.memId}
	</c:if>
	
	 : ${vo.memName} <br />
</c:forEach>

	${searchInfo.pageHtml}
	
    <script> 
		//페이지 링크 클릭시 실행될 goPage()함수를 재정의 할 필요가 있을 경우, 다시 선언	
 		function goPage(no){
 			//폼 내부에 페이지번호 파라미터를 설정하고
 			document.querySelector('[name="page"]').value = no;
 			//폼을 전송
 			document.querySelector('#sform').submit();
 		}
 	</script>
<hr />
<a href="${pageContext.request.contextPath}/member/add.do">회원가입</a>
</body>
</html>