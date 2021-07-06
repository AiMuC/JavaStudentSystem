package com.aimuc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBHelper {
	// ����JDBC����
	private static final String JDBC_DIR = "com.mysql.jdbc.Driver";
	// �������ݿ��ַ
	private static final String DB_URL = "jdbc:mysql://localhost:3306/studentdb?userssl=true";
	// �������ݿ��û���
	private static final String USER = "root";
	private static final String PWD = "123456";

	// �������ݿ�
	public static Connection connection() {
		try {
			Class.forName(JDBC_DIR);
			try {
				// System.out.println("���ݿ����ӳɹ�");
				return DriverManager.getConnection(DB_URL, USER, PWD);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	// �ر����ݿ�����
	public static void closeDB(Connection connection) {
		if (connection != null) {
			try {
				// System.out.println("�ѹر����ݿ�����");
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			connection = null;
		}
	}

}
