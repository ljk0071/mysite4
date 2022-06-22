package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService uService;
	
	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login() {
		return "/user/loginForm";
	}
	@RequestMapping(value = "/join", method = { RequestMethod.GET, RequestMethod.POST })
	public String join() {
		return "/user/joinForm";
	}
	@RequestMapping(value = "/joinOk", method = { RequestMethod.GET, RequestMethod.POST })
	public String joinOk(@ModelAttribute UserVo uVo) {
		if (uVo.getId() != "" && uVo.getPw() != "" && uVo.getName() != "" && uVo.getGender() != "") {
			return "/user/joinOk";
		}else {
			return "redirect:./join?result=fail";
		}
	}
	@RequestMapping(value = "/loginCheck", method = { RequestMethod.GET, RequestMethod.POST })
	public String loginCheck(HttpSession session, @RequestParam("id") String id, @RequestParam("pw") String pw) {
		if (uService.getUser(id, pw) != null) {
			session.setAttribute("authUser", uService.getUser(id, pw));
			return "redirect:../main";
		} else {
			return "redirect:/login?result=fail";
		}
	}
	@RequestMapping(value = "/logout", method = {RequestMethod.GET, RequestMethod.POST})
	public String logout(HttpSession session) {
		session.removeAttribute("authUser");
		return "redirect:../main";
	}
	
	@RequestMapping(value = "/modifyForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyForm(Model model, HttpSession session) {
		UserVo aVo = (UserVo)session.getAttribute("authUser");
		model.addAttribute("authUser", uService.getUser(aVo.getNo()));
		return "/user/modifyForm";
	}
	@RequestMapping(value = "/modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String modify(@ModelAttribute UserVo uVo) {
		if (uVo.getPw() != "" && uVo.getName() != "" && uVo.getGender() != "") {
			uService.doUpdatePerson(uVo);
			return "redirect:../main";
		} else {
			return "redirect:./modifyForm?result=fail";
		}
	}
}
