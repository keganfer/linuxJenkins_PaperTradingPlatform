package com.fdm.controllers;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdm.dal.StockInfoRepository;
import com.fdm.dal.TradesRepository;
import com.fdm.model.StockInfo;
import com.fdm.model.Trades;
import com.fdm.model.User;
import com.fdm.service.StockService;
import com.fdm.service.TradeService;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@Controller
public class TradeController {

	
private final TradesRepository tradeRepos;
private final StockService	stockService;
private final TradeService	tradeService;
private final StockInfoRepository stockRepository;
	
	@Autowired
	public TradeController(TradesRepository tradeRepos, StockService stockService, TradeService tradeService,StockInfoRepository stockRepository) {
		super();
		this.tradeRepos = tradeRepos;
		this.stockService = stockService;
		this.tradeService = tradeService;
		this.stockRepository = stockRepository;
	}
	
	
	
	
	@GetMapping(value="/getstock")
	public String getUserStockPage(@ModelAttribute Trades trade,HttpSession session) {
		User foundUser = (User) session.getAttribute("activeUser");
		tradeService.makePortfolio(foundUser.getUsername());
		tradeService.addPortfolioToSession(foundUser.getUsername());
		return "showstock";
		
	}
	

	@PostMapping(value="/processBuyStock")
	public String ProcessStock(@RequestParam String volume, @RequestParam String symbol,HttpSession session,Model model) {
		
		
		stockService.addStocksToSession();
		User foundUser = (User) session.getAttribute("activeUser");
		foundUser = tradeService.saveTrade(volume, symbol,foundUser.getUsername());
		if(foundUser == null) {
			model.addAttribute("errorBalance","Insufficient funds");
			return "buystock";
		}
		tradeService.makePortfolio(foundUser.getUsername());
		tradeService.addPortfolioToSession(foundUser.getUsername());
		
		session.setAttribute("activeUser", foundUser);
	
		return "dashboard";
		
	}
	
	@PostMapping(value="/processSellStock")
	public String ProcessSellStock(@RequestParam String volume, @RequestParam String symbol,HttpSession session,Model model) {
		
		User foundUser = (User) session.getAttribute("activeUser");
		foundUser = tradeService.sellstock(volume, symbol,foundUser.getUsername());
		if(foundUser == null) {
			model.addAttribute("errorDontOwnStock","You don't own any stocks of " + symbol + " or you're trying to sell too many stocks");
			return "sellstock";
		}
		tradeService.makePortfolio(foundUser.getUsername());
		tradeService.addPortfolioToSession(foundUser.getUsername());
		
		session.setAttribute("activeUser", foundUser);
	
		return "dashboard";
		
	}
	
	@PostMapping(value="/processAddStock")
	public String ProcessAddStock(@RequestParam String symbol,HttpSession session,Model model) throws IOException {
	

		Stock stock = YahooFinance.get(symbol);
		if(stock==null)
		{
		model.addAttribute("errorStockNotFound","Stock not found, please add stocks by stock symbol only! ");
		return "dashboard";	
		}
		StockInfo stockInfo = new StockInfo(stock.getSymbol(), stock.getName(), stock.getCurrency(),
		stock.getStockExchange(), stock.getQuote().getPrice(), stock.getQuote().getVolume());
		stockRepository.save(stockInfo);
		stockService.addStocksToSession();
		return "dashboard";
		
	}
}
