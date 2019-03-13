package com.douzone.mysite.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.service.GuestbookService;
import com.douzone.mysite.vo.GuestbookVo;

@Controller
@RequestMapping("/guestbook")
public class GusetbookController {

	@Autowired
	private GuestbookService guestbookService;

	@RequestMapping(value = "/list")
	public String list(Model model) {
		List<GuestbookVo> list = new ArrayList<GuestbookVo>();
		list = guestbookService.list();
		model.addAttribute("list", list);

		return "/guestbook/list";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@ModelAttribute GuestbookVo guestbookVo) {
		guestbookService.insert(guestbookVo);
		// redirect
		return "redirect:/guestbook/list";
	}

	@RequestMapping(value = "/deleteform", method = RequestMethod.GET)
	public String form(@RequestParam(value = "no", required = false) Long no, Model model) {
		model.addAttribute("no", no);
		return "/guestbook/delete";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@ModelAttribute GuestbookVo guestbookVo) {
		guestbookService.delete(guestbookVo);
		return "redirect:/guestbook/list";
	}

//	@RequestMapping(value = "/ajax")
//	public String ajaxList() {
//		return "/guestbook/index-ajax";
//	}

}
