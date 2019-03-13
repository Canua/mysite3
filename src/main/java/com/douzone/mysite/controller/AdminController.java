package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.service.FileuploadService;
import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.vo.SiteVo;
import com.douzone.security.Auth;
import com.douzone.security.Auth.Role;

//@Auth(Role.ADMIN)
@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private FileuploadService fileUploadService;

	
	@Autowired
	private SiteService siteService;

	@Auth(Role.ADMIN)
	@RequestMapping(value = { "", "/main" }, method = RequestMethod.GET)
	public String main(Model model) {
		SiteVo siteVo = siteService.getMain();
		model.addAttribute("siteVo", siteVo);
		return "admin/main";
	}

	@Auth(Role.ADMIN)
	@RequestMapping(value = { "/update" }, method = RequestMethod.POST)
	public String update(
			@ModelAttribute SiteVo siteVo,
			@RequestParam(value="upload-profile") MultipartFile multipartFile
			) {
		String profile = fileUploadService.restore(multipartFile);
		siteVo.setUrlprofile(profile);
		siteService.updateMain(siteVo);
		return "redirect:/admin";
	}
	
	// Multipart
//	@RequestMapping("/main/update")
//	public String updateSite(
//			) {
//		
//		
//		
//		return "redirect:/admin/main";
//	}
	
	
//	@Auth(Role.ADMIN)
//	@RequestMapping({ "/board" })
//	public String board() {
//		return "admin/board";
//	}
}
