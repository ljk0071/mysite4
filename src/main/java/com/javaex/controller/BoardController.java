package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService bService;

	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Model model, @RequestParam(value = "title", required=false, defaultValue="-1") String title) {
		if (title.equals("-1")) {
			List<BoardVo> bList = bService.getBoardList();
			model.addAttribute("bList", bList);
			return "/board/list";
		} else {
			List<BoardVo> sList = bService.getSearchList(title);
			model.addAttribute("sList", sList);
			return "/board/list";
		}
	}

	@RequestMapping(value = "/writeform", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeform() {
		return "/board/writeForm";
	}

	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@ModelAttribute BoardVo bVo) {
		if(bVo.getTitle() != "" && bVo.getContent() != "") {
			bVo.setContent(bVo.getContent().replace("\r\n", "<br>"));
			bService.doAddBoard(bVo);
			return "redirect:./list";
		} else {
			return "redirect:./writeform?result=fail&no="+bVo.getUserNo();
		}
	}

	@RequestMapping(value = "/read/{no}/{cancel}", method = { RequestMethod.GET, RequestMethod.POST })
	public String read(Model model, @PathVariable("no") int bNo, @PathVariable("cancel") int cancel) {
		model.addAttribute("bVo", bService.getBoard(bNo, cancel));
		return "/board/read";
	}

	@RequestMapping(value = "/modifyForm/{no}", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyForm(Model model, @PathVariable("no") int bNo) {
		BoardVo bVo = bService.getBoard(bNo);
		bVo.setContent(bVo.getContent().replace("<br>", "\r\n"));
		model.addAttribute("bVo", bVo);
		return "/board/modifyForm";
	}

	@RequestMapping(value = "/modify/{no}", method = { RequestMethod.GET, RequestMethod.POST })
	public String modify(Model model,@ModelAttribute BoardVo bVo ) {
		bVo.setContent(bVo.getContent().replace("\r\n", "<br>"));
		bService.doUpdateBoard(bVo);
		return "redirect:../list";
	}

	@RequestMapping(value = "/delete/{no}", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@PathVariable("no") int bNo) {
		bService.doDeleteBoard(bNo);
		return "redirect:../list";
	}

}
