package com.aimuc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBHelper {
	// 设置JDBC驱动
	private static final String JDBC_DIR = "com.mysql.jdbc.Driver";
	// 设置数据库地址
	private static final String DB_URL = "jdbc:mysql://localhost:3306/studentdb?userssl=true";
	// 设置数据库用户名
	private static final String USER = "root";
	private static final String PWD = "123456";

	// 连接数据库
	public static Connection connection() {
		try {
			Class.forName(JDBC_DIR);
			try {
				// System.out.println("数据库连接成功");
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

	// 关闭数据库连接
	public static void closeDB(Connection connection) {
		if (connection != null) {
			try {
				// System.out.println("已关闭数据库连接");
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			connection = null;
		}
	}

}
