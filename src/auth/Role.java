package auth;

import java.io.IOException;
import java.io.Serializable;
import java.security.Permissions;
import java.security.acl.Permission;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import biz.BizFactory;
import biz.BookInfoBiz;
import biz.UserBiz;
import entity.User;
import exception.NoSuchOptPermissionException;
import util.FileUtil;

public class Role implements Serializable{
	private static final long serialVersionUID = -2629435151927325624L;
	private String name;
	private String key; // ��ӦȨ�������ļ��е�Ȩ��key
	private List<String> permissions; // Ȩ�޼���-�����������ļ��У�Role_Permissions.properties
	private BookInfoBiz bookInfoBiz;
	private UserBiz userBiz;

	public Role() {
		setName("Ĭ�Ͻ�ɫ");
		setKey("default");
		setPermissions(key);
		bookInfoBiz = (BookInfoBiz) BizFactory.getBiz("BookInfoBiz");
		userBiz = (UserBiz) BizFactory.getBiz("UserBiz");
	}

	public Role(String name, String key) {
		setName(name);
		setKey(key);
		setPermissions(key);
		bookInfoBiz = (BookInfoBiz) BizFactory.getBiz("BookInfoBiz");
		userBiz = (UserBiz) BizFactory.getBiz("UserBiz");
	}

	public boolean checkPermission(String optName) {
		if (null == permissions || permissions.size() == 0)
			return false;
		for (String permission : permissions) {
			if (optName.equals(permission))
				return true;
			if (permission.equalsIgnoreCase("bookinfobiz.*") && optName.startsWith("bookinfobiz."))
				return true;
			if (permission.equalsIgnoreCase("userbiz.*") && optName.startsWith("userbiz."))
				return true;
		}
		return false;
	}
	public User login(User user) {
		return userBiz.login(user);
	}

	public boolean inStore(String isbn, int inCount) {
		if (checkPermission("bookinfobiz.inStore")) {
			return bookInfoBiz.inStore(isbn, inCount);
		} else {
			throw new NoSuchOptPermissionException();
		}
	}

	public boolean outStore(String isbn, int outCount) {
		if (checkPermission("bookinfobiz.outStore")) {			
			return bookInfoBiz.outStore(isbn, outCount);
		} else {
			throw new NoSuchOptPermissionException();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getPermissions() {
		return permissions;
	}

	private void setPermissions(String key) {
		// ��ȡ�����ļ�����õ�ǰ��ɫ��Ȩ�޼���
		Properties props = new Properties();
		try {
			props.load(Role.class.getResourceAsStream("Role_Permissions.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String strPermission = props.getProperty(key);
		if (permissions == null)
			permissions = new ArrayList<String>();
		permissions.clear();
		// ����ת��List��Ȼ��ת�ɵ�List������add�ˣ������ʲ��ô˷�ʽ
		// permissions.addAll(Arrays.asList(strPermission.split(",")));
		String[] permissionArray = strPermission.split(",");
		for (String per : permissionArray) {
			if ("".equals(per))
				continue;
			permissions.add(per.trim());
		}

	}

	public BookInfoBiz getBookInfoBiz() {
		return bookInfoBiz;
	}

	public void setBookInfoBiz(BookInfoBiz bookInfoBiz) {
		this.bookInfoBiz = bookInfoBiz;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	public boolean addUser(User user) {
		return userBiz.add(user);
	}
	
	public boolean deleteUser(User user) {
		return userBiz.delete(user);
	}
	
	public User updateUser(User user) {
		return userBiz.update(user);
	}
}
