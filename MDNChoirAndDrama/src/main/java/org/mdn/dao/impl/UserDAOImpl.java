package org.mdn.dao.impl;

import org.mdn.dao.UserDAO;
import org.mdn.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	MongoTemplate mongoTemplate;

	private static final String USER_COLLECTION = "users";

/*	@Override
	public boolean insertUserRegisterationDetails(User user) {
		user.setRole("ROLE_USER");
		User availableUser = findUserByName(user.getEmail());
		if (availableUser != null) {
			return false;
		}
		mongoTemplate.insert(user, USER_COLLECTION);
		return true;
	}*/

	@Override
	public User findUserByName(String email) {
		Query query = new Query(Criteria.where("email").is(email));
		return mongoTemplate.findOne(query, User.class, USER_COLLECTION);
	}

}