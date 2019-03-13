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
<link href="${pageContext.servletContext.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<div id="header">
			<c:import url="/WEB-INF/views/includes/header.jsp"/>
		</div>
		
		<div id="content">
			<div id="board">
				<form class="board-form" method="post" action="${pageContext.servletContext.contextPath }/board/commentmodify">
					<input type="hidden" name="no" value="${param.no }" />
					<input type='hidden' name="commentNo" value="${param.commentNo }">
					<input type="hidden" name="p" value="${param.p }" />
					<input type="hidden" name="kwd" value="${param.kwd }" />
					
					<table class="tbl-ex">
						<tr>
							<th colspan="2">댓글수정</th>
						</tr>

						<tr>
							<td class="label">닉네임</td>
							<td>${vo.name }</td>
						</tr>
						<tr>
							<td class="label">비번</td>
							<td><input type="password" name="password" value=""></td>
						</tr>
						<tr>
							<td class="label">내용</td>
							<td>
								<textarea id="content" name="contents">
									${vo.contents }
								</textarea>
							</td>
						</tr>
					</table>
					<div class="bottom">
						<a href="${pageContext.servletContext.contextPath }/board/view?pageNo=${param.pageNo}&no=${param.boardNo}&kwd=${param.kwd}">취소</a>
						<input type="submit" value="수정">
					</div>
				</form>				
			</div>
		</div>
		<div id="navigation">
			<c:import url="/WEB-INF/views/includes/navigation.jsp">
				<c:param name="menu" value="board"/>
			</c:import>
		</div>
		
		<div id="footer">
			<c:import url="/WEB-INF/views/includes/footer.jsp"/>	
		</div>
	</div>
</body>
</html>