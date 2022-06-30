package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@ResponseBody
	@RequestMapping(value="/api/guestbook/add2", method= {RequestMethod.GET, RequestMethod.POST})
	public GuestVo add2(@RequestBody GuestVo gVo) {
		System.out.println("ApiGuestbookController>add()");
		
		return gService.doAddGuest(gVo);
	}
	
	@ResponseBody
	@RequestMapping(value="/api/guestbook/delete", method= {RequestMethod.GET, RequestMethod.POST})
	public int delete(@ModelAttribute GuestVo gVo) {
		System.out.println("ApiGuestbookController>delete()");
		int count = gService.doDeleteGuest(gVo);
		if ( count == -1) {
			System.out.println("비밀번호가 다릅니다");
		} else if (count == 1){
			System.out.println("성공");
		} else {
			System.out.println("try again");
		}
		return count;
	}
}