package com.fdm.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.fdm.model.Trades;
import com.fdm.model.User;
import com.fdm.service.TradeService;

@Controller
public class DashboardController {
	
	
	private final TradeService	tradeService;

	
	public DashboardController(TradeService tradeService) {
		super();
		this.tradeService = tradeService;
	}

	@GetMapping(value="/getdashboard")
	public String getUserStockPage(@ModelAttribute Trades trade,HttpSession session) {
		User foundUser = (User) session.getAttribute("activeUser");
		tradeService.makePortfolio(foundUser.getUsername());
		tradeService.addPortfolioToSession(foundUser.getUsername());
		return "dashboard";
		
	}
	
	@GetMapping(value="/sellstock")
	public String getsellStockPage(@ModelAttribute Trades trade) {
		return "sellstock";
		
	}
	
	@GetMapping(value="/buystock")
	public String BuystockPage(@ModelAttribute Trades trade) {
		return "buystock";
		
	}
	
}
