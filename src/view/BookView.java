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
		System.out.println("******* ��ӭʹ��X9ͼ�����ϵͳ  ********");
		System.out.println("  1.��¼ϵͳ         2.ע��          3.�˳�ϵͳ");
		System.out.println("****************************************");
		System.out.println("��ѡ��");
		String choice = input.next();
		if ("1".equals(choice)) {
			showLoginView();
			if (loginUser == null) {
				System.out.println("��������˻����������������������");
				System.exit(0);
			}
			if (role.getKey().equals("administrator")) {
				showMainView_Administrator();
			} else if (role.getKey().equals("operator") || role.getKey().equals("default")) {
				showMainView_Operator();
			}
		} else if ("2".equals(choice)) {
			User user = new User();
			System.out.println("���������˻����û�����");
			user.setUserName(input.next());
			System.out.println("���������˻������룺");
			user.setPassword(input.next());
			System.out.println("������������ʵ������");
			user.getRole().setName(input.next());
			System.out.println("���������˻���Ȩ�޼���");
			user.getRole().setKey(input.next());
			user.getRole().addUser(user);
		} 
		System.out.println("������ɣ�");
		System.out.println("ϵͳ�ѳɹ��˳���");
	}

	public User showLoginView() {
		User loginUser = new User();
		System.out.println("�û�����");
		loginUser.setUserName(input.next());
		//loginUser.setUserName("Janos");
		System.out.println("���룺");
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
		System.out.println("��ӭ" + role.getName() + "�����ѽ���" + role.getKey() + "Ȩ�޵Ĺ���ϵͳ");
		System.out.println("  1.ͼ�飨���/�黹/��ѯ/ɾ����       2.ͼ���棨��/����     3.�˻�����/ɾ��       4.�˳�ϵͳ");
		System.out.println("��ѡ��");
		int choice = input.nextInt();
		if (choice == 1) {
			System.out.println("1.���     2.�黹     3.��ѯ     4.ɾ��     5.�˳�ϵͳ");
			System.out.println("��ѡ��");
			choice = input.nextInt();
			BookInfo info = new BookInfo();
			System.out.println("������Ҫ�����ͼ����ڲ�bookID��");
			String id = input.next();
			info = info.biz.searchById(id);
			if(info == null)   return;
			Book book = info.searchByBookId(id);
			switch (choice) {
			case 1:
				if (book.getState().compareTo(BookState.�ɽ�) == 0) {
					info.updateBooks(id, BookState.�ѽ��);
					info.searchByBookId(id);
				} else {
					System.out.println("�����ѽ������ȡʧ�ܣ����������������ɡ�");
				}
				break;
			case 2:
				if (book.getState().compareTo(BookState.�ѽ��) == 0) {
					info.updateBooks(id, BookState.�ɽ�);
					info.searchByBookId(id);
				} else {
					System.out.println("���ϴν��߸���ʱ�ǲ������˵Ǽ�����");
				}
				break;
			case 3:			
				break;
			case 4:
				info.deleteBooks(id);
				break;

			default:
				System.out.println("���˳�ϵͳ");
				System.exit(0);
				break;
			}
		} else if (choice == 2) {
			System.out.println("1.����ͼ����     2.����ͼ����     3.�˳�ϵͳ");
			System.out.println("��ѡ��");
			choice = input.nextInt();
			System.out.println("��������ͼ�������ISBN��");
			String isbn = input.next();
			switch (choice) {
			case 1:
				System.out.println("ͼ������������");
				int inCount = input.nextInt();
				if(role.inStore(isbn, inCount)) {
					System.out.println("���ɹ���");
				}else {
					System.out.println("���ʧ�ܣ�");
				}
				break;
			case 2:
				System.out.println("ͼ��ĳ���������");
				int outCount = input.nextInt();
				if(role.outStore(isbn, outCount)) {
					System.out.println("����ɹ���");
				}else {
					System.out.println("����ʧ�ܣ�");
				}
				break;
			default:
				System.out.println("���˳�ϵͳ");
				System.exit(0);
				break;
			}
		} else if (choice == 3) {
			System.out.println("1.����˻�       2.�����˻�       3.ɾ���˻�     4.�˳�ϵͳ");
			System.out.println("��ѡ��");
			choice = input.nextInt();
			switch (choice) {
			case 1:
				User user = new User();
				System.out.println("���������˻����û�����");
				user.setUserName(input.next());
				System.out.println("���������˻������룺");
				user.setPassword(input.next());
				System.out.println("������������ʵ������");
				user.getRole().setName(input.next());
				System.out.println("���������˻���Ȩ�޼���");
				user.getRole().setKey(input.next());
				user.getRole().addUser(user);
				break;
			case 2:
				User user2 = new User();
				System.out.println("������Ҫ���ĵ��˻����û�����");
				user2.setUserName(input.next());
				System.out.println("������Ҫ���ĵ��˻������룺");
				user2.setPassword(input.next());
				System.out.println("�������µ�Ȩ�޼���");
				user2.getRole().setKey(input.next());
				user2.getRole().updateUser(user2);
				break;
			case 3:
				User user3 = new User();
				System.out.println("������Ҫɾ�����˻����û�����");
				user3.setUserName(input.next());
				System.out.println("������Ҫɾ�����˻������룺");
				user3.setPassword(input.next());
				user3.getRole().deleteUser(user3);
				break;
			default:
				System.out.println("���˳�ϵͳ");
				System.exit(0);
				break;
			}
		} else {
			System.out.println("�ɹ��˳�ϵͳ");
			System.exit(0);
		}
	}

	public void showMainView_Operator() {
		System.out.println("****************************************");
		System.out.println("��ӭ" + role.getName() + "�����ѽ���" + role.getKey() + "Ȩ�޵Ĺ���ϵͳ");
		System.out.println("  1.ͼ�飨���/�黹/��ѯ/ɾ����       2.ͼ���棨��/����     3.�˳�ϵͳ");
		System.out.println("��ѡ��");
		int choice = input.nextInt();
		if (choice == 1) {
			System.out.println("1.���     2.�黹     3.��ѯ     4.ɾ��     5.�˳�ϵͳ");
			System.out.println("��ѡ��");
			choice = input.nextInt();
			BookInfo info = new BookInfo();
			System.out.println("������Ҫ�����ͼ����ڲ�bookID��");
			String id = input.next();
			info = info.biz.searchById(id);
			Book book = info.searchByBookId(id);
			if(book == null) {
				System.out.println("û������ڲ�ID��ͼ�飡");
				return;
			}
			switch (choice) {
			case 1:
				if (book.getState().compareTo(BookState.�ɽ�) == 0) {
					info.updateBooks(id, BookState.�ѽ��);
					info.searchByBookId(id);
				} else {
					System.out.println("�����ѽ������ȡʧ�ܣ����������������ɡ�");
				}
				break;
			case 2:
				if (book.getState().compareTo(BookState.�ѽ��) == 0) {
					info.updateBooks(id, BookState.�ɽ�);
					info.searchByBookId(id);
				} else {
					System.out.println("���ϴν��߸���ʱ�ǲ������˵Ǽ�����");
				}
				break;
			case 3:			
				break;
			case 4:
				info.deleteBooks(id);
				break;

			default:
				System.out.println("���˳�ϵͳ");
				System.exit(0);
				break;
			}
		} else if (choice == 2) {
			System.out.println("1.����ͼ����     2.����ͼ����     3.�˳�ϵͳ");
			System.out.println("��ѡ��");
			choice = input.nextInt();
			System.out.println("��������ͼ�������ISBN��");
			String isbn = input.next();
			switch (choice) {
			case 1:
				System.out.println("ͼ������������");
				int inCount = input.nextInt();
				if(role.inStore(isbn, inCount)) {
					System.out.println("���ɹ���");
				}else {
					System.out.println("���ʧ�ܣ�");
				}
				break;
			case 2:
				System.out.println("ͼ��ĳ���������");
				int outCount = input.nextInt();
				if(role.outStore(isbn, outCount)) {
					System.out.println("����ɹ���");
				}else {
					System.out.println("����ʧ�ܣ�");
				}
				break;
			default:
				System.out.println("���˳�ϵͳ");
				System.exit(0);
				break;
			}
		} else {
			System.out.println("�ɹ��˳�ϵͳ");
			System.exit(0);
		}
	}
}
