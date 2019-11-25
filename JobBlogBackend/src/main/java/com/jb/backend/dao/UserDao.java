package com.jb.backend.dao;

import java.util.List;

import com.jb.backend.model.User;

public interface UserDao {
		public void addUser(User user);
		public boolean updateUser(User user);
		public boolean deleteUser(User user);
		public List<User> listUsers();
		public User getUser(String u_phoneNumber);
		public User getUserPn(String u_phoneNumber);
		boolean isU_NumberUnique(String u_phoneNumber);
		User login(User user);
}
