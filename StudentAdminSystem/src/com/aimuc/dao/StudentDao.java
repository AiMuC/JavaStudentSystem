package com.aimuc.dao;

import java.sql.Connection;

import com.aimuc.pojo.Student;
import com.mysql.jdbc.PreparedStatement;

public class StudentDao {
	public static void main(String[] args) {

	}

	// ѧ���޸���Ϣ
	public static String[] changeMyData(Student student) throws Exception {
		// ִ���޸Ĳ���
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
			String[] returnarr = { "success", "�޸ĳɹ�!" };
			return returnarr;
		} else {
			String[] returnarr = { "success", "�޸�ʧ��!" };
			return returnarr;
		}

	}
}
