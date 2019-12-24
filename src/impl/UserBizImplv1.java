package impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import biz.UserBiz;
import entity.User;
import util.FileUtil;

public class UserBizImplv1 implements UserBiz {

	private static final long serialVersionUID = 5585998785611953523L;

	@Override
	public boolean add(User user) {
		Set<User> userSet = FileUtil.ReadUserSet();
		if(userSet == null) userSet = new HashSet<User>();
		userSet.add(user);
		FileUtil.SaveUserSet(userSet);
		return true;
	}

	@Override
	public boolean delete(User user) {
		Set<User> userSet = FileUtil.ReadUserSet();
		if(userSet == null || userSet.size() == 0) return false;
		boolean flag = userSet.remove(user);
		FileUtil.SaveUserSet(userSet);
		return flag;
	}

	@Override
	public User update(User user) {
		Set<User> userSet = FileUtil.ReadUserSet();
		if(userSet == null || userSet.size() == 0) return null;
		userSet.remove(user);
		userSet.add(user);
		FileUtil.SaveUserSet(userSet);
		return user;
	}

	@Override
	public User searchByIsbn(String Isbn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User searchById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User login(User user) {
		Set<User> userSet = FileUtil.ReadUserSet();
		if(userSet == null || userSet.size() == 0) return null;
		if(userSet.contains(user)) {
			Iterator<User> iter = userSet.iterator();
			while(iter.hasNext()) {
				User userDetail = (User)iter.next();
				if(user.equals(userDetail)) {
					return userDetail;
				}
			}
		}
		return null;
	}

}
