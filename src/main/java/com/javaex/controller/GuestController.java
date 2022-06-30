package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	public String delete(@ModelAttribute GuestVo gVo) {
		int count = gService.doDeleteGuest(gVo);
		if ( count == -1) {
			System.out.println("비밀번호가 다릅니다");
			return "redirect:../deleteform?result=fail&no=" + gVo.getNo();
		} else if (count == 1){
			System.out.println("성공");
			return "redirect:../list";
		} else {
			System.out.println("try again");
			return "redirect:../deleteform?result=fail&no=" + gVo.getNo();
		}
	}
}
