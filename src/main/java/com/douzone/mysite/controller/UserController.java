package com.douzone.mysite.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.UserVo;
import com.douzone.security.Auth;
import com.douzone.security.AuthUser;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(
			@ModelAttribute @Valid UserVo userVo,
			BindingResult result) {
		return "/user/join";
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(
			@ModelAttribute @Valid UserVo userVo,
			BindingResult result,
			Model model) {
			System.out.println(userVo);
		// 유효성 검사
		if(result.hasErrors()) {
//			List<ObjectError> list = result.getAllErrors();
//			for(ObjectError error:list) {
//				System.out.println(error);
//			}
			model.addAllAttributes(result.getModel());
			return "user/join";
		}
		
		userService.join(userVo);
		return "redirect:/user/joinsuccess";
	}

	@RequestMapping("/joinsuccess")
	public String joinSucess() {
		return "/user/joinsuccess";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "/user/login";
	}

	@Auth
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify(@AuthUser UserVo authUser, Model model) {
		
		UserVo userVo = userService.modifyform(authUser);
		System.out.println("modify UserVo : " + userVo);
		
		model.addAttribute("vo", userVo);
		return "/user/modify";
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(
			@AuthUser UserVo authUser,
			@ModelAttribute UserVo userVo) {
		userVo.setNo(authUser.getNo());
		userService.modify(userVo);
		
		authUser.setName(userVo.getName());
		return "redirect:/main";
	}
}
