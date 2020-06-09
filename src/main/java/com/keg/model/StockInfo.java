package com.keg.model;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class StockInfo {


	
	//@Column(name ="STOCK_ID")
	 @Id 
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 private int id;
	 private String symbol;
	 private String name;
	 private String currency;
	 private String stockExchange;
	 private BigDecimal price;
	 private Long volume;
	 @OneToMany(mappedBy = "stockInfo")
	// @JoinTable(name="StocksTrades", joinColumns = @JoinColumn(name="stock_id"))
	 List<Trades> userTrades = new ArrayList<>();

	

	public Long getVolume() {
		return volume;
	}

	public void setVolume(Long volume) {
		this.volume = volume;
	}

public StockInfo(String symbol, String name, String currency, String stockExchange, BigDecimal price,
			Long volume) {
		super();
	
		this.symbol = symbol;
		this.name = name;
		this.currency = currency;
		this.stockExchange = stockExchange;
		this.price = price;
		this.volume = volume;
	}

public void setUserTrades(Trades trades) { //MODIFIED THE SETTER FOR TRADES
	this.userTrades.add(trades);		
}

//public void setUserTrades(List<Trades> userTrades) {
//	this.userTrades = userTrades;
//}

//	public StockInfo(int id, String symbol, String name, String currency, String stockExchange, BigDecimal price) {
//		super();
//		this.id = id;
//		this.symbol = symbol;
//		this.name = name;
//		this.currency = currency;
//		this.stockExchange = stockExchange;
//		this.price = price;
//		
//	}
//	
//	public StockInfo(String symbol, String name, String currency, String stockExchange, BigDecimal price) {
//		super();
//		
//		this.symbol = symbol;
//		this.name = name;
//		this.currency = currency;
//		this.stockExchange = stockExchange;
//		this.price = price;
//	}

	
	

	@Override
	public String toString() {
		return "StockInfo [symbol=" + symbol + ", name=" + name + ", currency=" + currency + ", stockExchange="
				+ stockExchange + ", price=" + price + ",volume= "+volume +"]";
	}

	public StockInfo(int id, String symbol, String name, String currency, String stockExchange, BigDecimal price,
			Long volume) {
		super();
		this.id = id;
		this.symbol = symbol;
		this.name = name;
		this.currency = currency;
		this.stockExchange = stockExchange;
		this.price = price;
		this.volume = volume;
	}

	public StockInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<Trades> getUserTrades() {
		return userTrades;
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getStockExchange() {
		return stockExchange;
	}

	public void setStockExchange(String stockExchange) {
		this.stockExchange = stockExchange;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}




	 
	 
}
