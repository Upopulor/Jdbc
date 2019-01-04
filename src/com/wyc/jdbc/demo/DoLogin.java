package com.wyc.jdbc.demo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DoLogin {
	//根据用户名和密码查询用户信息
	public User findUser(String name,String password) {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		User u = null;
		try {
			connection = DBUtils.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from users where name='"+name+"' and password='"+password+"'");
			if(rs.next()) {
				u = new User();
				u.setId(rs.getInt(1));
				u.setName(rs.getString(2));
				u.setPassword(rs.getString(3));
				u.setEmail(rs.getString(4));
				u.setBirthday(rs.getDate(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.closeAll(rs, stmt, connection);
		}
		return u;
	}

}
