package com.aimuc.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.aimuc.dao.UserDao;
import com.aimuc.pojo.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddUser extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JTextField password;

	/**
	 * Launch the application.
	 */
	public static void main(User user) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddUser frame = new AddUser(user);
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					// 设置页面图标
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
	public AddUser(User user) {
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				dispose();// 关闭页面
				HomePage.main(user);// 携带参数跳转至首页
			}
		});
		setTitle("\u6DFB\u52A0\u7528\u6237");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 365, 280);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("\u7528\u6237\u540D\uFF1A");
		label.setBounds(36, 45, 54, 15);
		contentPane.add(label);

		JLabel label_1 = new JLabel("\u5BC6\u7801\uFF1A");
		label_1.setBounds(36, 87, 54, 15);
		contentPane.add(label_1);

		JLabel lblNewLabel = new JLabel("\u7528\u6237\u6743\u9650:");
		lblNewLabel.setBounds(36, 123, 68, 15);
		contentPane.add(lblNewLabel);

		username = new JTextField();
		username.setBounds(118, 42, 178, 21);
		contentPane.add(username);
		username.setColumns(10);

		password = new JTextField();
		password.setBounds(118, 84, 178, 21);
		contentPane.add(password);
		password.setColumns(10);

		JRadioButton admin = new JRadioButton("\u7BA1\u7406\u5458");
		admin.setBounds(115, 119, 85, 23);
		contentPane.add(admin);

		JRadioButton noadmin = new JRadioButton("\u666E\u901A\u7528\u6237");
		noadmin.setSelected(true);
		noadmin.setBounds(203, 119, 97, 23);
		contentPane.add(noadmin);

		ButtonGroup bGroup = new ButtonGroup();
		bGroup.add(admin);
		bGroup.add(noadmin);

		JButton button = new JButton("\u8FD4\u56DE");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();// 关闭页面
				HomePage.main(user);// 跳转回首页
			}
		});
		button.setBounds(36, 171, 118, 38);
		contentPane.add(button);

		JButton btnNewButton = new JButton("\u63D0\u4EA4");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// 判断是否为管理员选中为true否则为false
				String isadmin = (admin.isSelected() ? "1" : "0");
				// 判断信息是否填写完整
				if (isadmin.equals("") || username.getText().equals("") || password.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "信息不得为空！");
				} else {
					// 创建user对象
					User user1 = new User(username.getText(), password.getText(), isadmin);
					try {
						// 获取返回值存入数组
						String[] arr = UserDao.addUser(user1);
						// 判断执行结果
						if (arr[0].equals("success")) {
							JOptionPane.showMessageDialog(null, arr[1]);
							dispose();// 关闭页面
							HomePage.main(user);// 跳转回首页
						} else {
							JOptionPane.showMessageDialog(null, arr[1]);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		btnNewButton.setBounds(164, 171, 132, 38);
		contentPane.add(btnNewButton);
	}
}
