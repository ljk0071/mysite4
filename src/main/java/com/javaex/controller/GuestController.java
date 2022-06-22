package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.GuestService;
import com.javaex.vo.GuestVo;

@Controller
@RequestMapping("/guest")
public class GuestController {

	@Autowired
	private GuestService gService;

	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Model model) {
		model.addAttribute("gList", gService.getGuestList());
		return "/guestbook/addList";
	}

	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@ModelAttribute GuestVo gVo) {
		if(gVo.getName() != "" && gVo.getContent() != "" && gVo.getPassword() != "") {
			gVo.setContent(gVo.getContent().replace("\r\n", "<br>"));
			gService.doAddGuest(gVo);
			return "redirect:./list";
		} else {
			return "redirect:./list?result=fail";
		}
	}

	@RequestMapping(value = "/deleteform", method = { RequestMethod.GET, RequestMethod.POST })
	public String deleteform() {
		return "/guestbook/deleteForm";
	}

	@RequestMapping(value = "/delete/{no}", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@PathVariable("no") int gNo, @RequestParam(value = "password") String pw) {
		if (pw.equals(gService.getGuset(gNo).getPassword())) {
			gService.doDeleteGuest(gNo);
			return "redirect:../list";
		} else {
			return "redirect:../deleteform?result=fail&no=" + gNo;
		}
	}
}
