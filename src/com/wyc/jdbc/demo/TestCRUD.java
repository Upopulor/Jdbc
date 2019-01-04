package com.wyc.jdbc.demo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestCRUD {
	@Test
	public void testSelect() {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			connection = DBUtils.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from users");
			List<User> list = new ArrayList<User>();
			while(rs.next()) {
				User u = new User();
				u.setId(rs.getInt(1));
				u.setName(rs.getString(2));
				u.setPassword(rs.getString(3));
				u.setEmail(rs.getString(4));
				u.setBirthday(rs.getDate(5));
				list.add(u);
			}
			for(User user : list) {
				System.out.println(user);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.closeAll(rs, stmt, connection);
		}
	}
	@Test
	public void testInsert() {
		Connection connection = null;
		Statement stmt = null;
		try {
			connection = DBUtils.getConnection();
			stmt = connection.createStatement();
			int i = stmt.executeUpdate("INSERT INTO users VALUES(4,'tom','123','ss@com','2019-08-23')");
			if(i > 0) {
				System.out.println("success");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.closeAll(null, stmt, connection);
		}
	}
	@Test
	public void testUpdate() {
		Connection connection = null;
		Statement stmt = null;
		try {
			connection = DBUtils.getConnection();
			stmt = connection.createStatement();
			int i = stmt.executeUpdate("UPDATE users SET NAME='jerrs',PASSWORD='333',email='jeee@com' WHERE id = 4");
			if(i > 0) {
				System.out.println("success");
			}else{
				System.out.println("未找到用户");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.closeAll(null, stmt, connection);
		}
	}
	@Test
	public void testDelete() {
		Connection connection = null;
		Statement stmt = null;
		try {
			connection = DBUtils.getConnection();
			stmt = connection.createStatement();
			int i = stmt.executeUpdate("DELETE FROM users WHERE id = 4");
			if(i > 0) {
				System.out.println("success");
			}else{
				System.out.println("未找到用户");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.closeAll(null, stmt, connection);
		}
	}
}
