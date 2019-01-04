package com.wyc.jdbc.demo;

import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入用户名：");
		String name = scanner.nextLine();
		System.out.println("请输入密码：");
		String password = scanner.nextLine();
		DoLogin dl = new DoLogin();
		User user = dl.findUser(name, password);
		if(user!=null) {
			System.out.println("欢迎-"+user.getName());
		}else {
			System.out.println("用户或密码错误");
		}
		scanner.close();
	}

}
