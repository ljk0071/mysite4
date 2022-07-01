package com.javaex.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService2;
import com.javaex.vo.UserVo;

@Controller
public class ApiUserController {

	@Autowired
	private UserService2 uService;

	@RequestMapping(value = "/api/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login() {
		return "/apiuser/loginForm";
	}

	@RequestMapping(value = "/api/join", method = { RequestMethod.GET, RequestMethod.POST })
	public String join() {
		System.out.println("api/join");
		return "/apiuser/joinForm";
	}

	@RequestMapping(value = "/api/joinOk", method = { RequestMethod.GET, RequestMethod.POST })
	public String joinOk() {
		System.out.println("api/joinOk");

		return "/apiuser/joinOk";
	}

	@ResponseBody
	@RequestMapping(value = "/api/ovrlapchk", method = { RequestMethod.GET, RequestMethod.POST })
	public int ovrlapchk(@RequestBody String id) {
		System.out.println("api/ovrlapchk");
		String realId = id.replace("=", "");

		if (uService.doChkId(realId) == null) {
			return 1;
		} else {
			return 0;
		}
	}

	@ResponseBody
	@RequestMapping(value = "/api/joinCheck", method = { RequestMethod.GET, RequestMethod.POST })
	public int joinCheck(@RequestBody UserVo uVo) {
		System.out.println("api/joinCheck");

		System.out.println(uVo);

		int cnt = uService.doAddUser(uVo);

		return cnt;
	}

	@ResponseBody
	@RequestMapping(value = "/api/loginCheck", method = { RequestMethod.GET, RequestMethod.POST })
	public UserVo loginCheck(@RequestBody UserVo uVo) {
		System.out.println("api/loginCheck");
		if (uService.getUser(uVo.getId(), uVo.getPw()) == null) {
			return uVo;
		} else {
			System.out.println(uService.getUser(uVo.getId(), uVo.getPw()));
			return uService.getUser(uVo.getId(), uVo.getPw());
		}
	}

}
