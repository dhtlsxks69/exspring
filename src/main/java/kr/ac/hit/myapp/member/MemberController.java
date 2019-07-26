package kr.ac.hit.myapp.member;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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
	public String editForm(String memId, Map modelMap) {
		MemberVo vo = memberService.select(memId);
		modelMap.put("memberVo", vo);
		return "member/memEdit";
	}
	
	@RequestMapping(value="/member/edit.do", method=RequestMethod.POST)
	public String edit(MemberVo vo, Map modelMap) {
		int num = memberService.update(vo);
		return "redirect:/member/list.do";
	}
	
	@RequestMapping(value="/member/del.do", method=RequestMethod.GET)
	public String del(MemberVo vo) {
		int num = memberService.delete(vo);
		return "redirect:/member/list.do";
	}
}
