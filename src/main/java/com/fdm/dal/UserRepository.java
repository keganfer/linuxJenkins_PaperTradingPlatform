package com.fdm.dal;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fdm.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

	//List<User> findByUsername(String username);
	User findByUsername(String username);
	long countByUsername(String username);

	//print all of users trades
//	@Query("select u.username trades.id from trades t inner join User u where u.id= :t.id")
//	List<User> findById();
	
}
