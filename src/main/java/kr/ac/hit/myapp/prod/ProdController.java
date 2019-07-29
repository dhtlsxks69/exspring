package kr.ac.hit.myapp.prod;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProdController {
	@Resource
	private ProdService prodService;
	
	@RequestMapping(value="/prod/add.do", method=RequestMethod.GET)
	public String addForm() {
		return "prod/prodAdd";
	}
	
	@RequestMapping(value="/prod/add.do", method=RequestMethod.POST)
	public String add(ProdVo vo) {
		int num = prodService.insert(vo);
		return "redirect:/prod/list.do";
	}
	
	@RequestMapping(value="/prod/list.do", method=RequestMethod.GET)
	public String list(Map model) {
		List<ProdVo> list = prodService.selectList();
		model.put("prodList", list);
		return "prod/prodList";
	}
	
	@RequestMapping(value="/prod/edit.do", method=RequestMethod.GET)
	public String editForm(int prodNo, Map model) {
		ProdVo vo = prodService.select(prodNo);
		model.put("prodVo", vo);
		return "prod/prodEdit";
	}
	
	@RequestMapping(value="/prod/edit.do", method=RequestMethod.POST)
	public String edit(ProdVo vo) {
		int num = prodService.update(vo);
		return "redirect:/prod/list.do";
	}
	
	@RequestMapping(value="/prod/del.do", method=RequestMethod.GET)
	public String del(int prodNo) {
		int num = prodService.delete(prodNo);
		return "redirect:/prod/list.do";
	}
}
