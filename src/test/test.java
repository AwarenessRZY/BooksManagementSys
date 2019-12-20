package test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import auth.Role;
import entity.Book;
import entity.BookInfo;
import entity.User;
import impl.BookInfoBizImplv1;
import util.FileUtil;

public class test {

	public static void main(String[] args) {
		// testChangeStock();
		// testRole();
		testUser();
	}

	public static void testUser() {
		User user = new User(new Role("ͼ��ݵǼ�Ա", "operator"));
		user.setUserName("Janos");
		//user.setPassword("123");
		Set<User> userSet = new HashSet<User>();
		userSet.add(user);
		FileUtil.SaveUserSet(userSet);

		Set<User> userSet2 = FileUtil.ReadUserSet();
		System.out.println(userSet2);
//		for (User u : userSet2) {
//			System.out.println(u.getUserName());
//		}
	}

	public static void testRole() {
		Role role = new Role("Zed", "administrator");
		Role role2 = new Role();
		// System.out.println("�û��������ǣ�" + role.getName());
		// System.out.println("�û���Ȩ���ǣ�" + role.getPermissions());
		System.out.println(role.inStore("123", 1));
		// System.out.println(role2.inStore("123", 1));
	}

	public static void testBookInfo() {
		BookInfo info1 = new BookInfo();
		info1.setIsbn("123-456");
		Book book = new Book();
		book.setIsbn("123-456");
		info1.addBook(book);
	}

	public static void testChangeStock() {
		Map<String, BookInfo> map = new HashMap<String, BookInfo>();
		FileUtil.SaveBookInfoMap(map);
		BookInfo info1 = new BookInfo();
		info1.setIsbn("123-456");
		Book book = new Book();
		book.setIsbn("123-456");
		info1.addBook(book);
		info1.setInStoreCount(10);
		BookInfoBizImplv1 impl = new BookInfoBizImplv1();
		impl.add(info1);
		impl.inStore("123-456", 5);
		impl.outStore("123-456", 3);
		System.out.println(impl.searchByIsbn("123-456").getInStoreCount());
	}
}
