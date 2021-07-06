package com.aimuc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import com.aimuc.pojo.Student;
import com.aimuc.pojo.User;
import com.mysql.jdbc.PreparedStatement;

public class AdminDao {

	public static void main(String[] args) throws Exception {
		// getAllStudentData();
		// getStudentData("2006119202");
		// getUserData("aimuc");
//		getSearchData("2006119202");
	}

	// ��ȡ�û���Ϣ ����User���� ��Ϊ��ֵ�򲻴��ڸ�ѧ��
	public static User getUserData(String username) throws Exception {
		Connection connection = DBHelper.connection();
		PreparedStatement statement = null;
		String sql = "SELECT * FROM users WHERE username=?";
		statement = (PreparedStatement) connection.prepareStatement(sql);
		statement.setString(1, username);
		ResultSet rSet = statement.executeQuery();
		if (rSet.next()) {
			User user = new User(rSet.getString("username"), rSet.getString("password"), rSet.getString("usertype"),
					rSet.getString("l_ock"));
			return user;
		} else {
			return null;
		}
	}

	// ��ȡѧ����Ϣ ����student���� ��Ϊ��ֵ�򲻴��ڸ�ѧ��
	public static Student getStudentData(String Stuid) throws Exception {
		Connection connection = DBHelper.connection();
		PreparedStatement statement = null;
		String sql = "SELECT * FROM students WHERE stuid=?";
		statement = (PreparedStatement) connection.prepareStatement(sql);
		statement.setString(1, Stuid);
		ResultSet rSet = statement.executeQuery();
		if (rSet.next()) {
			Student student = new Student(rSet.getString("stuid"), rSet.getString("SID"), rSet.getString("password"),
					rSet.getString("sex"), rSet.getString("stuname"), rSet.getInt("age"));
			return student;
		} else {
			return null;
		}
	}

	// �༭�û���Ϣ
	public static String[] editUserData(User user) throws Exception {
		// ��֤ѧ���������Ƿ����У�����
		if (UserDao.checkInput(user)) {
			// ִ���޸Ĳ���
			Connection connection = DBHelper.connection();
			PreparedStatement statement = null;
			String sql = "UPDATE users SET PASSWORD=?,usertype=?,l_ock=? WHERE username=?";
			statement = (PreparedStatement) connection.prepareStatement(sql);
			statement.setString(1, user.Getpass());
			statement.setString(2, user.Getusertype());
			statement.setString(3, user.Getuserstate());
			statement.setString(4, user.Getname());
			int rs = statement.executeUpdate();
			if (rs >= 1) {
				String[] returnarr = { "success", "�޸ĳɹ�!" };
				return returnarr;
			} else {
				String[] returnarr = { "success", "�޸�ʧ��!" };
				return returnarr;
			}
		} else {
			String[] returnarr = { "error", "�û��������벻����У�����,����������!" };
			return returnarr;
		}
	}

	// �༭ѧ����Ϣ
	public static String[] editStudentData(Student student) throws Exception {
		// ��֤ѧ���������Ƿ����У�����
		if (UserDao.checkInput(student)) {
			// ִ���޸Ĳ���
			Connection connection = DBHelper.connection();
			PreparedStatement statement = null;
			String sql = "UPDATE students SET stuid=?,PASSWORD=?,SID=?,sex=?,stuname=?,age=? WHERE stuid=?";
			statement = (PreparedStatement) connection.prepareStatement(sql);
			statement.setString(1, student.getStuid());
			statement.setString(2, student.getPassword());
			statement.setString(3, student.getSID());
			statement.setString(4, student.Getsex());
			statement.setString(5, student.Getname());
			statement.setInt(6, student.Getage());
			statement.setString(7, student.getStuid());
			int rs = statement.executeUpdate();
			if (rs >= 1) {
				String[] returnarr = { "success", "�޸ĳɹ�!" };
				return returnarr;
			} else {
				String[] returnarr = { "success", "�޸�ʧ��!" };
				return returnarr;
			}
		} else {
			String[] returnarr = { "error", "ѧ�Ż����벻����У�����,����������!" };
			return returnarr;
		}
	}

	// ɾ���û���Ϣ
	public static String[] deleteUserData(String Userid) throws Exception {
		Connection connection = DBHelper.connection();
		PreparedStatement statement = null;
		String sql = "DELETE FROM users WHERE username=?";
		statement = (PreparedStatement) connection.prepareStatement(sql);
		statement.setString(1, Userid);
		int rs = statement.executeUpdate();// execute��������ӡ�������
		if (rs >= 1) {
			String[] returnarr = { "success", "ɾ���ɹ�!" };
			return returnarr;
		} else {
			String[] returnarr = { "error", "ɾ��ʧ��!" };
			return returnarr;
		}
	}

