package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;

@Controller
@RequestMapping("gallery")
public class GalleryController {
	
	@Autowired
	private GalleryService gService;
	
	@RequestMapping(value="/list", method= {RequestMethod.GET,RequestMethod.POST})
	public String list(Model model) {
		System.out.println("GalleryController>list()");
		model.addAttribute("gList", gService.getList());
		return "/gallery/list";
	}
	
	@RequestMapping(value="/upload", method= {RequestMethod.GET,RequestMethod.POST})
	public String upload(@ModelAttribute GalleryVo gVo) {
		System.out.println("GalleryController>upload()");
		
		gService.save(gVo);
		
		return "redirect:./list";
	}
	
	@ResponseBody
	@RequestMapping(value="/delete", method= {RequestMethod.GET,RequestMethod.POST})
	public int delete(@RequestBody int no) {
		System.out.println("GalleryController>delete()");
		
		int count = -1;
		
		count = gService.delete(no);
		
		return count;
	}


}
