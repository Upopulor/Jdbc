package com.wyc.jdbc.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

//使用JDBC实现查询数据库数据，并显示在控制台中
public class Demo1 {

	@Test
	public void Test1() throws Exception {
		//2 获取连接Connection
		Connection connection = null;
		//3 得到执行SQL语句的对象Statement
		Statement stmt = null;
		//4执行SQL语句，并返回结果
		ResultSet rs = null;
		List<User> list = null;
		try {
			//1加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/day06", "root", "root");
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from users"); //如果这里出了问题，那么将创建失败，所以下面finally里面
															//close的话是错误的，所以应该在下面加一个判断是否创建成功
			list = new ArrayList<User>();
			//5 处理结果
			//User u = new User(); 如果放这里，会有三个相同的第三行的值
			while(rs.next()) {
				User u = new User();
				u.setId(rs.getInt("id"));
				u.setName(rs.getString("name"));
				u.setPassword(rs.getString("password"));
				u.setEmail(rs.getString("email"));
				u.setBirthday(rs.getDate("birthday"));
				list.add(u);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
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
		for(User user: list) {
			System.out.println(user.toString());
		}
	}

}
