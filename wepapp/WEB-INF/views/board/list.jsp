<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link
	href="${pageContext.servletContext.contextPath }/assets/css/board.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<c:set var="pageCount" value="${pagecreate }" />
		<c:set var="pageNum_set" value="${pageNum_set }" />
		<div id="content">
			<div id="board">
				<form id="search_form"
					action="${pageContext.servletContext.contextPath }/board"
					method="post">
					<input type="hidden" name="a" value="search" /> <input
						type="hidden" name="p" value="${pageCount }" /> <input
						type="hidden" name="pn" value="${pageNum_set }" /> <select
						name='select' style="height: 27px; margin-top: 15px">
						<option value='all'>글쓴이 + 제목</option>
						<option value='user'>글쓴이</option>
						<option value='title'>제목</option>
					</select> <input type="text" id="kwd" name="kwd" value=""> <input
						type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					<c:set var="count" value="${fn:length(list_set) }" />
					<c:forEach items="${list }" var="vo" varStatus="status">
						<tr>
							<c:set var="pageNum_set" value="${pageNum_set }" />
							<c:choose>
								<c:when test="${pageNum_set == 1 }">
									<td>${count - status.index}</td>
								</c:when>
								<c:otherwise>
									<td>${count - ( status.index + ((pageNum_set-1) * 5))}</td>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${vo.depth >= 1 }">
									<td style="padding-left:${30*vo.depth }px"><img
										src="/mysite2/assets/images/reply.png" /><a
										href="${pageContext.servletContext.contextPath }/board?a=view&no=${vo.no}">${vo.title }</a></td>
								</c:when>
								<c:otherwise>
									<td><a
										href="${pageContext.servletContext.contextPath }/board?a=view&no=${vo.no}">${vo.title }</a></td>
								</c:otherwise>
							</c:choose>
							<td>${vo.name }</td>
							<td>${vo.hit }</td>
							<td>${vo.writeDate }</td>
							<c:choose>
								<c:when test="${vo.user_no == bo.nos }">
									<td><a
										href="${pageContext.servletContext.contextPath }/board?a=deleteboard&no=${vo.no}"
										class="del">삭제</a></td>
									<input type="hidden" name="a" value="deleteboard">
								</c:when>
							</c:choose>
						</tr>
					</c:forEach>
				</table>
				<c:set var="cancel" value="${cancel }" />
				<c:if test="${cancel != 9999}">
					<div class="pager">
						<ul>
							<li><a
								href="${pageContext.servletContext.contextPath }/board?a=&page=${pageNum_set - 1 }">◀</a></li>
							<c:forEach var="i" begin="${1 }" end="${pageCount}" step="${1 }">
								<c:if test="${i != pageNum_set }">
									<li><a
										href="${pageContext.servletContext.contextPath }/board?a=&page=${i }">${i }</a></li>
								</c:if>
								<c:if test="${i == pageNum_set }">
									<li class="selected"><a
										href="${pageContext.servletContext.contextPath }/board?a=&page=${i }">${i }</a></li>
								</c:if>
							</c:forEach>

							<li><a
								href="${pageContext.servletContext.contextPath }/board?a=&page=${pageNum_set + 1 }">▶</a></li>
						</ul>
					</div>
				</c:if>
				<c:if test="${cancel == 9999 }">
					<div class="bottom" style = "text-align: center">
						<div style = "display: inline-block">
						<button style="background-color: #E8ECF6; width: 65px; height: 35px; border: 1px solid black"><a href="${pageContext.servletContext.contextPath }/board?a=&page=1" style="font-size : 14px; text-decoration: none; color : black;">글목록</a></button>
						</div>
					</div>
				</c:if>
				<div class="bottom">
					<c:if test='${bo.nos > 0 }'>
						<a href="${pageContext.servletContext.contextPath }/board?a=write"
							id="new-book">글쓰기</a>
					</c:if>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>