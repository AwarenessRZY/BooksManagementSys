package entity;

import java.io.Serializable;
import java.util.Date;

import auth.Role;

public class User implements Serializable {
	
	private static final long serialVersionUID = 3320803810837555574L;
	private String userName;
	private String password;
	private Role role; // 当前用户的角色
	//private Date lastedLoginTime; // 上次登陆时间
	
	public User() {
		role = new Role();
	}
	
	public User(Role role) {
		if(null == role) role = new Role();
		setRole(role);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (null == obj)
			return false;
		if (!(obj instanceof User))
			return false;
		User user = (User) obj;
		
		return userName.equals(user.getUserName()) && password.equals(user.getPassword());
	}
	
	@Override
	public int hashCode() {

		return 0;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

//	public Date getLastedLoginTime() {
//		return lastedLoginTime;
//	}
//
//	public void setLastedLoginTime(Date lastedLoginTime) {
//		this.lastedLoginTime = lastedLoginTime;
//	}

}
