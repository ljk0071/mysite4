package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestService;
import com.javaex.vo.GuestVo;

@Controller
public class ApiGuestbookController {
	
	@Autowired
	private GuestService gService;

	@RequestMapping(value="/api/guestbook/addList", method= {RequestMethod.GET, RequestMethod.POST})
	public String addList() {
		System.out.println("ApiGuestbookController>addList()");
		
		return "apiguestbook/addList";
	}
	
	@ResponseBody
	@RequestMapping(value="/api/guestbook/list", method= {RequestMethod.GET, RequestMethod.POST})
	public List<GuestVo> list() {
		System.out.println("ApiGuestbookController>list()");
		return gService.getGuestList() ;
	}
	
	@ResponseBody
	@RequestMapping(value="/api/guestbook/add", method= {RequestMethod.GET, RequestMethod.POST})
	public GuestVo add(@ModelAttribute GuestVo gVo) {
		System.out.println("ApiGuestbookController>add()");
		
		return gService.doAddGuest(gVo);
	}
}