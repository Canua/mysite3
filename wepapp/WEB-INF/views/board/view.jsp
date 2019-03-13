<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% pageContext.setAttribute( "newLine", "\n" ); %>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board" class="board-form">
				<table class="tbl-ex">
					<tr>
						<th colspan="2">글보기</th>
					</tr>
					<tr>
						<td class="label">제목</td>
						<td>${boardVo.title }</td>
					</tr>
					<tr>
						<td class="label">내용</td>
						<td>
							<div class="view-content">
							${fn:replace(boardVo.contents, newLine, "<br>") }					
							</div>
						</td>
					</tr>
				</table>
				<div class="bottom">
					<a href="${pageContext.request.contextPath }/board?p=${param.p }&kwd=${param.kwd }">글목록</a>
					<c:if test="${ not empty authUser }">
						<a href="${pageContext.request.contextPath }/board/reply/${boardVo.no }?p=${param.p }&kwd=${param.kwd }">답글 달기</a>
						<c:if test="${authUser.no == boardVo.user_no }">
							<a href="${pageContext.request.contextPath }/board/modify/${boardVo.no }?p=${param.p }&kwd=${param.kwd }">글수정</a>
						</c:if>
					</c:if>
				</div>
				
				
				<!-- 댓글보여주기 -->
				<c:set var="count" value="0"/>		
				<c:set var="commentcount" value="${fn:length(commentlist)}"/>
				<hr>
					<br/>
					<strong>댓글(${commentcount }개)</strong>			
				<c:forEach items="${commentlist}" var="vo" varStatus="status">
				<hr>
				  <ol>
					<li>
						<table>
							<tr>
								 <td>
									[${ count = count+1 }] |
								 </td>
							<!-- 	
							<c:choose>
								<c:when test="${ vo.userNo > 0 }">			 
									<td style="color:red"><strong>${vo.name }</strong></td>
								</c:when>
								<c:otherwise>
									<td>${vo.name }</td>
								</c:otherwise>
							</c:choose>	 
							 -->			
							 <td>${vo.name }</td>
								<td> | ${vo.writeDate }</td>
								<td><a href="${pageContext.servletContext.contextPath }/board/commentdelete?no=${boardVo.no }&commentNo=${vo.no }&p=${param.p }&kwd=${param.kwd }">삭제</a></td>
								<td><a href="${pageContext.servletContext.contextPath }/board/commentmodify?no=${boardVo.no }&commentNo=${vo.no }&p=${param.p }&kwd=${param.kwd }">수정</a></td>
							</tr>
							<tr>
								<td colspan=4>
								 ${fn:replace(vo.comment,  newline, "<br>") }
								</td>
							</tr>
						</table>
									
						<br>
					</li>
				  </ol>
				</c:forEach>
			<!--  -->
				
				
				
				<c:choose>
					<c:when test="${ empty authuser }">
						<form class="board-form" method="post" action="${pageContext.servletContext.contextPath }/board/comment/${boardVo.no }">
							<input type="hidden" name="no" value="${boardVo.no }" />
							<input type="hidden" name="p" value="${param.p }" />
							<input type="hidden" name="kwd" value="${param.kwd }" />
						
							
							<table class="tbl-ex">
								
								<tr>
									<td>닉네임 : <input type="text" name="name" value=""></td>
								</tr>
								<tr>
									<td>비밀번호 :<input type="password" name="password" value=""></td>
								</tr>
								<tr>
									<td>
										<textarea id="content" name="comment"></textarea>
									</td>
								</tr>
								
							</table>
							<input type="submit" value="댓글달기">
						</form>
					</c:when>
					
					<c:otherwise>
						<form class="board-form" method="post" action="${pageContext.servletContext.contextPath }/board/comment/${boardVo.no }">
							<input type="hidden" name="no" value="${boardVo.no }" />
							<input type="hidden" name="p" value="${param.p }" />
							<input type="hidden" name="kwd" value="${param.kwd }" />
						
							<table class="tbl-ex">
								
								<tr>
									<td>닉네임 : ${authuser.name }</td>
								</tr>			
								<tr>
									<td>
										<textarea id="content" name="comment"></textarea>
									</td>
								</tr>
								
							</table>
							<input type="submit" value="댓글달기">
						</form>
					</c:otherwise>
					
				</c:choose>
			<!-- 댓글폼 -->
				
				
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>