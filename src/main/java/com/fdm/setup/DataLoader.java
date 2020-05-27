
package com.fdm.setup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.fdm.dal.StockInfoRepository;
import com.fdm.dal.UserRepository;
import com.fdm.model.StockInfo;
import com.fdm.model.User;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@Component
public class DataLoader implements ApplicationRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private StockInfoRepository stockRepository;
	
	@Autowired
	private HttpSession session;
	

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("Adding user to database");
		//userRepository.save(new User("admin", "pass123", 1000));
		List<StockInfo> stocks = stockRepository.findAll();
		stocks = setDefaultStocks(stocks);
		stockRepository.saveAll(stocks);
		

	}

	public List<StockInfo> setDefaultStocks(List<StockInfo> stockInfos) throws IOException {
		List<StockInfo> stocks = new ArrayList<>();
		List<String> stocknames = new  ArrayList<>();
				
		for(int i = 0; i < stockInfos.size(); i++) {
			stocknames.add(stockInfos.get(i).getSymbol());
		}
	
//		stocknames.add("GOOG");
//		stocknames.add("INTC");
//		stocknames.add("AMZN");
//		stocknames.add("TSLA");
		
		for (int i = 0; i < stocknames.size(); i++) {
			
			if(stockRepository.countBySymbol(stocknames.get(i))== 0) { //if stock is not in database
				System.out.println("adding stocks");
				Stock stock = YahooFinance.get(stocknames.get(i));
				StockInfo stockInfo = new StockInfo(stock.getSymbol(), stock.getName(), stock.getCurrency(),
				stock.getStockExchange(), stock.getQuote().getPrice(), stock.getQuote().getVolume());
				stocks.add(stockInfo);
			}
			else { //update prices
				System.out.println("updating stock price of " + stocknames.get(i));
				StockInfo stockInfo = stockRepository.findBySymbol(stocknames.get(i));
				Stock stock = YahooFinance.get(stocknames.get(i));
				stockInfo.setPrice(stock.getQuote().getPrice());
				stockRepository.save(stockInfo);
				
			}
			
		}
		//session.setAttribute("activeStocks",stocks);
		return stocks;
	}

	

}
