package view;

import java.util.Scanner;

import auth.Role;
import entity.Book;
import entity.BookInfo;
import entity.BookState;
import entity.User;
import impl.BookInfoBizImplv1;

public class BookView {
	private Scanner input = null;
	private User loginUser;
	private Role role;

	public BookView() {
		input = new Scanner(System.in);
		showWelcome();
	}

	public void showWelcome() {
		System.out.println("******* 欢迎使用X9图书管理系统  ********");
		System.out.println("  1.登录系统         2.注册          3.退出系统");
		System.out.println("****************************************");
		System.out.println("请选择：");
		String choice = input.next();
		if ("1".equals(choice)) {
			showLoginView();
			if (loginUser == null) {
				System.out.println("你输入的账户密码错误！请重新启动程序！");
				System.exit(0);
			}
			if (role.getKey().equals("administrator")) {
				showMainView_Administrator();
			} else if (role.getKey().equals("operator") || role.getKey().equals("default")) {
				showMainView_Operator();
			}
		} else if ("2".equals(choice)) {
			User user = new User();
			System.out.println("请输入新账户的用户名：");
			user.setUserName(input.next());
			System.out.println("请输入新账户的密码：");
			user.setPassword(input.next());
			System.out.println("请输入您的真实姓名：");
			user.getRole().setName(input.next());
			System.out.println("请输入新账户的权限级别：");
			user.getRole().setKey(input.next());
			user.getRole().addUser(user);
		} 
		System.out.println("操作完成！");
		System.out.println("系统已成功退出！");
	}

	public User showLoginView() {
		User loginUser = new User();
		System.out.println("用户名：");
		loginUser.setUserName(input.next());
		//loginUser.setUserName("Janos");
		System.out.println("密码：");
		loginUser.setPassword(input.next());
		//loginUser.setPassword("123");
		this.loginUser = loginUser.getRole().login(loginUser);
		if (this.loginUser == null) {
			return null;
		} else {
			this.role = this.loginUser.getRole();
			return this.loginUser;
		}
	}

	public void showMainView_Administrator() {
		System.out.println("****************************************");
		System.out.println("欢迎" + role.getName() + "，您已进入" + role.getKey() + "权限的管理系统");
		System.out.println("  1.图书（借出/归还/查询/删除）       2.图书库存（增/减）     3.账户（增/删）       4.退出系统");
		System.out.println("请选择：");
		int choice = input.nextInt();
		if (choice == 1) {
			System.out.println("1.借出     2.归还     3.查询     4.删除     5.退出系统");
			System.out.println("请选择：");
			choice = input.nextInt();
			BookInfo info = new BookInfo();
			System.out.println("请输入要借出的图书的内部bookID：");
			String id = input.next();
			info = info.biz.searchById(id);
			if(info == null)   return;
			Book book = info.searchByBookId(id);
			switch (choice) {
			case 1:
				if (book.getState().compareTo(BookState.可借) == 0) {
					info.updateBooks(id, BookState.已借出);
					info.searchByBookId(id);
				} else {
					System.out.println("该书已借出，借取失败！过几天再来看看吧。");
				}
				break;
			case 2:
				if (book.getState().compareTo(BookState.已借出) == 0) {
					info.updateBooks(id, BookState.可借);
					info.searchByBookId(id);
				} else {
					System.out.println("您上次借走该书时是不是忘了登记啦？");
				}
				break;
			case 3:			
				break;
			case 4:
				info.deleteBooks(id);
				break;

			default:
				System.out.println("已退出系统");
				System.exit(0);
				break;
			}
		} else if (choice == 2) {
			System.out.println("1.增加图书库存     2.减少图书库存     3.退出系统");
			System.out.println("请选择：");
			choice = input.nextInt();
			System.out.println("请输入新图书种类的ISBN：");
			String isbn = input.next();
			switch (choice) {
			case 1:
				System.out.println("图书的入库数量：");
				int inCount = input.nextInt();
				if(role.inStore(isbn, inCount)) {
					System.out.println("入库成功！");
				}else {
					System.out.println("入库失败！");
				}
				break;
			case 2:
				System.out.println("图书的出库数量：");
				int outCount = input.nextInt();
				if(role.outStore(isbn, outCount)) {
					System.out.println("出库成功！");
				}else {
					System.out.println("出库失败！");
				}
				break;
			default:
				System.out.println("已退出系统");
				System.exit(0);
				break;
			}
		} else if (choice == 3) {
			System.out.println("1.添加账户       2.更改账户       3.删除账户     4.退出系统");
			System.out.println("请选择：");
			choice = input.nextInt();
			switch (choice) {
			case 1:
				User user = new User();
				System.out.println("请输入新账户的用户名：");
				user.setUserName(input.next());
				System.out.println("请输入新账户的密码：");
				user.setPassword(input.next());
				System.out.println("请输入您的真实姓名：");
				user.getRole().setName(input.next());
				System.out.println("请输入新账户的权限级别：");
				user.getRole().setKey(input.next());
				user.getRole().addUser(user);
				break;
			case 2:
				User user2 = new User();
				System.out.println("请输入要更改的账户的用户名：");
				user2.setUserName(input.next());
				System.out.println("请输入要更改的账户的密码：");
				user2.setPassword(input.next());
				System.out.println("请输入新的权限级别：");
				user2.getRole().setKey(input.next());
				user2.getRole().updateUser(user2);
				break;
			case 3:
				User user3 = new User();
				System.out.println("请输入要删除的账户的用户名：");
				user3.setUserName(input.next());
				System.out.println("请输入要删除的账户的密码：");
				user3.setPassword(input.next());
				user3.getRole().deleteUser(user3);
				break;
			default:
				System.out.println("已退出系统");
				System.exit(0);
				break;
			}
		} else {
			System.out.println("成功退出系统");
			System.exit(0);
		}
	}

