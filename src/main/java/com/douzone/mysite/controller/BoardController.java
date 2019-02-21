package com.douzone.mysite.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(
			HttpSession session, 
			Model model, 
			@RequestParam(value = "page") int pageNum_set
			) {
		List<BoardVo> list_set = new ArrayList<BoardVo>();
		List<BoardVo> list = new ArrayList<BoardVo>();
		UserVo authUser = (UserVo) session.getAttribute("authuser");
		System.out.println("page : " + pageNum_set);
		int pageCreate = boardService.size();
		System.out.println("보드 리스트 size : " +  pageCreate);
		list_set = boardService.list();
		if (pageNum_set < 1) {
			pageNum_set = 1;
		}
		if(pageCreate%5 != 0) {
			pageCreate += 1;
		}
		if (pageNum_set >= pageCreate ) {
			pageNum_set = pageCreate;
		}
		if (authUser != null) {
			UserVo bo = userService.get(authUser.getNo());
			model.addAttribute("bo", bo);
		}
		int pageNum = (pageNum_set - 1) * 5;
		model.addAttribute("pagecreate", pageCreate);
		model.addAttribute("pageNum_set", pageNum_set);
		list = boardService.getPageList(pageNum);
		return "/board/list";
	}

}
