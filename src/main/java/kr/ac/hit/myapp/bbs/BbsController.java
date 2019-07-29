package kr.ac.hit.myapp.bbs;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BbsController {
	@Resource
	private BbsService bbsService;
	
	@RequestMapping(value="/bbs/add.do", method=RequestMethod.GET)
	public String addForm() {
		return "bbs/bbsAdd";
	}
	
	@RequestMapping(value="/bbs/add.do", method=RequestMethod.POST)
	public String add(BbsVo vo) {
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
	public String edit(BbsVo vo) {
		int num = bbsService.update(vo);
		return "redirect:/bbs/list.do";
	}
	
	@RequestMapping(value="/bbs/del.do", method=RequestMethod.GET)
	public String del(int bbsNo) {
		int num = bbsService.delete(bbsNo);
		return "redirect:/bbs/list.do";
	}
}
