package com.ms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
	
	//Index page
	@GetMapping("/")
	public String getIndexPage() {
		return "index";
	}
		

}
