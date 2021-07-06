package com.aimuc.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.MenuDragMouseListener;

import com.aimuc.dao.AdminDao;
import com.aimuc.pojo.Student;
import com.aimuc.pojo.User;

import javax.swing.JComboBox;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Dimension;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ListSelectionModel;

public class HomePage extends JFrame {

	protected static final Component JPanel = null;
	private JPanel contentPane;
	private JTable table;
	private JTextField searchstuid;

	/**
	 * Launch the application.
	 */
	public static void main(User user) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage frame = new HomePage(user);
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
	public HomePage(User user) throws Exception {
		setResizable(false);
		switch (user.Getusertype()) {
		case "1":
			setTitle("学生信息管理系统-当前权限:管理员");
			break;
		case "0":
			setTitle("学生信息管理系统-当前权限:教师");
			break;
		case "-1":
			setTitle("学生信息管理系统-当前权限:学生");
			break;
		default:
			setTitle("学生信息管理系统");
			break;
		}
//		setTitle("学生信息管理系统");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 741, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 725, 21);
		contentPane.add(menuBar);

		JMenu StudentAdmin = new JMenu("\u5B66\u751F\u7BA1\u7406");

		menuBar.add(StudentAdmin);

		// 判断是否为学生用户登入 是则不显示该按钮
		if (!user.Getusertype().equals("-1")) {

			JMenuItem AddStudent = new JMenuItem("新增学生用户");
			StudentAdmin.add(AddStudent);

			AddStudent.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					new AddStudent(user).main(user);
					dispose();
				}
			});

			// JMenuItem StudentSearch = new
			// JMenuItem("\u5B66\u751F\u4FE1\u606F\u67E5\u8BE2");
			// StudentAdmin.add(StudentSearch);
		}

		//仅学生用户显示修改个人信息按钮
		if(user.Getusertype().equals("-1")){
			JMenuItem ChangeStudentData = new JMenuItem("学生信息修改");
			StudentAdmin.add(ChangeStudentData);
			ChangeStudentData.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					dispose();
					StuEditData.main(user);
				}
			});
		}
		
		// 仅管理员用户显示
		if (user.Getusertype().equals("1")) {
			JMenu UserAdmin = new JMenu("\u7528\u6237\u7BA1\u7406");
			menuBar.add(UserAdmin);

			JMenuItem AddUser = new JMenuItem("\u65B0\u589E\u7528\u6237");
			UserAdmin.add(AddUser);

			AddUser.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					dispose();// 关闭页面
					new AddUser(user).main(user);// 携带参数跳转至添加用户页
				}
			});

			JMenuItem ChangeUserData = new JMenuItem("用户管理");
			UserAdmin.add(ChangeUserData);
			ChangeUserData.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					dispose();// 关闭页面
					UserList.main(user);
				}
			});
		}

		JMenu menu = new JMenu("\u8D26\u6237\u7BA1\u7406");
		menuBar.add(menu);

		JMenuItem ChangeUserPwd = new JMenuItem("修改账户密码");
		menu.add(ChangeUserPwd);

		ChangeUserPwd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();// 关闭页面
				ResetMyPwd.main(user);// 携带参数跳转至修改密码页
			}
		});

		JMenuItem ExitLogin = new JMenuItem("退出当前账户");
		menu.add(ExitLogin);

		ExitLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();// 关闭页面
				JOptionPane.showMessageDialog(null, "退出登入成功");
				new UserLogin().main(null);// 跳转至登入页面
			}
		});
		
//		 if (true) {
		if (!user.Getusertype().equals("-1")) {
			searchstuid = new JTextField();
			searchstuid.setBounds(10, 33, 592, 25);
			contentPane.add(searchstuid);
			searchstuid.setColumns(10);

			JButton SearchButton = new JButton("\u641C\u7D22\u5B66\u53F7");
			SearchButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(!AddStudent.isNumeric(searchstuid.getText())){
						JOptionPane.showMessageDialog(null, "输入的学号必须为数字类型!");
						return;
					}
					//模糊搜索操作 将模糊结果返回至table中
					try {
						Object[][] data = AdminDao.getSearchStudentData(searchstuid.getText());
						if(data!=null){
							table.setModel(new DefaultTableModel(data, new String[] { "\u5B66\u53F7",
									"\u59D3\u540D", "\u8EAB\u4EFD\u8BC1\u53F7", "\u6027\u522B", "\u5E74\u9F84" }));
						}else{
							JOptionPane.showMessageDialog(null, "未搜索到该学号!");
							table.setModel(new DefaultTableModel(AdminDao.getAllStudentData(), new String[] { "\u5B66\u53F7",
									"\u59D3\u540D", "\u8EAB\u4EFD\u8BC1\u53F7", "\u6027\u522B", "\u5E74\u9F84" }));
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "系统错误!");
						e1.printStackTrace();
					}
					//精确搜索操作 直接跳转编辑框
//					try {
//						Student student = AdminDao.getStudentData(searchstuid.getText());
//						if(student!=null){
//							dispose();
//							EditStudentData.main(user,searchstuid.getText());
//						}else{
//							JOptionPane.showMessageDialog(null, "不存在该学号请重新输入!");
//						}
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
				}
			});
			SearchButton.setBounds(612, 33, 109, 25);
			contentPane.add(SearchButton);

			ButtonGroup bGroup = new ButtonGroup();

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(0, 64, 735, 407);
			contentPane.add(scrollPane);

			table = new JTable() {
				public boolean isCellEditable(int row, int column) {
					return false;// 表格不允许被编辑
				}
			};
			
			//设置表格禁止拖动
			table.getTableHeader().setReorderingAllowed(false);
			// 设置表格高度
			table.setRowHeight(30);
			
			// table.setEnabled(false);
			scrollPane.setViewportView(table);
			table.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 2) {
						// 获得行位置
						int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint());
						// 获得列位置
						int col = ((JTable) e.getSource()).columnAtPoint(e.getPoint());
						dispose();
						EditStudentData.main(user, (String) table.getValueAt(row, 0));
					}
				}
			});

			table.setModel(new DefaultTableModel(AdminDao.getAllStudentData(), new String[] { "\u5B66\u53F7",
					"\u59D3\u540D", "\u8EAB\u4EFD\u8BC1\u53F7", "\u6027\u522B", "\u5E74\u9F84" }));
		}

	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
