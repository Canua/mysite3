package com.douzone.mysite.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
		int pageCreate = boardService.size()/ 5;
		list_set = boardService.list();
		if (pageNum_set < 1) {
			pageNum_set = 1;
		}
		if(list_set.size()%5 != 0) {
			pageCreate += 1;
		}
		if (pageNum_set >= pageCreate ) {
			pageNum_set = pageCreate;
		}
		
		if (authUser != null) {
			UserVo bo = userService.get(authUser.getNo());
			model.addAttribute("bo", bo);
		}
		model.addAttribute("list_set", list_set);
		
		int pageNum = (pageNum_set - 1) * 5;
		list = boardService.getPageList(pageNum);
		model.addAttribute("pagecreate", pageCreate);
		model.addAttribute("pageNum_set", pageNum_set);
		model.addAttribute("list", list);
		return "/board/list";
	}
		@RequestMapping(value="/write", method=RequestMethod.GET)
		public String write() {
			return "/board/write";
		}
		@RequestMapping(value="/write", method=RequestMethod.POST)
		public String write(
				HttpSession session, 
				@ModelAttribute BoardVo boardVo
				) {
			UserVo authUser = (UserVo) session.getAttribute("authuser");
			long user_no = authUser.getNo();
			if(authUser == null) {
				return "redirect:/main";
			}
			boardVo.setUser_no(user_no);
			boardService.write(boardVo);
			return "redirect:/board/list?page=1";
		}
		
		@RequestMapping(value="/delete", method=RequestMethod.GET)
		public String delete(
				HttpSession session, 
				@RequestParam (value="no") Long no) {
			UserVo authUser = (UserVo) session.getAttribute("authuser");
			long user_no = authUser.getNo();
			long board_user = boardService.getUser(no);
			if(user_no == board_user) {
			boardService.delete(no);
			} else {
				return "redirect:/main";
			}
			return "redirect:/board/list?page=1";
		}
		@RequestMapping(value="view", method=RequestMethod.GET)
		public String view(
				HttpSession session,
				@RequestParam (value="no") long no,
				Model model) {
			UserVo authUser = (UserVo) session.getAttribute("authuser");
			if(authUser != null) {
				UserVo bo = userService.get(authUser.getNo());
				model.addAttribute("bo", bo);
			}
			boardService.hitUpdate(no);
			BoardVo vo = boardService.getView(no);
			System.out.println("view vo : " + vo);
			model.addAttribute("vo", vo);
			return "/board/view";
		}
		
		@RequestMapping(value="modify", method=RequestMethod.GET)
		public String modify(
				HttpSession session,
				@RequestParam (value="no") long no,
				Model model
				) {
			
		
			UserVo authUser = (UserVo) session.getAttribute("authuser");
			if (authUser == null) {
				return "redirect:/main";
			}
			boolean check = boardService.check(no, authUser.getNo());
			if (check == true) {
				BoardVo vo = boardService.getView(no);
				model.addAttribute("vo", vo);
				return "/board/modify";
			} else {
				return "redirect:/board/list?page=1";
			}			
		}
		
		@RequestMapping(value="modify", method=RequestMethod.POST)
		public String modify(
				@ModelAttribute BoardVo boardVo,
				@RequestParam (value="no") long no
				) {
			
			boardService.update(boardVo);
			return "redirect:/board/view?no=" + no;
		}
		
		@RequestMapping(value="reply", method=RequestMethod.GET)
		public String reply(
				HttpSession session,
				@RequestParam (value="no") long no,
				Model model
				) {
			System.out.println("nononono2222 : " + no);
			UserVo authUser = (UserVo) session.getAttribute("authuser");
			if (authUser == null) {
				return "redirect:/main";
			}
			BoardVo vo = boardService.getInfo(no);
			model.addAttribute("vo", vo);
			System.out.println("vodddd : " + vo);
			return "/board/reply";
		}
		
		
		@RequestMapping(value="reply", method=RequestMethod.POST)
		public String reply(
				HttpSession session,
				@ModelAttribute BoardVo boardVo
				) {
//			System.out.println("nononono33 : " + no);
			UserVo authUser = (UserVo) session.getAttribute("authuser");
			if (authUser == null) {
				return "redirect:/main";
			}
			System.out.println("vovovovovo : " + boardVo);
					
			return "/board/reply";
		}
		
}
