package com.fdm.dal;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.fdm.model.Trades;

@Repository
public interface TradesRepository extends CrudRepository<Trades, Integer>{

	
	
}
