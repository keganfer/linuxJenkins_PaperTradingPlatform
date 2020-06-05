package com.keg.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.keg.model.User;

@Controller
public class WelcomeController {

	@GetMapping(value="/getUser")
	public String getWelcomePage(HttpSession session) {
		User foundUser = (User) session.getAttribute("activeUser");
		System.out.println("foundUser = "+foundUser.getUsername()+" "+foundUser.getPassword());
		
		return "welcome" ;
		
	}
	
	
	
//	@GetMapping(value="/check")
//	public String attributeCheck(HttpSession session) {
//		String foundName = (String) session.getAttribute("myAttribute");
//		System.out.println("foundName = "+ foundName);
//		return "welcome" ;
//	}
//		
//		
//	
//	
//	@GetMapping(value="/find/{name}")
//	public String findByName(@PathVariable String name){
//		System.out.println("name = " +name);
//		return "welcome";
//		
//	}
	
}
