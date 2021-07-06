package com.aimuc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.aimuc.pojo.Student;
import com.aimuc.pojo.User;
import com.mysql.jdbc.PreparedStatement;

public class UserDao {

	// 测试main方法
	public static void main(String[] args) throws Exception {

		// // 验证检查输入内容方法
		// User user = new User("abcdefg", "123456789@");
		// System.out.println(userReg(user));
		// // 验证检查输入内容方法
		// System.out.println(checkUserEmpty(user));
		// // 验证检查输入内容方法
		// System.out.println(checkInput(user));

		// Student student = new Student("2006119202", "1", "1", "1", "1", 18);
		// //验证验证数据库内是否存在当前学生
		// System.out.println(checkStudentEmpty(student));

		// Connection connection = DBHelper.connection();// 获取数据库连接
		// // DBHelper.closeDB(connection);//关闭数据库连接
		// Statement stmt = connection.createStatement();
		// ResultSet rs = stmt.executeQuery("select * from users");
		// while (rs.next()) {
		// System.out.println("用户编号:" + rs.getInt("userid") + " 用户名:" +
		// rs.getString("username") + " 用户类型:"
		// + rs.getInt("usertype"));
		// }
	}

	// 学生用户登入方法
	public static String[] studentLogin(Student student) throws SQLException {
		Connection connection = DBHelper.connection();
		PreparedStatement statement = null;
		String sql = "select * from students where stuid=?";
		statement = (PreparedStatement) connection.prepareStatement(sql);
		statement.setString(1, student.getStuid());
		ResultSet rs = statement.executeQuery();
		// 判断是否存在数据
		if (rs.next()) {
			// 判断用户输入的密码与数据库内储存的是否一致
			if (student.getPassword().equals(rs.getString("password"))) {
				String[] returnarr = { "success", "登入成功!", "-1" };
				return returnarr;
			} else {
				String[] returnarr = { "error", "用户密码错误!" };
				return returnarr;
			}
		} else {
			// 不存在即返回错误
			String[] returnarr = { "error", "不存在此学号!" };
			return returnarr;
		}
	}

	// 用户登入方法
	public static String[] userLogin(User user) throws SQLException {
		Connection connection = DBHelper.connection();
		PreparedStatement statement = null;
		String sql = "select * from users where username=?";
		statement = (PreparedStatement) connection.prepareStatement(sql);
		statement.setString(1, user.Getname());
		ResultSet rs = statement.executeQuery();
		// 判断是否存在数据
		if (rs.next()) {
			// 判断用户输入的密码与数据库内储存的是否一致
			if (user.Getpass().equals(rs.getString("password"))) {
				// 判断用户是否处于锁定状态 锁定状态则无法登入系统
				if (rs.getString("l_ock").equals("1")) {
					String[] returnarr = { "error", "登入失败,该账户尚未通过管理员审核!", rs.getString("usertype") };
					return returnarr;
				} else {
					String[] returnarr = { "success", "登入成功!", rs.getString("usertype") };
					return returnarr;
				}
			} else {
				String[] returnarr = { "error", "用户密码错误!" };
				return returnarr;
			}
		} else {
			String[] returnarr = { "error", "不存在此用户!" };
			return returnarr;
		}
	}

	// 用户注册方法
	public static String[] userReg(User user) throws SQLException {
		// 调用验证是否存在用户方法 为空则不存在 否则返回 ResultSet
		if (checkUserEmpty(user) == null) {
			// 验证用户输入的账户密码是否符合规则
			if (checkInput(user)) {
				Connection connection = DBHelper.connection();
				PreparedStatement statement = null;
				String sql = "INSERT INTO users (username,PASSWORD)VALUES(?,?)";
				statement = (PreparedStatement) connection.prepareStatement(sql);
				statement.setString(1, user.Getname());
				statement.setString(2, user.Getpass());
				int rs = statement.executeUpdate();// execute方法返回印象的行数
				// 判断返回行数为1则执行成功
				if (rs == 1) {
					String[] returnarr = { "success", "注册成功!请等待管理员审核通过后登入!" };
					return returnarr;
				} else {
					String[] returnarr = { "error", "注册失败" };
					return returnarr;
				}
			} else {
				String[] returnarr = { "error", "用户名或密码不符合规则 长度需要五位" };
				return returnarr;
			}
		} else {
			String[] returnarr = { "error", "用户名已存在,请更换用户名后重试!" };
			return returnarr;
		}
	}

