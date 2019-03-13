package com.douzone.mysite.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.CommentVo;
import com.douzone.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;

	@RequestMapping( "" )
	public String index( 
		@RequestParam( value="p", required=true, defaultValue="1") Integer page,
		@RequestParam( value="kwd", required=true, defaultValue="") String keyword,
		Model model ) {
		Map<String, Object> map = boardService.getMessageList( page, keyword );
		model.addAttribute( "map", map );
		return "board/list";
	}
	
	
	@RequestMapping( value="/write", method=RequestMethod.GET )	
	public String write(HttpSession session) {
		/* 접근제어 */
		if(null == session.getAttribute("authUser")) {
			return "redirect:/";
		}
		
		return "board/write";
	}

	@RequestMapping( value="/write", method=RequestMethod.POST )	
	public String write(
		HttpSession session,
		@ModelAttribute BoardVo boardVo,
		@RequestParam( value="p", required=true, defaultValue="1") Integer page,
		@RequestParam( value="kwd", required=true, defaultValue="") String keyword ) {

		UserVo authUser = (UserVo)session.getAttribute("authUser");

		/* 접근제어 */
		if(null == authUser) {
			return "redirect:/";
		}
		
		boardVo.setUser_no( authUser.getNo() );
		
		if( boardVo.getG_no() != null ) {
			boardService.increaseGroupOrderNo( boardVo );
		}
		boardService.addMessage( boardVo );
		
		return	( boardVo.getG_no() != null ) ?
				"redirect:/board?p=" + page + "&kwd=" + keyword :
				"redirect:/board";
	}

	@RequestMapping( "/delete/{no}" )
	public String delete(
		HttpSession session,
		@PathVariable( "no" ) Long boardNo,
		@RequestParam( value="p", required=true, defaultValue="1") Integer page,
		@RequestParam( value="kwd", required=true, defaultValue="") String keyword ) {

		UserVo authUser = (UserVo)session.getAttribute("authUser");

		/* 접근제어 */
		if(null == authUser) {
			return "redirect:/";
		}
		
		boardService.deleteMessage( boardNo, authUser.getNo() );
		return "redirect:/board?p=" + page + "&kwd=" + keyword;
	}

	@RequestMapping( "/view/{no}" )
	public String view( 
			@PathVariable( "no" ) Long no, Model model,
			HttpSession session) {
//		UserVo authUser = (UserVo)session.getAttribute("authUser");

		System.out.println("보드 번호 : " + no);
//		System.out.println("유저 번호 : " + authUser.getNo());
		
		
		BoardVo boardVo = boardService.getMessage( no );
		model.addAttribute( "boardVo", boardVo );

		List<CommentVo> list = new ArrayList<CommentVo>();
		list = boardService.getComments(no);
		
		model.addAttribute("commentlist", list);
		
		
		
		return "board/view";
	}

	@RequestMapping( value="/modify/{no}" )	
	public String modify(
		HttpSession session,
		@PathVariable( "no" ) Long no,
		Model model) {
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		/* 접근제어 */
		if(null == authUser) {
			return "redirect:/";
		}
		BoardVo boardVo = boardService.getMessage(no, authUser.getNo() );
		model.addAttribute( "boardVo", boardVo );
		return "board/modify";
	}

	@RequestMapping( value="/modify", method=RequestMethod.POST )	
	public String modify(
		HttpSession session,
		@ModelAttribute BoardVo boardVo,
		@RequestParam( value="p", required=true, defaultValue="1") Integer page,
		@RequestParam( value="kwd", required=true, defaultValue="") String keyword
		) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");

		/* 접근제어 */
		if(null == authUser) {
			return "redirect:/";
		}
		
		boardVo.setUser_no( authUser.getNo() );
		boardService.modifyMessage( boardVo );
		return "redirect:/board/view/" + boardVo.getNo() + 
				"?p=" + page + 
				"&kwd=" + keyword;
	}
	

	@RequestMapping(value = "/reply/{no}")
	public String reply(HttpSession session, @PathVariable("no") Long no, Model model) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		/* 접근제어 */
		if (authUser == null) {
			return "redirect:/main";
		}
	
		BoardVo boardVo = boardService.getMessage(no);
		boardVo.setO_no(boardVo.getO_no() + 1);
		boardVo.setDepth(boardVo.getDepth() + 1);

		model.addAttribute("boardVo", boardVo);

		return "board/reply";
	}
	@RequestMapping(value = "/comment/{no}", method=RequestMethod.POST )
	public String addComment(
			Model model,
			@ModelAttribute CommentVo commentVo,
			@PathVariable( "no" ) Long no,
			@RequestParam( value="p", required=true, defaultValue="1") Integer page,
			@RequestParam( value="kwd", required=true, defaultValue="") String keyword
			) {
		commentVo.setBoardNo(no);
		boardService.addComment(commentVo);
		return "redirect:/board/view/" + no + 
				"?p=" + page + 
				"&kwd=" + keyword;
	}

	@RequestMapping(value = "/commentdelete", method=RequestMethod.GET)
	public String deleteComment() {
		return "board/commentdelete";
	}
	
	@RequestMapping(value = "/commentdelete", method=RequestMethod.POST)
	public String deleteComment(
			@ModelAttribute CommentVo commentVo,
			@RequestParam( value="commentNo", required=true, defaultValue="1") Integer commentNo,
			@RequestParam( value="no", required=true, defaultValue="1") Integer no,
			@RequestParam( value="p", required=true, defaultValue="1") Integer page,
			@RequestParam( value="kwd", required=true, defaultValue="") String keyword
			) {
		commentVo.setNo(commentNo);
		boardService.deleteComment(commentVo); 
		
		return "redirect:/board/view/" + no + 
				"?p=" + page + 
				"&kwd=" + keyword;
	}
	
	@RequestMapping(value = "/commentmodify", method=RequestMethod.GET)
	public String modifyComment(
			Model model
			) {
		return "board/commentmodify";
	}
	
	
	
}