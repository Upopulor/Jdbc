package com.wyc.jdbc.demo;

import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("�������û�����");
		String name = scanner.nextLine();
		System.out.println("���������룺");
		String password = scanner.nextLine();
		DoLogin dl = new DoLogin();
		User user = dl.findUser(name, password);
		if(user!=null) {
			System.out.println("��ӭ-"+user.getName());
		}else {
			System.out.println("�û����������");
		}
		scanner.close();
	}

}
