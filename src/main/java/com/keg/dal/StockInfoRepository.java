package com.keg.dal;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.keg.model.StockInfo;



public interface StockInfoRepository  extends CrudRepository<StockInfo, Integer>{

	double countBySymbol(String symbol);
	StockInfo findBySymbol(String symbol);
	List<StockInfo> findAll();
	
//	@Query("select si.symbol from StockInfo si inner join User u where u.id= :si.id")
//	List<StockInfo> findById();
}
