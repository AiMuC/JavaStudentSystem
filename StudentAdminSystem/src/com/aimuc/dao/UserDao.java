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

	// ����main����
	public static void main(String[] args) throws Exception {

		// // ��֤����������ݷ���
		// User user = new User("abcdefg", "123456789@");
		// System.out.println(userReg(user));
		// // ��֤����������ݷ���
		// System.out.println(checkUserEmpty(user));
		// // ��֤����������ݷ���
		// System.out.println(checkInput(user));

		// Student student = new Student("2006119202", "1", "1", "1", "1", 18);
		// //��֤��֤���ݿ����Ƿ���ڵ�ǰѧ��
		// System.out.println(checkStudentEmpty(student));

		// Connection connection = DBHelper.connection();// ��ȡ���ݿ�����
		// // DBHelper.closeDB(connection);//�ر����ݿ�����
		// Statement stmt = connection.createStatement();
		// ResultSet rs = stmt.executeQuery("select * from users");
		// while (rs.next()) {
		// System.out.println("�û����:" + rs.getInt("userid") + " �û���:" +
		// rs.getString("username") + " �û�����:"
		// + rs.getInt("usertype"));
		// }
	}

	// ѧ���û����뷽��
	public static String[] studentLogin(Student student) throws SQLException {
		Connection connection = DBHelper.connection();
		PreparedStatement statement = null;
		String sql = "select * from students where stuid=?";
		statement = (PreparedStatement) connection.prepareStatement(sql);
		statement.setString(1, student.getStuid());
		ResultSet rs = statement.executeQuery();
		// �ж��Ƿ��������
		if (rs.next()) {
			// �ж��û���������������ݿ��ڴ�����Ƿ�һ��
			if (student.getPassword().equals(rs.getString("password"))) {
				String[] returnarr = { "success", "����ɹ�!", "-1" };
				return returnarr;
			} else {
				String[] returnarr = { "error", "�û��������!" };
				return returnarr;
			}
		} else {
			// �����ڼ����ش���
			String[] returnarr = { "error", "�����ڴ�ѧ��!" };
			return returnarr;
		}
	}

	// �û����뷽��
	public static String[] userLogin(User user) throws SQLException {
		Connection connection = DBHelper.connection();
		PreparedStatement statement = null;
		String sql = "select * from users where username=?";
		statement = (PreparedStatement) connection.prepareStatement(sql);
		statement.setString(1, user.Getname());
		ResultSet rs = statement.executeQuery();
		// �ж��Ƿ��������
		if (rs.next()) {
			// �ж��û���������������ݿ��ڴ�����Ƿ�һ��
			if (user.Getpass().equals(rs.getString("password"))) {
				// �ж��û��Ƿ�������״̬ ����״̬���޷�����ϵͳ
				if (rs.getString("l_ock").equals("1")) {
					String[] returnarr = { "error", "����ʧ��,���˻���δͨ������Ա���!", rs.getString("usertype") };
					return returnarr;
				} else {
					String[] returnarr = { "success", "����ɹ�!", rs.getString("usertype") };
					return returnarr;
				}
			} else {
				String[] returnarr = { "error", "�û��������!" };
				return returnarr;
			}
		} else {
			String[] returnarr = { "error", "�����ڴ��û�!" };
			return returnarr;
		}
	}

	// �û�ע�᷽��
	public static String[] userReg(User user) throws SQLException {
		// ������֤�Ƿ�����û����� Ϊ���򲻴��� ���򷵻� ResultSet
		if (checkUserEmpty(user) == null) {
			// ��֤�û�������˻������Ƿ���Ϲ���
			if (checkInput(user)) {
				Connection connection = DBHelper.connection();
				PreparedStatement statement = null;
				String sql = "INSERT INTO users (username,PASSWORD)VALUES(?,?)";
				statement = (PreparedStatement) connection.prepareStatement(sql);
				statement.setString(1, user.Getname());
				statement.setString(2, user.Getpass());
				int rs = statement.executeUpdate();// execute��������ӡ�������
				// �жϷ�������Ϊ1��ִ�гɹ�
				if (rs == 1) {
					String[] returnarr = { "success", "ע��ɹ�!��ȴ�����Ա���ͨ�������!" };
					return returnarr;
				} else {
					String[] returnarr = { "error", "ע��ʧ��" };
					return returnarr;
				}
			} else {
				String[] returnarr = { "error", "�û��������벻���Ϲ��� ������Ҫ��λ" };
				return returnarr;
			}
		} else {
			String[] returnarr = { "error", "�û����Ѵ���,������û���������!" };
			return returnarr;
		}
	}

	// ����ѧ������
	public static String[] addStudent(Student student) throws SQLException {
		// ������֤ѧ��״̬������ȡѧ������
		ResultSet Studentdata = checkStudentEmpty(student);
		// �������ֵΪ����ִ��һ������
		if (Studentdata == null) {
			// ��֤ѧ���������Ƿ����У�����
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
				int rs = statement.executeUpdate();// execute��������ӡ�������
				// �жϷ�������Ϊ1��ִ�гɹ�
				if (rs == 1) {
					String[] returnarr = { "success", "ѧ����ӳɹ�" };
					return returnarr;
				} else {
					String[] returnarr = { "error", "���ʧ��" };
					return returnarr;
				}
			} else {
				String[] returnarr = { "error", "ѧ�Ż����벻����У�����,����������!" };
				return returnarr;
			}
		} else {
			String[] returnarr = { "error", "�û����Ѵ���,������û���������!" };
			return returnarr;
		}
	}

	// �����û�����
	public static String[] addUser(User user) throws SQLException {
		// ������֤�û�״̬������ȡ�û�����
		ResultSet Userdata = checkUserEmpty(user);
		// �������ֵΪ����ִ��һ������
		if (Userdata == null) {
			if (checkInput(user)) {
				Connection connection = DBHelper.connection();
				PreparedStatement statement = null;
				String sql = "INSERT INTO users (username,password,usertype,l_ock)VALUES(?,?,?,0)";
				statement = (PreparedStatement) connection.prepareStatement(sql);
				statement.setString(1, user.Getname());
				statement.setString(2, user.Getpass());
				statement.setString(3, user.Getusertype());
				int rs = statement.executeUpdate();// execute��������ӡ�������
				// �жϷ�������Ϊ1��ִ�гɹ�
				if (rs == 1) {
					String[] returnarr = { "success", "��ӳɹ�!" };
					return returnarr;
				} else {
					String[] returnarr = { "error", "���ʧ��" };
					return returnarr;
				}
			} else {
				String[] returnarr = { "error", "�˻������벻���Ϲ���" };
				return returnarr;
			}
		} else {
			String[] returnarr = { "error", "�û����Ѵ���,������û���������!" };
			return returnarr;
		}
	}

	// �޸�ѧ���û����뷽��
	public static String[] resetPwdForStudent(Student student, String oldpwd) throws SQLException {
		// ������֤�û�״̬������ȡ�û�����
		ResultSet Studentdata = checkStudentEmpty(student);
		// �������ֵΪ����ִ��һ������
		if (Studentdata != null) {
			// У��������û����Լ������Ƿ�Ϲ�
			if (checkInput(student)) {
				if (Studentdata.getString("password").equals(oldpwd)) {
					Connection connection = DBHelper.connection();
					PreparedStatement statement = null;
					String sql = "UPDATE students SET PASSWORD=? WHERE stuid=?";
					statement = (PreparedStatement) connection.prepareStatement(sql);
					statement.setString(1, student.getPassword());
					statement.setString(2, student.getStuid());
					int rs = statement.executeUpdate();// execute��������ӡ�������
					// �жϷ�������Ϊ1��ִ�гɹ�
					if (rs == 1) {
						String[] returnarr = { "success", "�����޸ĳɹ�" };
						return returnarr;
					} else {
						String[] returnarr = { "error", "�޸�ʧ��" };
						return returnarr;
					}
				} else {
					String[] returnarr = { "error", "�����벻����" };
					return returnarr;
				}
			} else {
				String[] returnarr = { "error", "������򲻷��ϣ���Ҫ������λ" };
				return returnarr;
			}
		} else {
			String[] returnarr = { "error", "δ֪����!" };
			return returnarr;
		}
	}

	// �޸��û����뷽��
	public static String[] resetPwdForUser(User user, String oldpwd) throws SQLException {
		// ������֤�û�״̬������ȡ�û�����
		ResultSet Userdata = checkUserEmpty(user);
		// �������ֵΪ����ִ��һ������
		if (Userdata != null) {
			// У��������û����Լ������Ƿ�Ϲ�
			if (checkInput(user)) {
				// �ж����������Ƿ������ݿ���������ͬ
				if (Userdata.getString("password").equals(oldpwd)) {
					Connection connection = DBHelper.connection();
					PreparedStatement statement = null;
					String sql = "UPDATE users SET PASSWORD=? WHERE username=?";
					statement = (PreparedStatement) connection.prepareStatement(sql);
					statement.setString(1, user.Getpass());
					statement.setString(2, user.Getname());
					int rs = statement.executeUpdate();// execute��������ӡ�������
					// �жϷ�������Ϊ1��ִ�гɹ�
					if (rs == 1) {
						String[] returnarr = { "success", "�����޸ĳɹ�" };
						return returnarr;
					} else {
						String[] returnarr = { "error", "�޸�ʧ��" };
						return returnarr;
					}
				} else {
					String[] returnarr = { "error", "�����벻����" };
					return returnarr;
				}
			} else {
				String[] returnarr = { "error", "�˻������벻���Ϲ���" };
				return returnarr;
			}
		} else {
			String[] returnarr = { "error", "δ֪����!" };
			return returnarr;
		}
	}

	// ѧ���û������һ�
	public static String[] resetPassword(Student student) throws SQLException {
		// ������֤ѧ��״̬������ȡѧ������
		ResultSet Studentdata = checkStudentEmpty(student);
		// �������ֵΪ����ִ��һ������
		if (Studentdata != null) {
			// У��������û����Լ������Ƿ�Ϲ�
			if (checkInput(student)) {
				// ��ȡ���ݿ������֤�������λ���û���������ݶԱ� һ�������ִ��
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
						String[] returnarr = { "success", "�����޸ĳɹ�" };
						return returnarr;
					} else {
						String[] returnarr = { "error", "�޸�ʧ��" };
						return returnarr;
					}
				} else {
					String[] returnarr = { "error", "���֤����λУ�����" };
					return returnarr;
				}
			} else {
				String[] returnarr = { "error", "�˻������벻���Ϲ���" };
				return returnarr;
			}
		} else {
			String[] returnarr = { "error", "�����ڴ�ѧ��������!" };
			return returnarr;
		}
	}

	// У��Student�����û����Լ������Ƿ���Ϲ��� ��Ҫ����5�����ַ��� ���ҷ��Ϲ���
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

	// У��user�����û����Լ������Ƿ���Ϲ��� ��Ҫ����5�����ַ��� ���ҷ��Ϲ���
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

	// ��֤���ݿ����Ƿ���ڵ�ǰѧ�� ��������򷵻�ResultSet���򷵻ؿ�ֵ
	public static ResultSet checkStudentEmpty(Student student) throws SQLException {
		Connection connection = DBHelper.connection();
		PreparedStatement statement = null;
		String sql = "select * from students where stuid=?";
		statement = (PreparedStatement) connection.prepareStatement(sql);
		statement.setString(1, student.getStuid());
		ResultSet rs = statement.executeQuery();
		// �ж��Ƿ��������
		if (!rs.next()) {
			return null;
		} else {
			// ���ػ�ȡ��������
			return rs;
		}
	}

	// ��֤���ݿ����Ƿ���ڵ�ǰ�û� ��������򷵻�ResultSet���򷵻ؿ�ֵ
	public static ResultSet checkUserEmpty(User user) throws SQLException {
		Connection connection = DBHelper.connection();
		PreparedStatement statement = null;
		String sql = "select * from users where username=?";
		statement = (PreparedStatement) connection.prepareStatement(sql);
		statement.setString(1, user.Getname());
		ResultSet rs = statement.executeQuery();
		// �ж��Ƿ��������
		if (!rs.next())
			return null;
		else
			// ���ػ�ȡ��������
			return rs;

	}

}
