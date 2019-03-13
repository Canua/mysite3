<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/user.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp"></jsp:include>
		<div id="content">
			<div id="user">
				<form id="login-form" method="post" action="${pageContext.servletContext.contextPath }/user/auth">
					<label class="block-label" for="email">이메일</label> 
					<input id="email" name="email" type="text" value=""> 
					<label class="block-label">패스워드</label> 
					<input name="password" type="password" value="">
					<c:set var = "fail" value= "fail"></c:set>
					<c:if test='${param.result == fail}'>
							<p>
								로그인이 실패 했습니다.
							</p>
					</c:if>		
					<input type="submit" value="로그인">
				</form>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/views/includes/footer.jsp"></jsp:include>
	</div>
</body>
</html>