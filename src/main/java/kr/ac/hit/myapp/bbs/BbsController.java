package kr.ac.hit.myapp.bbs;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
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
	
	//요청을 받으면 실행되는 컨트롤러의 메서드에서 
	//HttpServletResponse를 인자로 받고 반환타입을 void로 설정하면,
	//스프링은 해당 메서드에서 직접 응답을 처리할 것으로 판단하여,
	//뷰이름을 반환받을 것이라고 생각하지 않는다.
	@RequestMapping("/bbs/down.do")
	public void download(int attNo, HttpServletResponse resp, HttpServletRequest req) throws IOException {
		//데이터베이스에서 요청받은 번호의 첨부파일 정보를 가져오기
		AttachVo vo = bbsService.selectAttach(attNo);
		//해당 첨부파일을 파일시스템(하드디스크)에서 읽어서 응답으로 전송
		File f = bbsService.getAttachFile(vo);
		
		//한글 파일명을 위한 인코딩 설정
		String userAgent = req.getHeader("User-Agent"); //요청을 보낸 브라우저 종류를 판단하기 위한 "User-Agent" 헤더값
		String fileName = new String(vo.getAttOrgName().getBytes("UTF-8"), "ISO-8859-1");
		if(userAgent.contains("MSIE") || userAgent.contains("Trident")) { //익스플로어인 경우
			fileName = URLEncoder.encode(vo.getAttOrgName(), "UTF-8").replaceAll("\\+", "%20");			
		}
		
		resp.setContentType("application/octet-stream"); //응답데이터의 종류
		resp.setHeader("Content-Disposition", "attachment; filename=\""+ fileName + "\""); //다운로드 파일명
		resp.setContentLength((int)f.length()); //응답데이터의 크기
		
		// 파일 f의 내용을 읽어서, 응답객체(resp)의 출력스트림에 쓰기
		// == 파일 내용을 응답으로 전송
		FileCopyUtils.copy(new FileInputStream(f), resp.getOutputStream());
	}
}
