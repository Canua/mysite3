package com.douzone.mysite.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.dto.JSONResult;
import com.douzone.mysite.service.GuestbookAjaxService;
import com.douzone.mysite.vo.GuestbookVo;

@Controller("guestbookApiController")
@RequestMapping("/guestbook/ajax")
public class GuestbookAjaxController {

	@Autowired
	GuestbookAjaxService guestbookAjaxService;
	
	@RequestMapping("")
	public String index() {
		return "/guestbook/index-ajax";
	}

	@ResponseBody
	@RequestMapping("/list")
	public JSONResult list(@RequestParam(value = "p", required = true, defaultValue = "1") String sPage) {
		if ("".equals(sPage)) {
			sPage = "1";
		}

		// isNumeric 정규표현식을 이용한 parameter 검사
		if (sPage.matches("\\d*") == false) {
			sPage = "1";
		}

		int page = Integer.parseInt(sPage);

		List<GuestbookVo> list = guestbookAjaxService.getList(page);

		return JSONResult.success(list);
	}

	@ResponseBody
	@RequestMapping("/insert")
	public JSONResult insert(
			@RequestParam(value = "name") String name,
			@RequestParam(value = "password") String password, 
			@RequestParam(value = "message") String message) {
		GuestbookVo vo = new GuestbookVo();
		vo.setName(name);
		vo.setPassword(password);
		vo.setMessage(message);
		System.out.println("받은 vo" + vo);
		long no = guestbookAjaxService.insert(vo);
		
		GuestbookVo newVo = guestbookAjaxService.getList(no); 
		
		return JSONResult.success(newVo);
		
	}
	@ResponseBody
	@RequestMapping("/delete")
	public JSONResult delete(
			@RequestParam(value = "clickNo")  long clickNo,
			@RequestParam(value = "password") String password) {
		GuestbookVo vo = new GuestbookVo();
		vo.setNo(clickNo);
		vo.setPassword(password);
		int result = guestbookAjaxService.delete(vo);
		
		return JSONResult.success(result);
	}

}
