package view;

import java.util.Scanner;

import entity.User;

public class BookView {
	private Scanner input = null;
	
	public void BookView() {
		input = new Scanner(System.in);
		showWelcome();
	}
	
	public void showWelcome() {
		System.out.println("******* 欢迎使用X9图书管理系统  ********");
		System.out.println("\t\t\t1.登录系统\t2.注册\t3.退出系统");
		System.out.println("****************************************");
		System.out.println("请选择：");
		String choice = input.next();
		if("1".equals(choice)) {
			
		}else if("2".equals(choice)) {
			//注册功能自行实现
			
		}else {
			System.out.println("系统已成功推退出！");
		}
	}
	
	public User showLoginView() {
		return null;
	}
	
	public void showMainView_Administrator() {
		
	}
	
	public void showMainView_Operator() {
		
	}
}
