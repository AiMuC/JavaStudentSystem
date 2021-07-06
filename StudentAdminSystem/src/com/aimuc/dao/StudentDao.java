package com.aimuc.dao;

import java.sql.Connection;

import com.aimuc.pojo.Student;
import com.mysql.jdbc.PreparedStatement;

public class StudentDao {
	public static void main(String[] args) {

	}

	// 学生修改信息
	public static String[] changeMyData(Student student) throws Exception {
		// 执行修改操作
		Connection connection = DBHelper.connection();
		PreparedStatement statement = null;
		String sql = "UPDATE students SET SID=?,sex=?,stuname=?,age=? WHERE stuid=?";
		statement = (PreparedStatement) connection.prepareStatement(sql);
		statement.setString(1, student.getSID());
		statement.setString(2, student.Getsex());
		statement.setString(3, student.Getname());
		statement.setInt(4, student.Getage());
		statement.setString(5, student.getStuid());
		int rs = statement.executeUpdate();
		if (rs >= 1) {
			String[] returnarr = { "success", "修改成功!" };
			return returnarr;
		} else {
			String[] returnarr = { "success", "修改失败!" };
			return returnarr;
		}

	}
}
