<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/user.css"
	rel="stylesheet" type="text/css">
<script type ="text/javascript" src = "${pageContext.servletContext.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
// form
$(function(){
	$("#join-form").submit(function(){	
		//1. 이름 체크
		if($("#name").val() == ""){
			alert("이름은 필수 입력 항목입니다.");
			$("#name").focus();
			return false;
		}
		
		//2-1. 이메일이 비어 있는지 확인
		if($("#email").val() == ""){
			alert("이메일은 필수 입력 항목입니다.");
			$("#email").focus();
			return false;
		}
		
		//2-2. 이메일 중복체크 유무
		if($("#img-checkemail").is(":visible") == false){
			alert("이메일 중복 체크를 해야합니다.");
			return false;
		}
		
		//3. 비밀번호 확인
		if($("input[type='password']").val() == ""){
			alert("비밀번호는 필수 입력 항목입니다.");
			$("input[type='password']").focus();
			return false;
		}
		
		//4. 약관동의
		if($("#agree-prov").is(":checked") == false){
			alert("필수 약관 동의를 해야합니다.");
			return false;
		}
		return true;
	});
	
	$("#email").change(function () {
		$("#btn-checkdmail").show();
		$("#img-checkemail").hide();
	});
	
	$("#btn-checkdmail").click(function () {
		var email = $("#email").val();
		if(email == ""){
			return
		}
		
		$.ajax( {
			url : "${pageContext.request.contextPath }/user/api/checkemail?email=" + email,
			type: "get",
			dataType: "json",
			data: "",
			success: function (response) {
				console.log(response);
				// 사용 불가능한 이메일
				if(response.data == true){ 
					alert("이미 존재하는 이메일입니다. 다른 이메일을 사용해 주세요.");
					$("#email").val("").focus();
					
					return;
				}
				// 사용가능한 이메일
				$("#btn-checkdmail").hide();
				$("#img-checkemail").show();
				
			},
			error: function (xhr, status, e) {
				console.log(status + ":" + e);
			}
		});
	});
});
</script>
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp"></jsp:include>
		<div id="content">
			<div id="user">

				<form:form 
					modelAttribute="userVo"
					id="join-form" 
					name="joinForm" 
					method="post" 
					action="${pageContext.servletContext.contextPath }/user/join">
					<input type="hidden" name="a" value="join" /> 
					<label class="block-label" for="name">이름</label>

					<input id="name" name="name" type="text" value="${userVo.name }"> 
						<spring:hasBindErrors name="userVo">
						   <c:if test="${errors.hasFieldErrors('name') }">
						   		<p style="padding:0; text-align: left; color:red">
						        	<strong>
						        		<spring:message 
						        			code="${errors.getFieldError( 'name' ).codes[0] }"
						        			text="${errors.getFieldError( 'name' ).defaultMessage }"
						        		/>
						        	</strong>
						   		</p>
						   </c:if>
						</spring:hasBindErrors>

					<label class="block-label" for="email">이메일</label> 
					<form:input path="email" />
					<p style="margin:0; padding:0; font-weight:bold; color:red; text-align:left">
					<form:errors path="email" />
					</p>

					<img id= "img-checkemail" style = "width : 20px; display : none;" src="${pageContext.servletContext.contextPath }/assets/images/check.png" />
					<input id ="btn-checkdmail" type="button" value="이메일 확인"> 
					
					
					<label class="block-label">패스워드</label> 
					<form:password path="password" />

					<fieldset>
						<legend>성별</legend>
						<label>여</label> 
						<input type="radio" name="gender" value="female" checked="checked"> 
						<label>남</label> <input type="radio" name="gender" value="male">
					</fieldset>

					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>

					<input type="submit" value="가입하기">

				</form:form>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/views/includes/footer.jsp"></jsp:include>
	</div>
</body>
</html>