package com.aimuc.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.aimuc.dao.DBHelper;
import com.aimuc.dao.UserDao;
import com.aimuc.pojo.Student;
import com.aimuc.pojo.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import java.awt.Font;

public class UserLogin extends JFrame {

	protected static final Component JPanel = null;
	private JPanel contentPane;
	private JTextField username;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		// 为所有页面加入样式Nimbus
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserLogin frame = new UserLogin();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					// 设置页面左上角ico图标
					javax.swing.ImageIcon icon = new javax.swing.ImageIcon(getClass().getResource("/imgs/favicon.ico"));
					frame.setIconImage(icon.getImage());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UserLogin() {
		setResizable(false);
		setTitle("\u7528\u6237\u767B\u5165");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 395, 293);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel title = new JLabel("\u5B66\u751F\u4FE1\u606F\u7BA1\u7406\u7CFB\u7EDF");
		title.setFont(new Font("宋体", Font.PLAIN, 23));
		title.setBounds(100, 10, 209, 35);
		contentPane.add(title);

		JLabel label = new JLabel("\u7528\u6237\u540D\uFF1A");
		label.setFont(new Font("宋体", Font.PLAIN, 13));
		label.setBounds(40, 58, 54, 18);
		contentPane.add(label);

		JLabel label_1 = new JLabel("\u5BC6  \u7801\uFF1A");
		label_1.setFont(new Font("宋体", Font.PLAIN, 13));
		label_1.setBounds(40, 97, 54, 15);
		contentPane.add(label_1);

		JButton Register = new JButton("用户注册");
		Register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();// 关闭当前页面
				new Register().main(null);// 打开用户注册页面
			}
		});
		Register.setBounds(40, 204, 144, 35);
		contentPane.add(Register);

		JRadioButton isstudent = new JRadioButton("\u662F\u5426\u4E3A\u5B66\u751F\u7528\u6237\uFF1F");
		isstudent.setFont(new Font("宋体", Font.PLAIN, 15));
		isstudent.setSelected(true);
		isstudent.setBounds(40, 130, 269, 23);
		contentPane.add(isstudent);

		JButton Login = new JButton("\u767B  \u5165");
		Login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 判断是否为学生用户 否则执行管理员登入
				if (isstudent.isSelected()) {
					// 判断是否输入了内容
					if (username.getText().equals("") || new String(password.getPassword()).equals("")) {
						JOptionPane.showMessageDialog(null, "用户名或密码为空!");
						return;
					}
					String[] arr;
					// 创建student对象用于登入验证
					Student student = new Student(username.getText(), new String(password.getPassword()));
					try {
						// 获取登入结果用于判断
						arr = UserDao.studentLogin(student);
						// 结果为success则进入主页否则提示错误
						if (arr[0].equals("success")) {
							JOptionPane.showMessageDialog(JPanel, arr[1]);
							// 新建User对象用于携带权限以及用户信息
							User userdata = new User(student.getStuid(), student.getPassword(), arr[2]);
							// 携带权限信息等跳转至主页
							HomePage.main(userdata);
							dispose();
						} else {
							JOptionPane.showMessageDialog(JPanel, arr[1]);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else {
					// 判断是否输入了内容
					if (username.getText().equals("") || new String(password.getPassword()).equals("")) {
						JOptionPane.showMessageDialog(null, "用户名或密码为空!");
						return;
					}
					String[] arr;
					try {
						// 创建User对象用于登入验证
						User user = new User(username.getText(), new String(password.getPassword()));
						// 获取登入结果用于判断
						arr = UserDao.userLogin(user);
						// 结果为success则进入主页否则提示错误
						if (arr[0].equals("success")) {
							JOptionPane.showMessageDialog(JPanel, arr[1]);
							// 新建User对象用于携带权限以及用户信息
							User userdata = new User(user.Getname(), user.Getpass(), arr[2]);
							// 携带权限信息等跳转至主页
							HomePage.main(userdata);
							dispose();
						} else {
							JOptionPane.showMessageDialog(JPanel, arr[1]);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						JOptionPane.showMessageDialog(JPanel, "系统错误");
					}
				}
			}
		});
		Login.setBounds(40, 159, 303, 35);
		contentPane.add(Login);

		username = new JTextField();
		username.setBounds(100, 55, 243, 24);
		contentPane.add(username);
		username.setColumns(10);

		JButton ResetPassword = new JButton("\u5FD8\u8BB0\u5BC6\u7801/\u4EC5\u5B66\u751F");
		ResetPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();// 关闭页面
				new ResetPassword().main(null);// 跳转至重置密码界面
			}
		});
		ResetPassword.setBounds(199, 204, 144, 35);
		contentPane.add(ResetPassword);

		password = new JPasswordField();
		password.setBounds(100, 93, 243, 24);
		contentPane.add(password);
	}
}