	public void showMainView_Operator() {
		System.out.println("****************************************");
		System.out.println("欢迎" + role.getName() + "，您已进入" + role.getKey() + "权限的管理系统");
		System.out.println("  1.图书（借出/归还/查询/删除）       2.图书库存（增/减）     3.退出系统");
		System.out.println("请选择：");
		int choice = input.nextInt();
		if (choice == 1) {
			System.out.println("1.借出     2.归还     3.查询     4.删除     5.退出系统");
			System.out.println("请选择：");
			choice = input.nextInt();
			BookInfo info = new BookInfo();
			System.out.println("请输入要借出的图书的内部bookID：");
			String id = input.next();
			info = info.biz.searchById(id);
			Book book = info.searchByBookId(id);
			if(book == null) {
				System.out.println("没有这个内部ID的图书！");
				return;
			}
			switch (choice) {
			case 1:
				if (book.getState().compareTo(BookState.可借) == 0) {
					info.updateBooks(id, BookState.已借出);
					info.searchByBookId(id);
				} else {
					System.out.println("该书已借出，借取失败！过几天再来看看吧。");
				}
				break;
			case 2:
				if (book.getState().compareTo(BookState.已借出) == 0) {
					info.updateBooks(id, BookState.可借);
					info.searchByBookId(id);
				} else {
					System.out.println("您上次借走该书时是不是忘了登记啦？");
				}
				break;
			case 3:			
				break;
			case 4:
				info.deleteBooks(id);
				break;

			default:
				System.out.println("已退出系统");
				System.exit(0);
				break;
			}
		} else if (choice == 2) {
			System.out.println("1.增加图书库存     2.减少图书库存     3.退出系统");
			System.out.println("请选择：");
			choice = input.nextInt();
			System.out.println("请输入新图书种类的ISBN：");
			String isbn = input.next();
			switch (choice) {
			case 1:
				System.out.println("图书的入库数量：");
				int inCount = input.nextInt();
				if(role.inStore(isbn, inCount)) {
					System.out.println("入库成功！");
				}else {
					System.out.println("入库失败！");
				}
				break;
			case 2:
				System.out.println("图书的出库数量：");
				int outCount = input.nextInt();
				if(role.outStore(isbn, outCount)) {
					System.out.println("出库成功！");
				}else {
					System.out.println("出库失败！");
				}
				break;
			default:
				System.out.println("已退出系统");
				System.exit(0);
				break;
			}
		} else {
			System.out.println("成功退出系统");
			System.exit(0);
		}
	}
}
