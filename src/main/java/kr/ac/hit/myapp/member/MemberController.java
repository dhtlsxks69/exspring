package kr.ac.hit.myapp.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MemberController {
	
	//GET방식으로 보내기
	@RequestMapping(value="/member/add.do", method=RequestMethod.GET)
	public String addForm() {
		return "member/memAdd";
	}
	
	//POST방식으로 보내기	
	@RequestMapping(value="/member/add.do", method=RequestMethod.POST)
	public String add(MemberVo vo) {
		System.out.println(vo.getMemId());
		System.out.println(vo.getMemPass());
		System.out.println(vo.getMemName());
		System.out.println(vo.getMemPoint());
		//파라미터로 넘어온 값들을 받아서 데이터베이스에 추가(insert)
		return "member/memAdd";
	}
}
