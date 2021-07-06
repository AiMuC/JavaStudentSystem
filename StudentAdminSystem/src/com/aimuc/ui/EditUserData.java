package com.aimuc.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.aimuc.dao.AdminDao;
import com.aimuc.pojo.User;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.lang.model.element.Element;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditUserData extends JFrame {

	private JPanel contentPane;
	private JTextField uname;
	private JTextField upwd;

	/**
	 * Launch the application.
	 */
	public static void main(User user, String username) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditUserData frame = new EditUserData(user, username);
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					// 页面图标
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
	 * 
	 * @throws Exception
	 */
	public EditUserData(User user, String username) throws Exception {
		User userdata = AdminDao.getUserData(username);
		setTitle("用户信息修改-当前正在修改:" + username);
		setResizable(false);
		// 自定义页面右上角关闭按钮事件
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				dispose();// 关闭页面
				UserList.main(user);// 跳转至主页
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 363, 357);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("\u7528\u6237\u4FE1\u606F");
		label.setFont(new Font("宋体", Font.PLAIN, 22));
		label.setBounds(145, 10, 106, 59);
		contentPane.add(label);

		JLabel label_1 = new JLabel("\u7528\u6237\u540D:");
		label_1.setFont(new Font("宋体", Font.PLAIN, 18));
		label_1.setBounds(35, 69, 69, 21);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("\u5BC6\u7801:");
		label_2.setFont(new Font("宋体", Font.PLAIN, 18));
		label_2.setBounds(50, 112, 54, 21);
		contentPane.add(label_2);

		JLabel label_3 = new JLabel("\u6743\u9650:");
		label_3.setFont(new Font("宋体", Font.PLAIN, 18));
		label_3.setBounds(50, 156, 54, 21);
		contentPane.add(label_3);

		JLabel lblNewLabel = new JLabel("\u8D26\u6237\u72B6\u6001:");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel.setBounds(13, 198, 81, 21);
		contentPane.add(lblNewLabel);

		uname = new JTextField();
		uname.setEnabled(false);
		uname.setText(userdata.Getname());
		uname.setBounds(114, 71, 175, 21);
		contentPane.add(uname);
		uname.setColumns(10);

		upwd = new JTextField();
		upwd.setText(userdata.Getpass());
		upwd.setBounds(114, 114, 175, 21);
		contentPane.add(upwd);
		upwd.setColumns(10);

		JRadioButton admin = new JRadioButton("\u7BA1\u7406\u5458");
		if (userdata.Getusertype().equals("1")) {
			admin.setSelected(true);
		}
		admin.setBounds(113, 157, 81, 23);
		contentPane.add(admin);

		JRadioButton teacher = new JRadioButton("\u6559\u5E08");
		if (userdata.Getusertype().equals("0")) {
			teacher.setSelected(true);
		}
		teacher.setBounds(235, 157, 97, 23);
		contentPane.add(teacher);

		ButtonGroup bGroup = new ButtonGroup();
		bGroup.add(admin);
		bGroup.add(teacher);

		JRadioButton ok = new JRadioButton("\u6B63\u5E38");
		if (userdata.Getuserstate().equals("0")) {
			ok.setSelected(true);
		}
		ok.setBounds(114, 199, 81, 23);
		contentPane.add(ok);

		JRadioButton l_ock = new JRadioButton("\u9501\u5B9A");
		if (userdata.Getuserstate().equals("1")) {
			l_ock.setSelected(true);
		}
		l_ock.setBounds(235, 199, 69, 23);
		contentPane.add(l_ock);

		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(ok);
		buttonGroup.add(l_ock);

		JButton btnNewButton = new JButton("\u63D0\u4EA4\u4FEE\u6539");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String isadmin = (admin.isSelected() ? "1" : "0");
				String lock = (l_ock.isSelected() ? "1" : "0");
				String username = uname.getText();
				String password = upwd.getText();
				//非空判断
				if (isadmin.isEmpty() || lock.isEmpty() || username.isEmpty() || password.isEmpty()) {
					JOptionPane.showMessageDialog(null, "信息未填写完整,请检查后重试!");
				} else {
					try {
						//新建user对象用于存放编辑完成的数据
						User edituser = new User(username, password, isadmin, lock);
						//新建数组用于存放返回值
						String arr[] = AdminDao.editUserData(edituser);
						//操作判断
						if(arr[0].equals("success")){
							JOptionPane.showMessageDialog(null, arr[1]);
							dispose();
							UserList.main(user);
						}else {
							JOptionPane.showMessageDialog(null, arr[1]);
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnNewButton.setBounds(10, 251, 140, 39);
		contentPane.add(btnNewButton);

		JButton button = new JButton("\u5220\u9664\u8BE5\u7528\u6237");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int delete = JOptionPane.showConfirmDialog(null, "确认删除吗", "确认删除框", JOptionPane.YES_NO_OPTION);
				// 判断用户点击的按钮0为确认按钮
				if (delete == 0) {
					try {
						String arr[] = AdminDao.deleteUserData(username);
						if (arr[0].equals("success")) {
							JOptionPane.showMessageDialog(null, arr[1]);
							dispose();// 关闭页面
							UserList.main(user);// 携带参数跳转至首页
						} else {
							JOptionPane.showMessageDialog(null, arr[1]);
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					System.out.println("取消操作");
				}
			}
		});
		button.setBounds(192, 251, 140, 39);
		contentPane.add(button);

	}
}
