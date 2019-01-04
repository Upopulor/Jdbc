package com.wyc.ConnectPool;
//模拟数据库连接池，无实际开发意义
import java.sql.Connection;
import java.util.Collections;
import java.util.LinkedList;

import com.wyc.jdbc.demo.DBUtils;

public class SimpleConnnectPool {
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
	}//得到一个连接从连接池
	public Connection getConnectionFromPool() {
		if(pool.size()>0) {
			return pool.removeFirst();
		}else {
			//可以等一段时间，若超过就新创建一个pool,原来的用完后close
			throw new RuntimeException("服务器繁忙。。");
		}
	}
	//释放连接
	public void release(Connection conn) {
		pool.addLast(conn);
	}
}
