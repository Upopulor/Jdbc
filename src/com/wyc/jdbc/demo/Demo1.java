package com.wyc.jdbc.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

//ʹ��JDBCʵ�ֲ�ѯ���ݿ����ݣ�����ʾ�ڿ���̨��
public class Demo1 {

	@Test
	public void Test1() throws Exception {
		//2 ��ȡ����Connection
		Connection connection = null;
		//3 �õ�ִ��SQL���Ķ���Statement
		Statement stmt = null;
		//4ִ��SQL��䣬�����ؽ��
		ResultSet rs = null;
		List<User> list = null;
		try {
			//1��������
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/day06", "root", "root");
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from users"); //�������������⣬��ô������ʧ�ܣ���������finally����
															//close�Ļ��Ǵ���ģ�����Ӧ���������һ���ж��Ƿ񴴽��ɹ�
			list = new ArrayList<User>();
			//5 ������
			//User u = new User(); ������������������ͬ�ĵ����е�ֵ
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
		for(User user: list) {
			System.out.println(user.toString());
		}
	}

}
