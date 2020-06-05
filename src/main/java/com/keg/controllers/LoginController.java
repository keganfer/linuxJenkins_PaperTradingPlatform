package com.keg.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.keg.dal.UserRepository;
import com.keg.model.User;
import com.keg.service.StockService;

@Controller
public class LoginController {

	private final UserRepository userRepos;
	private final StockService stockService;

	@Autowired
	public LoginController(UserRepository userRepos, StockService stockService) {
		super();
		this.userRepos = userRepos;
		this.stockService = stockService;
	}

	@GetMapping(value = "/getLogin")
	public String getLoginPage(Model model) {
		model.addAttribute("user", new User());
		return "login";

	}

	

	@GetMapping(value = "/")
	public String getLandingPage() {

		return "landing";

	}


	@PostMapping(value = "/processLogin") // logic of loggin in our user
	public String processLogin(HttpSession session, @ModelAttribute User user, Model model) {

		if (userRepos.countByUsername(user.getUsername()) > 0) {// username exists in database
			
			User foundUser = userRepos.findByUsername(user.getUsername());
			if (foundUser.getPassword().equals(user.getPassword())) {
				session.setAttribute("activeUser", foundUser);
				stockService.addStocksToSession();
				return "dashboard";
			} else {
				model.addAttribute("incorPassMessage", "Password is incorrect");
				return "login";
			}
		} else {
			model.addAttribute("regMessage", "Please register first, your account does not exist");
			return "register";
		}
	}

}
