package com.jb.backend.dao;

import java.util.List;

import com.jb.backend.model.Friend;
import com.jb.backend.model.User;

public interface FriendDao {
	List<User> getSuggestedUsers(String u_phoneNumber);
	void addFriendRequest(Friend friend);
	List<Friend> getPendingRequest(String u_phoneNumber);
	void acceptFriendRequest(Friend friend);
	void deleteFriendRequest(Friend friend);
	List<User> listOfFriends(String u_phoneNumber);
	void deleteFriend(int id,int fid);
	
}