	// 新增学生方法
	public static String[] addStudent(Student student) throws SQLException {
		// 调用验证学生状态方法获取学生数据
		ResultSet Studentdata = checkStudentEmpty(student);
		// 如果返回值为空则执行一下内容
		if (Studentdata == null) {
			// 验证学号与密码是否符合校验规则
			if (UserDao.checkInput(student)) {
				Connection connection = DBHelper.connection();
				PreparedStatement statement = null;
				String sql = "INSERT INTO students (stuid,password,SID,sex,stuname,age)VALUES(?,?,?,?,?,?)";
				statement = (PreparedStatement) connection.prepareStatement(sql);
				statement.setString(1, student.getStuid());
				statement.setString(2, student.getPassword());
				statement.setString(3, student.getSID());
				statement.setString(4, student.Getsex());
				statement.setString(5, student.Getname());
				statement.setInt(6, student.Getage());
				int rs = statement.executeUpdate();// execute方法返回印象的行数
				// 判断返回行数为1则执行成功
				if (rs == 1) {
					String[] returnarr = { "success", "学生添加成功" };
					return returnarr;
				} else {
					String[] returnarr = { "error", "添加失败" };
					return returnarr;
				}
			} else {
				String[] returnarr = { "error", "学号或密码不符合校验规则,请重新输入!" };
				return returnarr;
			}
		} else {
			String[] returnarr = { "error", "用户名已存在,请更换用户名后重试!" };
			return returnarr;
		}
	}

	// 新增用户方法
	public static String[] addUser(User user) throws SQLException {
		// 调用验证用户状态方法获取用户数据
		ResultSet Userdata = checkUserEmpty(user);
		// 如果返回值为空则执行一下内容
		if (Userdata == null) {
			if (checkInput(user)) {
				Connection connection = DBHelper.connection();
				PreparedStatement statement = null;
				String sql = "INSERT INTO users (username,password,usertype,l_ock)VALUES(?,?,?,0)";
				statement = (PreparedStatement) connection.prepareStatement(sql);
				statement.setString(1, user.Getname());
				statement.setString(2, user.Getpass());
				statement.setString(3, user.Getusertype());
				int rs = statement.executeUpdate();// execute方法返回印象的行数
				// 判断返回行数为1则执行成功
				if (rs == 1) {
					String[] returnarr = { "success", "添加成功!" };
					return returnarr;
				} else {
					String[] returnarr = { "error", "添加失败" };
					return returnarr;
				}
			} else {
				String[] returnarr = { "error", "账户或密码不符合规则！" };
				return returnarr;
			}
		} else {
			String[] returnarr = { "error", "用户名已存在,请更换用户名后重试!" };
			return returnarr;
		}
	}

	// 修改学生用户密码方法
	public static String[] resetPwdForStudent(Student student, String oldpwd) throws SQLException {
		// 调用验证用户状态方法获取用户数据
		ResultSet Studentdata = checkStudentEmpty(student);
		// 如果返回值为空则执行一下内容
		if (Studentdata != null) {
			// 校验输入的用户名以及密码是否合规
			if (checkInput(student)) {
				if (Studentdata.getString("password").equals(oldpwd)) {
					Connection connection = DBHelper.connection();
					PreparedStatement statement = null;
					String sql = "UPDATE students SET PASSWORD=? WHERE stuid=?";
					statement = (PreparedStatement) connection.prepareStatement(sql);
					statement.setString(1, student.getPassword());
					statement.setString(2, student.getStuid());
					int rs = statement.executeUpdate();// execute方法返回印象的行数
					// 判断返回行数为1则执行成功
					if (rs == 1) {
						String[] returnarr = { "success", "密码修改成功" };
						return returnarr;
					} else {
						String[] returnarr = { "error", "修改失败" };
						return returnarr;
					}
				} else {
					String[] returnarr = { "error", "旧密码不符！" };
					return returnarr;
				}
			} else {
				String[] returnarr = { "error", "密码规则不符合！需要大于五位" };
				return returnarr;
			}
		} else {
			String[] returnarr = { "error", "未知错误!" };
			return returnarr;
		}
	}

