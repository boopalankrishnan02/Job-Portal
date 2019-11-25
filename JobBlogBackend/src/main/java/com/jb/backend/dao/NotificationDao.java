package com.jb.backend.dao;

import java.util.List;

import com.jb.backend.model.Notification;

public interface NotificationDao {
	Notification getNotification(int n_id);
	void addNotification(Notification notification);
	void updateNotification(int n_id);
	List<Notification> getNotificationNotViewed(String u_phoneNumber);
}
