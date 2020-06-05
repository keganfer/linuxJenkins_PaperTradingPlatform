package com.keg.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keg.dal.StockInfoRepository;
import com.keg.model.StockInfo;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;


@Service
public class StockService {

	private final StockInfoRepository stockRepos;
	private HttpSession session;

	@Autowired
	public StockService(StockInfoRepository stockRepos, HttpSession session) {
		super();
		this.stockRepos = stockRepos;
		this.session = session;
	}

	public void addStocksToSession() {
		
		List<StockInfo> stocks = stockRepos.findAll();
		session.setAttribute("activeStocks" ,stocks);	
		
		
		
	}
	
}
