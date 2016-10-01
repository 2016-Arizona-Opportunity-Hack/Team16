package org.mdn.dao;

import org.mdn.model.User;

public interface UserDAO {

	public User findUserByName(String email);

}