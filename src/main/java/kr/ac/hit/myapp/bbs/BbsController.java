package kr.ac.hit.myapp.bbs;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.ac.hit.myapp.member.MemberVo;

@Controller
public class BbsController {
	@Resource
	private BbsService bbsService;
	
	@RequestMapping(value="/bbs/add.do", method=RequestMethod.GET)
	public String addForm() {
		return "bbs/bbsAdd";
	}
	
	@RequestMapping(value="/bbs/add.do", method=RequestMethod.POST)
	public String add(BbsVo vo, HttpSession session) {
		MemberVo loginUser = (MemberVo) session.getAttribute("loginUser"); // 로그인한 사용자
		vo.setBbsWriter(loginUser.getMemId()); // 로그인한 사용자 아이디로 글쓴이를 강제설정
		int num = bbsService.insert(vo);
		return "redirect:/bbs/list.do";
	}
	
	@RequestMapping(value="/bbs/list.do", method=RequestMethod.GET)
	public String list(Map model) {
		List<BbsVo> list = bbsService.selectList();
		model.put("bbsList", list);
		return "bbs/bbsList";
	}
	
	@RequestMapping(value="/bbs/edit.do", method=RequestMethod.GET)
	public String editForm(int bbsNo, Map model) {
		BbsVo vo = bbsService.select(bbsNo);
		model.put("bbsVo", vo);
		return "bbs/bbsEdit";
	}
	
	@RequestMapping(value="/bbs/edit.do", method=RequestMethod.POST)
	public String edit(BbsVo vo, HttpSession session) {
		MemberVo loginUser = (MemberVo) session.getAttribute("loginUser"); // 로그인한 사용자
		// 1. 수정하려고 하는 게시글 정보를 데이터베이스에서 select 해와서,
		// 로그인 사용자 아이디와 게시글 작성자 아이디와 비교해서 동일하면, update 실행
		
		// 2. 로그인한 사용자의 아이디 정보를 update 실행시 함께 전달하여
		// 로그인 사용자 아이디와 게시글 작성자 아이디가 같은 경우에만 update되도록 SQL문 변경
		vo.setBbsWriter(loginUser.getMemId());
		
		int num = bbsService.update(vo);
		return "redirect:/bbs/list.do";
	}
	
	@RequestMapping(value="/bbs/del.do", method=RequestMethod.GET)
	public String del(BbsVo vo, HttpSession session) {
		MemberVo loginUser = (MemberVo) session.getAttribute("loginUser"); // 로그인한 사용자
		vo.setBbsWriter(loginUser.getMemId());
		
		int num = bbsService.delete(vo);
		return "redirect:/bbs/list.do";
	}
}
