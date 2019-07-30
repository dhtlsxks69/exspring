package kr.ac.hit.myapp.member;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MemberController {
	//	@Autowired @Inject @Resource 중 하나를 사용하여 자동 주입(스프링은 new를 알아서 해주기 때문에 어노태이션을 넣으면 됨)
	@Resource
	private MemberService memberService;
	//요청방식 : GET방식으로
	@RequestMapping(value="/member/add.do", method=RequestMethod.GET)
	public String addForm() {
		return "member/memAdd";
	}
	//요청방식 : POST방식으로	
	@RequestMapping(value="/member/add.do", method=RequestMethod.POST)
	public String add(MemberVo vo) {
		//파라미터로 넘어온 값들을 받아서 데이터베이스에 추가(insert)
		memberService.insert(vo);
		//JSP 파일로 이동하는 대신 redirect: 뒤에 지정한 주소로 이동하라는 응답을 전송
		return "redirect:/member/list.do";
	}
	
	@RequestMapping("/member/list.do")
	public String list(Map modelMap) {
		//데이터베이스에서 회원목록을 조회하고,
		List<MemberVo> list = memberService.selectList();
		//조회한 회원목록인 list를 JSP에서 ${memberList}로 사용할 수 있도록 모델에 저장
		modelMap.put("memberList", list);
		return "member/memList";
	}
	
	@RequestMapping(value="/member/edit.do", method=RequestMethod.GET)
	public String editForm(String memId, Map modelMap, HttpSession session) {
		// 로그인한 사용자와 상세정보를 조회하는 사용자(memId)와 일치하는지 확인
		MemberVo loginUser = (MemberVo) session.getAttribute("loginUser");
		if(loginUser.getMemId().equals(memId) == false) { // 로그인한 사용자와 조회대상 사용자가 다르면
//			return "redirect:/member/list.do";
			throw new RuntimeException("권한이 없습니다."); // 예외(에러) 발생
		}
		
		MemberVo vo = memberService.select(memId);
		modelMap.put("memberVo", vo);
		return "member/memEdit";
	}
	
	@RequestMapping(value="/member/edit.do", method=RequestMethod.POST)
	public String edit(MemberVo vo, Map modelMap, HttpSession session) {
		// 로그인한 사용자와 상세정보를 조회하는 사용자(memId)와 일치하는지 확인
		MemberVo loginUser = (MemberVo) session.getAttribute("loginUser");
		if(loginUser.getMemId().equals(vo) == false) { // 로그인한 사용자와 조회대상 사용자가 다르면
//			return "redirect:/member/list.do";
			throw new RuntimeException("권한이 없습니다."); // 예외(에러) 발생
		}
		int num = memberService.update(vo);
		return "redirect:/member/list.do";
	}
	
	@RequestMapping(value="/member/del.do", method=RequestMethod.GET)
	public String del(MemberVo vo, HttpSession session) {
		// 로그인한 사용자와 상세정보를 조회하는 사용자(memId)와 일치하는지 확인
		MemberVo loginUser = (MemberVo) session.getAttribute("loginUser");
		if(loginUser.getMemId().equals(vo) == false) { // 로그인한 사용자와 삭제할 사용자가 다르면
//			return "redirect:/member/list.do";
			throw new RuntimeException("권한이 없습니다."); // 예외(에러) 발생
		}
		
		int num = memberService.delete(vo);
		return "redirect:/member/list.do";
	}
	
	@RequestMapping(value="/member/login.do", method=RequestMethod.GET)
	public String loginForm() {
		return "member/login";
	}
	
	@RequestMapping(value="/member/login.do", method=RequestMethod.POST)
	public String login(MemberVo vo, HttpSession session) { // 스프링이 실해시 세션객체를 전달
		//사용자가 입력한 아이디/비밀번호와 일치하는 회원 정보 조회
		MemberVo mvo = memberService.selectLoginUser(vo);
		if(mvo == null) { //일차하는 회원이 없는 경우 == 로그인 실패
			return "member/login"; //다시 로그인 화면 출력
		}
		// mvo != null : 로그인 성공 --> 로그인한 사용자 정보(mvo)를 세션에 "loginUser"라는 이름으로 저장
		session.setAttribute("loginUser", mvo);
		return "redirect:/bbs/list.do"; //게시판 글목록으로 이동
	}
	
	@RequestMapping("/member/logout.do")
	public String logout(HttpSession session) {
		//로그아웃 == 세션에 저장된 로그인 정보를 삭제 3가지 방법
//		session.setAttribute("loginUser", null); // 세션에 "loginUser"라는 이름으로 null을 저장
//		session.removeAttribute("loginUser"); // 세션에서 "loginUser" 속성 자체를 제거 
		session.invalidate(); // 세션 객체 자체를 삭제(하고 새로 생성)
		return "redirect:/member/login.do";
	}
}
