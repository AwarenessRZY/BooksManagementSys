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
		System.out.println("******* ��ӭʹ��X9ͼ�����ϵͳ  ********");
		System.out.println("\t\t\t1.��¼ϵͳ\t2.ע��\t3.�˳�ϵͳ");
		System.out.println("****************************************");
		System.out.println("��ѡ��");
		String choice = input.next();
		if("1".equals(choice)) {
			
		}else if("2".equals(choice)) {
			//ע�Ṧ������ʵ��
			
		}else {
			System.out.println("ϵͳ�ѳɹ����˳���");
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
