package com.keg.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import org.springframework.data.annotation.Transient;


@Entity

public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int Id;
	private String username;
	private String password;
	private double balance;
	@OneToMany(cascade = CascadeType.ALL)
	//@JoinTable(name="UsersTrades", joinColumns = @JoinColumn(name="user_id"))
	List<Trades> userTrades = new ArrayList<>();
//	@Transient
//	HashMap<StockInfo,Integer> portfolio = new HashMap<>();
	
	

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public User(String username, String password, double balance) {
		super();
		this.username = username;
		this.password = password;
		this.balance = balance;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Trades> getUserTrades() {
		return userTrades;
	}

	

	@Override
	public String toString() {
		return "User [Id=" + Id + ", username=" + username + ", password=" + password + ", balance=" + balance
				+ ", userTrades=" + userTrades + "]";
	}

	public void setUserTrades(List<Trades> userTrades) {
		this.userTrades = userTrades;
	}

	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	///// CUSTOM //////
	
	public void userAddToTrades(Trades trades) {
		this.userTrades.add(trades);		
	}
	
//	public void buyStocks(Trades trade) {
//		
//		userAddToTrades(trade);
//	}
}
