package com.aimuc.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.aimuc.dao.AdminDao;
import com.aimuc.pojo.Student;
import com.aimuc.pojo.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditStudentData extends JFrame {

	private JPanel contentPane;
	private JTextField stuid;
	private JTextField studentname;
	private JTextField password;
	private JTextField SID;
	private JTextField age;

	/**
	 * Launch the application.
	 */
	public static void main(User user, String Stuid) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditStudentData frame = new EditStudentData(user, Stuid);
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
	 * 
	 * @throws Exception
	 */
	public EditStudentData(User user, String Stuid) throws Exception {

		Student studentdata = AdminDao.getStudentData(Stuid);

		// 自定义页面右上角关闭按钮事件
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				dispose();// 关闭页面
				HomePage.main(user);// 携带参数跳转至首页
			}
		});
		setTitle("正在编辑的学号:" + Stuid);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 452, 421);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel label = new JLabel("\u5B66\u53F7:");
		label.setFont(new Font("宋体", Font.PLAIN, 16));
		label.setBounds(50, 71, 54, 15);
		contentPane.add(label);

		JLabel label_1 = new JLabel("\u59D3\u540D:");
		label_1.setFont(new Font("宋体", Font.PLAIN, 16));
		label_1.setBounds(50, 100, 54, 15);
		contentPane.add(label_1);

		JLabel lblNewLabel_1 = new JLabel("\u7528\u6237\u5BC6\u7801:");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(18, 135, 83, 15);
		contentPane.add(lblNewLabel_1);

		JLabel label_2 = new JLabel("\u8EAB\u4EFD\u8BC1\u53F7:");
		label_2.setFont(new Font("宋体", Font.PLAIN, 16));
		label_2.setBounds(18, 170, 72, 15);
		contentPane.add(label_2);

		JLabel label_3 = new JLabel("\u5E74\u9F84:");
		label_3.setFont(new Font("宋体", Font.PLAIN, 16));
		label_3.setBounds(50, 206, 54, 15);
		contentPane.add(label_3);

		JLabel label_4 = new JLabel("\u6027\u522B:");
		label_4.setFont(new Font("宋体", Font.PLAIN, 16));
		label_4.setBounds(50, 242, 54, 15);
		contentPane.add(label_4);

		JRadioButton nan = new JRadioButton("\u7537");
		nan.setBounds(99, 239, 72, 23);
		if (studentdata.Getsex().equals("男")) {
			nan.setSelected(true);
		}
		contentPane.add(nan);

		JRadioButton nv = new JRadioButton("\u5973");
		nv.setBounds(181, 239, 72, 23);
		if (studentdata.Getsex().equals("女")) {
			nv.setSelected(true);
		}
		contentPane.add(nv);

		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(nan);
		buttonGroup.add(nv);

		stuid = new JTextField();
		stuid.setEnabled(false);
		stuid.setText(studentdata.getStuid());
		stuid.setBounds(99, 64, 299, 25);
		contentPane.add(stuid);
		stuid.setColumns(10);

		studentname = new JTextField();
		studentname.setText(studentdata.Getname());
		studentname.setBounds(99, 98, 299, 25);
		contentPane.add(studentname);
		studentname.setColumns(10);

		password = new JTextField();
		password.setText(studentdata.getPassword());
		password.setBounds(99, 133, 299, 25);
		contentPane.add(password);
		password.setColumns(10);

		SID = new JTextField();
		SID.setText(studentdata.getSID());
		SID.setBounds(100, 168, 298, 25);
		contentPane.add(SID);
		SID.setColumns(10);

		age = new JTextField();
		age.setText(studentdata.Getage() + "");
		age.setBounds(99, 204, 299, 25);
		contentPane.add(age);
		age.setColumns(10);

		JLabel lblNewLabel = new JLabel("\u5B66\u751F\u4FE1\u606F\u4FEE\u6539");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 22));
		lblNewLabel.setBounds(152, 10, 150, 44);
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("\u63D0\u4EA4\u4FEE\u6539");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String sex = (nan.isSelected() ? nan.getText() : nv.getText());
				int stuage;// 创建学生年龄变量
				try {
					// 强制类型转换
					stuage = Integer.parseInt(age.getText());
				} catch (Exception e1) {
					// 跳出
					JOptionPane.showMessageDialog(null, "您输入的年龄非数字类型！");
					return;
				}
				// 判断学号是否为int类型
				if (!AddStudent.isNumeric(stuid.getText())) {
					JOptionPane.showMessageDialog(null, "学号必须为数字类型！");
					return;
				}
				// 判断输入的信息是否完整
				if (stuid.getText().equals("") || SID.getText().equals("") || stuage <= 0
						|| studentname.getText().equals("") || password.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "请填写完整后提交");
					// 调用修改方法
				} else {
					Student student = new Student(stuid.getText(), SID.getText(), password.getText(), sex,
							studentname.getText(), stuage);
					try {
						String arr[] = AdminDao.editStudentData(student);
						if (arr[0].equals("success")) {
							JOptionPane.showMessageDialog(null, arr[1]);
							dispose();
							HomePage.main(user);
						} else {
							JOptionPane.showMessageDialog(null, arr[1]);
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		});
		btnNewButton.setBounds(18, 307, 185, 44);
		contentPane.add(btnNewButton);

		JButton button = new JButton("删除该学生");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int delete = JOptionPane.showConfirmDialog(null, "确认删除吗", "确认删除框", JOptionPane.YES_NO_OPTION);
				// 判断用户点击的按钮0为确认按钮
				if (delete == 0) {
					try {
						String arr[] = AdminDao.deleteStudentData(Stuid);
						if (arr[0].equals("success")) {
							JOptionPane.showMessageDialog(null, arr[1]);
							dispose();// 关闭页面
							HomePage.main(user);// 携带参数跳转至首页
						} else {
							JOptionPane.showMessageDialog(null, arr[1]);
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					System.out.println("取消操作");
				}
			}
		});
		button.setBounds(213, 307, 185, 44);
		contentPane.add(button);
	}
}