	// ɾ��ѧ����Ϣ
	public static String[] deleteStudentData(String Stuid) throws Exception {
		Connection connection = DBHelper.connection();
		PreparedStatement statement = null;
		String sql = "DELETE FROM students WHERE stuid=?";
		statement = (PreparedStatement) connection.prepareStatement(sql);
		statement.setString(1, Stuid);
		int rs = statement.executeUpdate();// execute��������ӡ�������
		if (rs >= 1) {
			String[] returnarr = { "success", "ɾ���ɹ�!" };
			return returnarr;
		} else {
			String[] returnarr = { "error", "ɾ��ʧ��!" };
			return returnarr;
		}
	}

	// ��ȡȫ���û���Ϣ
	public static Object[][] getAllUserData() throws Exception {
		Connection connection = DBHelper.connection();// ��ȡ���ݿ�����
		PreparedStatement statement = null;
		String sql = "SELECT * FROM users ORDER BY userid";// �������
		statement = (PreparedStatement) connection.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();
		int count = 0;
		while (rs.next()) {
			count++;// ��ȡ�����˶�����
		}
		Object[][] info = new Object[count][4];// ����һ������
		rs = statement.executeQuery();// ����ִ�����
		count = 0;
		while (rs.next()) {
			info[count][0] = rs.getString("username");
			info[count][1] = rs.getString("password");
			if (rs.getInt("usertype") == 1)
				info[count][2] = "����Ա";
			else
				info[count][2] = "��ʦ";
			if (rs.getInt("l_ock") == 1)
				info[count][3] = "����";
			else
				info[count][3] = "����";
			count++;
		}
		return info;// ��������
	}

	// ģ�������û�
	public static Object[][] getSearchUserData(String username) throws Exception {
		username = "%"+username+"%";
		Connection connection = DBHelper.connection();// ��ȡ���ݿ�����
		PreparedStatement statement = null;
		String sql = "SELECT * FROM users WHERE username LIKE ?";// �������
		statement = (PreparedStatement) connection.prepareStatement(sql);
		statement.setString(1, username);
		ResultSet rs = statement.executeQuery();
		int count = 0;
		while (rs.next()) {
			count++;// ��ȡ�����˶�����
		}
		if(count==0)return null;
		Object[][] info = new Object[count][4];// ����һ������
		rs = statement.executeQuery();// ����ִ�����
		count = 0;
		while (rs.next()) {
			info[count][0] = rs.getString("username");
			info[count][1] = rs.getString("password");
			if (rs.getInt("usertype") == 1)
				info[count][2] = "����Ա";
			else
				info[count][2] = "��ʦ";
			if (rs.getInt("l_ock") == 1)
				info[count][3] = "����";
			else
				info[count][3] = "����";
			count++;
		}
		return info;// ��������
	}
	
	// ģ������ѧ���û���
	public static Object[][] getSearchStudentData(String studentid) throws Exception {
		studentid = "%"+studentid+"%";
		Connection connection = DBHelper.connection();// ��ȡ���ݿ�����
		PreparedStatement statement = null;
		String sql = "SELECT * FROM students WHERE stuid LIKE ?";// �������
		statement = (PreparedStatement) connection.prepareStatement(sql);
		statement.setString(1, studentid);
		ResultSet rs = statement.executeQuery();
		int count = 0;
		while (rs.next()) {
			count++;// ��ȡ�����˶�����
		}
		if(count==0)return null;
		Object[][] info = new Object[count][5];// ����һ������
		rs = statement.executeQuery();// ����ִ�����
		count = 0;
		while (rs.next()) {
			info[count][0] = rs.getString("stuid");
			info[count][1] = rs.getString("stuname");
			info[count][2] = rs.getString("SID");
			info[count][3] = rs.getString("sex");
			info[count][4] = rs.getString("age");
			count++;
		}
		return info;// ��������
	}

	// ��ȡȫ��ѧ����Ϣ
	public static Object[][] getAllStudentData() throws Exception {
		Connection connection = DBHelper.connection();// ��ȡ���ݿ�����
		PreparedStatement statement = null;
		String sql = "SELECT * FROM students ORDER BY stuid";// �������
		statement = (PreparedStatement) connection.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();
		int count = 0;
		while (rs.next()) {
			count++;// ��ȡ�����˶�����
		}
		Object[][] info = new Object[count][5];// ����һ������
		rs = statement.executeQuery();// ����ִ�����
		count = 0;
		while (rs.next()) {
			info[count][0] = rs.getString("stuid");
			info[count][1] = rs.getString("stuname");
			info[count][2] = rs.getString("SID");
			info[count][3] = rs.getString("sex");
			info[count][4] = rs.getString("age");
			count++;
		}
		return info;// ��������
	}
}
