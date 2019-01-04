package com.wyc.ConnectPool;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.logging.Logger;

import javax.sql.DataSource;

import com.wyc.jdbc.demo.DBUtils;

public class MyDataSource implements DataSource{
	//创建一个存放连接的池子
	private static LinkedList<Connection> pool = (LinkedList<Connection>) Collections.synchronizedList(new LinkedList<Connection>());
	static {
		try {
			for(int i = 0 ; i < 10 ; i++) {
				Connection conn = DBUtils.getConnection();
				pool.add(conn);
			}
		} catch (Exception e) {
			throw new ExceptionInInitializerError("初始化数据库连接失败，请检查配置文件");
		}
	}
	
	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Connection getConnection() throws SQLException {
		if(pool.size()>0) {
			return pool.removeFirst();
		}else {
			//可以等一段时间，若超过就新创建一个pool,原来的用完后close
			throw new RuntimeException("服务器繁忙。。");
		}
	}
	@Override
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
}
