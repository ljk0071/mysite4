package com.javaex.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.RboardService;
import com.javaex.vo.RboardVo;

@Controller
@RequestMapping("/rboard")
public class RboardController {
	
	@Autowired
	private RboardService rService;
	
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Model model, @RequestParam(value = "crtPage", required=false, defaultValue = "1") int crtPage,@RequestParam(value = "keyword", required=false) String keyword) {
		Map<String, Object> pMap = rService.getRboardList(crtPage, keyword);
		model.addAttribute("pMap", pMap);
		return "/rboard/list";
	}
	
	@RequestMapping(value = "/writeform/{gNo}/{oNo}/{depth}", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeform(@PathVariable("gNo") int gNo, @PathVariable("oNo") int oNo, @PathVariable("depth") int depth, Model model) {
		RboardVo rVo = new RboardVo(gNo, oNo, depth);
		model.addAttribute("rVo", rVo);
		return "/rboard/writeForm";
	}
	
	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@ModelAttribute RboardVo rVo) {
		if(rVo.getTitle() != "" && rVo.getContent() != "") {
			rService.doAddBoard(rVo);
			return "redirect:./list";
		} else {
			return "redirect:./writeform?result=fail&no="+rVo.getUserNo();
		}
	}
	
	@RequestMapping(value = "/read/{no}/{cancel}", method = { RequestMethod.GET, RequestMethod.POST })
	public String read(Model model, @PathVariable("no") int rNo, @PathVariable("cancel") int cancel) {
		model.addAttribute("rVo", rService.getRboard(rNo, cancel));
		return "/rboard/read";
	}
	
	@RequestMapping(value = "/modifyForm/{no}", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyForm(Model model, @PathVariable("no") int rNo) {
		RboardVo rVo = rService.getRboard(rNo);
		rVo.setContent(rVo.getContent().replace("<br>", "\r\n"));
		rVo.setTitle(rVo.getTitle().replace("▶",""));
		model.addAttribute("rVo", rVo);
		return "/rboard/modifyForm";
	}
	
	@RequestMapping(value = "/modify/{no}", method = { RequestMethod.GET, RequestMethod.POST })
	public String modify(Model model,@ModelAttribute RboardVo rVo ) {
		rService.doUpdateRboard(rVo);
		return "redirect:../list";
	}

	@RequestMapping(value = "/delete/{no}", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@PathVariable("no") int rNo) {
		rService.doDeleteRboard(rNo);
		return "redirect:../list";
	}
	

}