	// 修改用户密码方法
	public static String[] resetPwdForUser(User user, String oldpwd) throws SQLException {
		// 调用验证用户状态方法获取用户数据
		ResultSet Userdata = checkUserEmpty(user);
		// 如果返回值为空则执行一下内容
		if (Userdata != null) {
			// 校验输入的用户名以及密码是否合规
			if (checkInput(user)) {
				// 判断输入密码是否与数据库内密码相同
				if (Userdata.getString("password").equals(oldpwd)) {
					Connection connection = DBHelper.connection();
					PreparedStatement statement = null;
					String sql = "UPDATE users SET PASSWORD=? WHERE username=?";
					statement = (PreparedStatement) connection.prepareStatement(sql);
					statement.setString(1, user.Getpass());
					statement.setString(2, user.Getname());
					int rs = statement.executeUpdate();// execute方法返回印象的行数
					// 判断返回行数为1则执行成功
					if (rs == 1) {
						String[] returnarr = { "success", "密码修改成功" };
						return returnarr;
					} else {
						String[] returnarr = { "error", "修改失败" };
						return returnarr;
					}
				} else {
					String[] returnarr = { "error", "旧密码不符！" };
					return returnarr;
				}
			} else {
				String[] returnarr = { "error", "账户或密码不符合规则！" };
				return returnarr;
			}
		} else {
			String[] returnarr = { "error", "未知错误!" };
			return returnarr;
		}
	}

	// 学生用户密码找回
	public static String[] resetPassword(Student student) throws SQLException {
		// 调用验证学生状态方法获取学生数据
		ResultSet Studentdata = checkStudentEmpty(student);
		// 如果返回值为空则执行一下内容
		if (Studentdata != null) {
			// 校验输入的用户名以及密码是否合规
			if (checkInput(student)) {
				// 截取数据库内身份证号码后六位与用户输入的内容对比 一致则继续执行
				if (Studentdata.getString("SID").substring(Studentdata.getString("SID").length() - 6)
						.equals(student.getSID())) {
					Connection connection = DBHelper.connection();
					PreparedStatement statement = null;
					String sql = "UPDATE students SET PASSWORD=? WHERE stuid=?";
					statement = (PreparedStatement) connection.prepareStatement(sql);
					statement.setString(1, student.getPassword());
					statement.setString(2, student.getStuid());
					int rs = statement.executeUpdate();
					if (rs == 1) {
						String[] returnarr = { "success", "密码修改成功" };
						return returnarr;
					} else {
						String[] returnarr = { "error", "修改失败" };
						return returnarr;
					}
				} else {
					String[] returnarr = { "error", "身份证后六位校验错误" };
					return returnarr;
				}
			} else {
				String[] returnarr = { "error", "账户或密码不符合规则！" };
				return returnarr;
			}
		} else {
			String[] returnarr = { "error", "不存在此学号请重试!" };
			return returnarr;
		}
	}

	// 校验Student对象用户名以及密码是否符合规则 需要大于5个子字符串 并且符合规则
	public static boolean checkInput(Student student) {
		String regex = "^\\w+$";
		if (student.getStuid().length() < 5 || student.getPassword().length() < 5) {
			return false;
		} else if (student.getStuid().matches(regex) || student.getPassword().matches(regex)) {
			return true;
		} else {
			return false;
		}
	}

	// 校验user对象用户名以及密码是否符合规则 需要大于5个子字符串 并且符合规则
	public static boolean checkInput(User user) {
		String regex = "^\\w+$";
		if (user.Getname().length() < 5 || user.Getpass().length() < 5) {
			return false;
		} else if (user.Getname().matches(regex) || user.Getpass().matches(regex)) {
			return true;
		} else {
			return false;
		}
	}

	// 验证数据库内是否存在当前学生 如果存在则返回ResultSet否则返回空值
	public static ResultSet checkStudentEmpty(Student student) throws SQLException {
		Connection connection = DBHelper.connection();
		PreparedStatement statement = null;
		String sql = "select * from students where stuid=?";
		statement = (PreparedStatement) connection.prepareStatement(sql);
		statement.setString(1, student.getStuid());
		ResultSet rs = statement.executeQuery();
		// 判断是否存在数据
		if (!rs.next()) {
			return null;
		} else {
			// 返回获取到的数据
			return rs;
		}
	}

	// 验证数据库内是否存在当前用户 如果存在则返回ResultSet否则返回空值
	public static ResultSet checkUserEmpty(User user) throws SQLException {
		Connection connection = DBHelper.connection();
		PreparedStatement statement = null;
		String sql = "select * from users where username=?";
		statement = (PreparedStatement) connection.prepareStatement(sql);
		statement.setString(1, user.Getname());
		ResultSet rs = statement.executeQuery();
		// 判断是否存在数据
		if (!rs.next())
			return null;
		else
			// 返回获取到的数据
			return rs;

	}

}
