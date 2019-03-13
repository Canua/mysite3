<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
	
		<div id="header">
			<c:import url="/WEB-INF/views/includes/header.jsp"/>
		</div>
		
		<div id="content">
			<div id="guestbook" class="delete-form">
				<form method="post" action="${pageContext.servletContext.contextPath }/board/commentdelete">
					<input type="hidden" name="no" value="${param.no }" />
					<input type='hidden' name="commentNo" value="${param.commentNo }">
					<input type="hidden" name="p" value="${param.p }" />
					<input type="hidden" name="kwd" value="${param.kwd }" />
						
					<label>비밀번호</label>
					<input type="password" name="password">
					<input type="submit" value="확인">
				</form>
			</div>
		</div>

		<div id="navigation">
			<c:import url="/WEB-INF/views/includes/navigation.jsp">
				<c:param name="menu" value="guestbook"/>
			</c:import>
		</div>
		
		<div id="footer">
			<c:import url="/WEB-INF/views/includes/footer.jsp"/>	
		</div>
		
	</div>
</body>
</html>