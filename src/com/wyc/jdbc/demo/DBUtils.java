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
	static { //需要一开始就加载所以写在静态块中
		//此对象是用于加载properties文件数据的
		ResourceBundle rb = ResourceBundle.getBundle("DBinfo");//国际化，读取配置
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
	//得到连接的方法
	public static Connection getConnection() throws Exception {
		return DriverManager.getConnection(url, username, password);
	}
	//关闭资源的方法
	public static void closeAll(ResultSet rs,Statement stmt,Connection connection) {
		//6 关闭资源
		if(rs!=null) {
			try { //里面了可能还有异常，所以加trycatch
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
