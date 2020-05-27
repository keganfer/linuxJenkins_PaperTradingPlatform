package com.fdm.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity

public class Trades {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int id;
	private double volume;
	
	
//	public BigDecimal getPriceAtBuy() {
//		return PriceAtBuy;
//	}
//	public void setPriceAtBuy(BigDecimal priceAtBuy) {
//		PriceAtBuy = priceAtBuy;
//	}
	@ManyToOne
	private StockInfo stockInfo;
	
	@Override
	public String toString() {
		return "Trades [id=" + id + ", volume=" + volume + ", stockInfo=" + stockInfo + "]";
	}
	public StockInfo getStockInfo() {
		return stockInfo;
	}
	public void setStockInfo(StockInfo stockInfo) {
		this.stockInfo = stockInfo;
	}
	public Trades() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	 
	public Trades(double volume) {
		super();
		this.volume = volume;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getVolume() {
		return volume;
	}
	public void setVolume(double volume) {
		this.volume = volume;
	}
	
}
