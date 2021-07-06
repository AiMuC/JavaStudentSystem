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

	// 获取用户信息 返回User对象 如为空值则不存在该学号
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

	// 获取学生信息 返回student对象 如为空值则不存在该学号
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

	// 编辑用户信息
	public static String[] editUserData(User user) throws Exception {
		// 验证学号与密码是否符合校验规则
		if (UserDao.checkInput(user)) {
			// 执行修改操作
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
				String[] returnarr = { "success", "修改成功!" };
				return returnarr;
			} else {
				String[] returnarr = { "success", "修改失败!" };
				return returnarr;
			}
		} else {
			String[] returnarr = { "error", "用户名或密码不符合校验规则,请重新输入!" };
			return returnarr;
		}
	}

	// 编辑学生信息
	public static String[] editStudentData(Student student) throws Exception {
		// 验证学号与密码是否符合校验规则
		if (UserDao.checkInput(student)) {
			// 执行修改操作
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
				String[] returnarr = { "success", "修改成功!" };
				return returnarr;
			} else {
				String[] returnarr = { "success", "修改失败!" };
				return returnarr;
			}
		} else {
			String[] returnarr = { "error", "学号或密码不符合校验规则,请重新输入!" };
			return returnarr;
		}
	}

	// 删除用户信息
	public static String[] deleteUserData(String Userid) throws Exception {
		Connection connection = DBHelper.connection();
		PreparedStatement statement = null;
		String sql = "DELETE FROM users WHERE username=?";
		statement = (PreparedStatement) connection.prepareStatement(sql);
		statement.setString(1, Userid);
		int rs = statement.executeUpdate();// execute方法返回印象的行数
		if (rs >= 1) {
			String[] returnarr = { "success", "删除成功!" };
			return returnarr;
		} else {
			String[] returnarr = { "error", "删除失败!" };
			return returnarr;
		}
	}

	// 删除学生信息
	public static String[] deleteStudentData(String Stuid) throws Exception {
		Connection connection = DBHelper.connection();
		PreparedStatement statement = null;
		String sql = "DELETE FROM students WHERE stuid=?";
		statement = (PreparedStatement) connection.prepareStatement(sql);
		statement.setString(1, Stuid);
		int rs = statement.executeUpdate();// execute方法返回印象的行数
		if (rs >= 1) {
			String[] returnarr = { "success", "删除成功!" };
			return returnarr;
		} else {
			String[] returnarr = { "error", "删除失败!" };
			return returnarr;
		}
	}

	// 获取全部用户信息
	public static Object[][] getAllUserData() throws Exception {
		Connection connection = DBHelper.connection();// 获取数据库链接
		PreparedStatement statement = null;
		String sql = "SELECT * FROM users ORDER BY userid";// 定义语句
		statement = (PreparedStatement) connection.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();
		int count = 0;
		while (rs.next()) {
			count++;// 获取返回了多少行
		}
		Object[][] info = new Object[count][4];// 创建一个对象
		rs = statement.executeQuery();// 重新执行语句
		count = 0;
		while (rs.next()) {
			info[count][0] = rs.getString("username");
			info[count][1] = rs.getString("password");
			if (rs.getInt("usertype") == 1)
				info[count][2] = "管理员";
			else
				info[count][2] = "教师";
			if (rs.getInt("l_ock") == 1)
				info[count][3] = "锁定";
			else
				info[count][3] = "正常";
			count++;
		}
		return info;// 返回内容
	}

	// 模糊搜索用户
	public static Object[][] getSearchUserData(String username) throws Exception {
		username = "%"+username+"%";
		Connection connection = DBHelper.connection();// 获取数据库链接
		PreparedStatement statement = null;
		String sql = "SELECT * FROM users WHERE username LIKE ?";// 定义语句
		statement = (PreparedStatement) connection.prepareStatement(sql);
		statement.setString(1, username);
		ResultSet rs = statement.executeQuery();
		int count = 0;
		while (rs.next()) {
			count++;// 获取返回了多少行
		}
		if(count==0)return null;
		Object[][] info = new Object[count][4];// 创建一个对象
		rs = statement.executeQuery();// 重新执行语句
		count = 0;
		while (rs.next()) {
			info[count][0] = rs.getString("username");
			info[count][1] = rs.getString("password");
			if (rs.getInt("usertype") == 1)
				info[count][2] = "管理员";
			else
				info[count][2] = "教师";
			if (rs.getInt("l_ock") == 1)
				info[count][3] = "锁定";
			else
				info[count][3] = "正常";
			count++;
		}
		return info;// 返回内容
	}
	
	// 模糊搜索学生用户名
	public static Object[][] getSearchStudentData(String studentid) throws Exception {
		studentid = "%"+studentid+"%";
		Connection connection = DBHelper.connection();// 获取数据库链接
		PreparedStatement statement = null;
		String sql = "SELECT * FROM students WHERE stuid LIKE ?";// 定义语句
		statement = (PreparedStatement) connection.prepareStatement(sql);
		statement.setString(1, studentid);
		ResultSet rs = statement.executeQuery();
		int count = 0;
		while (rs.next()) {
			count++;// 获取返回了多少行
		}
		if(count==0)return null;
		Object[][] info = new Object[count][5];// 创建一个对象
		rs = statement.executeQuery();// 重新执行语句
		count = 0;
		while (rs.next()) {
			info[count][0] = rs.getString("stuid");
			info[count][1] = rs.getString("stuname");
			info[count][2] = rs.getString("SID");
			info[count][3] = rs.getString("sex");
			info[count][4] = rs.getString("age");
			count++;
		}
		return info;// 返回内容
	}

	// 获取全部学生信息
	public static Object[][] getAllStudentData() throws Exception {
		Connection connection = DBHelper.connection();// 获取数据库链接
		PreparedStatement statement = null;
		String sql = "SELECT * FROM students ORDER BY stuid";// 定义语句
		statement = (PreparedStatement) connection.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();
		int count = 0;
		while (rs.next()) {
			count++;// 获取返回了多少行
		}
		Object[][] info = new Object[count][5];// 创建一个对象
		rs = statement.executeQuery();// 重新执行语句
		count = 0;
		while (rs.next()) {
			info[count][0] = rs.getString("stuid");
			info[count][1] = rs.getString("stuname");
			info[count][2] = rs.getString("SID");
			info[count][3] = rs.getString("sex");
			info[count][4] = rs.getString("age");
			count++;
		}
		return info;// 返回内容
	}
}
