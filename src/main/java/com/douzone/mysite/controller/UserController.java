package com.douzone.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {
		return "/user/join";
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute UserVo userVo) {
		userService.join(userVo);
		return "redirect:/user/joinsuccess";
	}

	@RequestMapping("/joinsuccess")
	public String joinSucess() {
		return "/user/joinsuccess";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpSession session) {
		UserVo authUser = (UserVo) session.getAttribute("authuser");
		if(authUser != null) {
			return "redirect:/main";
		}
		return "/user/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpSession session, Model model, @ModelAttribute UserVo userVo) {
		System.out.println("sdaf : " + userVo);
		UserVo authUser = (UserVo) session.getAttribute("authuser");
		authUser = userService.login(userVo);
		System.out.println(authUser);
		if (authUser == null) {
			model.addAttribute("result", "fail");
			System.out.println("login x");
			return "/user/login";
		}
		session.setAttribute("authuser", authUser);
		System.out.println("login");
		return "redirect:/main";
	}
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		if (session != null && session.getAttribute("authuser") != null) {
			// logout 처리
			session.removeAttribute("authuser");
			session.invalidate();
		}
		return "redirect:/main";
	}
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify(HttpSession session, Model model) {
		UserVo authUser = (UserVo) session.getAttribute("authuser");
		if(authUser == null) {
			return "redirect:/main";
		}
		UserVo userVo = userService.modifyform(authUser);
		model.addAttribute("vo", userVo);

		return "/user/modify";
	}
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(HttpSession session, Model model, @ModelAttribute UserVo userVo) {
		UserVo authUser = (UserVo) session.getAttribute("authuser");
		
		userVo.setNo(authUser.getNo());
		userService.modify(userVo);
		
		return "redirect:/main";
	}
	

//	UserVo authUser = (UserVo)session.getAttribute("authuser");
}
