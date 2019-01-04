package com.wyc.jdbc.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class DBUtils {
	private static String driverClass;
	private static String url;
	private static String username;
	private static String password;
	static { //��Ҫһ��ʼ�ͼ�������д�ھ�̬����
		//�˶��������ڼ���properties�ļ����ݵ�
		ResourceBundle rb = ResourceBundle.getBundle("DBinfo");//���ʻ�����ȡ����
		driverClass = rb.getString("driverClass");
		url = rb.getString("url");
		username = rb.getString("username");
		password = rb.getString("password");
		try {
			Class.forName(driverClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	//�õ����ӵķ���
	public static Connection getConnection() throws Exception {
		return DriverManager.getConnection(url, username, password);
	}
	//�ر���Դ�ķ���
	public static void closeAll(ResultSet rs,Statement stmt,Connection connection) {
		//6 �ر���Դ
		if(rs!=null) {
			try { //�����˿��ܻ����쳣�����Լ�trycatch
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			rs=null;
		}
		if(stmt!=null) {
			try {
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			stmt = null;
		}
		if(connection!=null) {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			connection = null;
		}
	}
}
