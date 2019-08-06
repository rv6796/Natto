package com.natto.service;

import com.natto.dao.UserDao;
import com.natto.exception.UserAlreadyExistsException;
import com.natto.model.User;

public class UserService {

	public void saveUser(User user) {
		UserDao dao = new UserDao();
		if (dao.getUserPojoByEmailAddress(user.getUserEmailAddress()) == null) {
			dao.saveUser(user);
		} else {
			throw new UserAlreadyExistsException("User has already register");
		}
	}

}
