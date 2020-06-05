package com.keg.service;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.keg.dal.StockInfoRepository;
import com.keg.dal.TradesRepository;
import com.keg.dal.UserRepository;
import com.keg.model.StockInfo;
import com.keg.model.Trades;
import com.keg.model.User;

@Service
public class TradeService {

	private final TradesRepository tradeRepos;
	private final StockInfoRepository stockRepos;
	private final UserRepository userRepos;
	private HttpSession session;

	@Autowired
	public TradeService(TradesRepository tradeRepos, StockInfoRepository stockRepos, UserRepository userRepos,
			HttpSession session) {
		super();
		this.tradeRepos = tradeRepos;
		this.stockRepos = stockRepos;
		this.userRepos = userRepos;
		this.session = session;
	}

	public User saveTrade(String volume, String symbol, String username) {
		Trades trade = new Trades();
		int amount = Integer.parseInt(volume);
		trade.setVolume(amount);

		StockInfo stockt = stockRepos.findBySymbol(symbol);

		trade.setStockInfo(stockt);
		stockt.setUserTrades(trade);
		

		double price = stockt.getPrice().doubleValue();
		double cost = amount * price;
		
		

		User foundUser = userRepos.findByUsername(username);
		
		if(cost > foundUser.getBalance())
		{
			foundUser = null;
			return foundUser;
		}
		
		stockRepos.save(stockt); // persist StockInfo
		double newBalance = foundUser.getBalance() - cost;
		DecimalFormat df = new DecimalFormat("#.#");
		df.setRoundingMode(RoundingMode.CEILING);
		String formattedDouble = df.format(newBalance);
		
		Double formattedBalance = Double.parseDouble(formattedDouble);
		foundUser.setBalance(formattedBalance);
		Trades trade1 = tradeRepos.save(trade); // persist trade
		
		foundUser.userAddToTrades(trade1);

		

		User saveUser = userRepos.save(foundUser); // persist user
		return saveUser;
	}

	public HashMap<StockInfo, Double> makePortfolio(String username) {
		User foundUser = userRepos.findByUsername(username);
		HashMap<StockInfo, Double> portf = new HashMap<>();

		for (Trades gotTrade : foundUser.getUserTrades()) {
			
			if (gotTrade.getVolume() > 0) {
				if (portf.containsKey(gotTrade.getStockInfo())) { // if that stock is in the porfolio
					double newStockAmount = portf.get(gotTrade.getStockInfo());
					double newVolume = newStockAmount + gotTrade.getVolume();
					portf.put(gotTrade.getStockInfo(), newVolume);
					// System.out.println("newStockAMount: " + newStockAmount);
				} else {
					// System.out.println("here in else");
					portf.put(gotTrade.getStockInfo(), gotTrade.getVolume());
				}
			}
			else {
				
				if (portf.containsKey(gotTrade.getStockInfo())) { // if that stock is in the porfolio
					double newStockAmount = portf.get(gotTrade.getStockInfo());
					double newVolume = newStockAmount - (-1 * gotTrade.getVolume());
					portf.put(gotTrade.getStockInfo(), newVolume);
					// System.out.println("newStockAMount: " + newStockAmount);
					if(newVolume ==0) {
						portf.remove(gotTrade.getStockInfo());
					}
				} 
				
				
			}
		}


		return portf;
	}

	public void addPortfolioToSession(String username) {

		User foundUser = userRepos.findByUsername(username);
		HashMap<StockInfo, Double> portf = makePortfolio(foundUser.getUsername());
		session.setAttribute("ownedStocks", portf);

	}

	public User sellstock(String volume, String symbol, String username) {


		Trades trade = new Trades();
		int amount = -1*Integer.parseInt(volume);
		trade.setVolume(amount);

		addPortfolioToSession(username);
		HashMap<StockInfo, Double> portf = (HashMap<StockInfo, Double>) session.getAttribute("ownedStocks");
		StockInfo stockt = stockRepos.findBySymbol(symbol);
		if(portf.containsKey(stockt)) {
			//System.out.println("this si the amount in map: "+ portf.get(stockt) + "this amount to sell: " + amount );
			if(portf.get(stockt) < (-1* amount)) {
				return null;
			}
			
			trade.setStockInfo(stockt);
			stockt.setUserTrades(trade);

			Trades trade1 = tradeRepos.save(trade); // persist trade

			double price = stockt.getPrice().doubleValue();
			double cost = (-1* amount) * price;
			stockRepos.save(stockt); // persist StockInfo

			User foundUser = userRepos.findByUsername(username);
			foundUser.userAddToTrades(trade1);

			double newBalance = foundUser.getBalance() + cost;
			DecimalFormat df = new DecimalFormat("#.#");
			df.setRoundingMode(RoundingMode.CEILING);
			String formattedDouble = df.format(newBalance);
			
			Double formattedBalance = Double.parseDouble(formattedDouble);
			foundUser.setBalance(formattedBalance);

			User saveUser = userRepos.save(foundUser); // persist user
			return saveUser;
			
		}
		else {
			return null;
		}
		
		

	}

}
