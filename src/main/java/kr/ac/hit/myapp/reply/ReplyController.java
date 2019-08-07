package kr.ac.hit.myapp.reply;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ReplyController {
	@Resource
	private ReplyService replyService; 
	
	@RequestMapping("/reply/add.do")
	@ResponseBody //이 메서드의 반환값이 뷰(JSP)이름이 아니라, 반환값 자체가 응답 데이터로 전송
	public Map<String, Object> add(ReplyVo vo) {
		int num = replyService.insert(vo);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", num);
		return resultMap; // { "result" : 1 }
	}
}