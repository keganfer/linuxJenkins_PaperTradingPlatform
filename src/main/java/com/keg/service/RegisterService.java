package com.keg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keg.dal.UserRepository;
import com.keg.model.User;

@Service
public class RegisterService {

	private final UserRepository userRepos;

	@Autowired
	public RegisterService(UserRepository userRepos) {
		super();
		this.userRepos = userRepos;
	}

	public User register(User toBeRegistered) {
		toBeRegistered.setBalance(10000);
		if (userRepos.countByUsername(toBeRegistered.getUsername()) == 0) {
			
			return userRepos.save(toBeRegistered);
		} else {
			return null;
		}

	}

}
