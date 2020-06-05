package com.keg.dal;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.keg.model.Trades;

@Repository
public interface TradesRepository extends CrudRepository<Trades, Integer>{

	
	
}
