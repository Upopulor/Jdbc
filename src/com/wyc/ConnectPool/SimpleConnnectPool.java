package com.wyc.ConnectPool;
//ģ�����ݿ����ӳأ���ʵ�ʿ�������
import java.sql.Connection;
import java.util.Collections;
import java.util.LinkedList;

import com.wyc.jdbc.demo.DBUtils;

public class SimpleConnnectPool {
	//����һ��������ӵĳ���
	private static LinkedList<Connection> pool = (LinkedList<Connection>) Collections.synchronizedList(new LinkedList<Connection>());
	static {
		try {
			for(int i = 0 ; i < 10 ; i++) {
				Connection conn = DBUtils.getConnection();
				pool.add(conn);
			}
		} catch (Exception e) {
			throw new ExceptionInInitializerError("��ʼ�����ݿ�����ʧ�ܣ����������ļ�");
		}
	}//�õ�һ�����Ӵ����ӳ�
	public Connection getConnectionFromPool() {
		if(pool.size()>0) {
			return pool.removeFirst();
		}else {
			//���Ե�һ��ʱ�䣬���������´���һ��pool,ԭ���������close
			throw new RuntimeException("��������æ����");
		}
	}
	//�ͷ�����
	public void release(Connection conn) {
		pool.addLast(conn);
	}
}
