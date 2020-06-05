package com.keg.controllers;





import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.keg.dal.StockInfoRepository;
import com.keg.model.User;
import com.keg.service.RegisterService;
import com.keg.service.StockService;



@Controller
public class RegisterController {

	
	private final RegisterService registerService ;

	private final StockService stockService;
	
	
	@Autowired
	public RegisterController(RegisterService registerService,StockService stockService) {
		super();
		this.registerService = registerService;
		this.stockService = stockService;
	}
	
	@Autowired
	StockInfoRepository stockRepos;


	@GetMapping(value = "/getRegister")
	public String getRegisterPage(HttpSession session, Model model) {
		stockService.addStocksToSession();
		if (session.getAttribute("activeUser") != null) {
			return "dashboard";
		} else {
			model.addAttribute("user", new User());
			return "register";
		}
	}

	
	@PostMapping("/processRegister")
	public String processRegistration(Model model,HttpSession session,@ModelAttribute User user) {
		
		User registeredUser = registerService.register(user);
		if(registeredUser!=null) {
			
			session.setAttribute("activeUser",registeredUser);
			return "dashboard";
		}
		else {
			model.addAttribute("errorMessage","Username is taken");
			return "register";
			
		}
		
	}
	
	
	
}
