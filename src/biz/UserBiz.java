package biz;

import entity.User;

public interface UserBiz extends Biz<User> {
	public User login(User user);
}
