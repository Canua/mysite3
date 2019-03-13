package com.douzone.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.douzone.mysite.vo.UserVo;
import com.douzone.security.Auth.Role;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		// 1. Handler 종류 확인
		if (handler instanceof HandlerMethod == false) {
			return true;
		}

		// 2. Casting
		HandlerMethod handlerMethod = (HandlerMethod) handler;

		// 3. Method에 @Auth 받아오기
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);

		// 4. Method에 @Auth가 안 붙어 있으면 class(type)의 @Auth 받아오기
		if(auth == null) {
			auth = handlerMethod.getMethod().getDeclaringClass().getAnnotation(Auth.class);
		}
		
		// 5. Method와 class에 @Auth가 안 붙어있으면
		if (auth == null) {
			return true;
		}

		// 6. @Auth 붙어 있기 때문에 로그인 여부(인증여부)를 확인해야 한다.
		HttpSession session = request.getSession();
		UserVo authUser = (session == null) ? null : (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
		
		// 7. Role 비교 작업
		// amin, user 인지 비교
		Role role = auth.value(); // role 가져오기
		System.out.println("Role : " + role);
		
		// 8. User Role 접근이면 인증만 되어 있으면 허용
		if(role == Role.USER) {
			return true;
		}
		System.out.println("authUser : " + authUser);
		System.out.println(authUser.getRole() + " 내가 가지고 있는 Role") ;
		
		// 9. 접급
		// ADMIN 권한이 없는 사용자를 메인으로
		if("ADMIN".equals(authUser.getRole()) == false) {
			System.out.println("거부");
			response.sendRedirect(request.getContextPath() + "/");
			return true;
		}
		
		// 10. ADMIN 접근 허용
		return true;
	}

}
