package com.aimuc.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.aimuc.dao.AdminDao;
import com.aimuc.pojo.User;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserList extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField searchuser;

	/**
	 * Launch the application.
	 */
	public static void main(User user) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserList frame = new UserList(user);
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
	public UserList(User user) throws Exception {
		setTitle("\u7528\u6237\u7BA1\u7406");
		setResizable(false);
		// 自定义页面右上角关闭按钮事件
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				dispose();// 关闭页面
				HomePage.main(user);// 跳转至主页
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 686, 529);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 91, 680, 409);
		contentPane.add(scrollPane);

		table = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;// 表格不允许被编辑
			}
		};
		// 设置表格禁止拖动
		table.getTableHeader().setReorderingAllowed(false);
		// 设置表格高度
		table.setRowHeight(30);
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(AdminDao.getAllUserData(), new String[] { "用户名", "用户密码", "权限", "账户状态" }));

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					// 获得行位置
					int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint());
					// 获得列位置
					int col = ((JTable) e.getSource()).columnAtPoint(e.getPoint());
					dispose();
					EditUserData.main(user, (String) table.getValueAt(row, 0));
					// System.out.println(table.getValueAt(row, 0));
				}
			}
		});

		searchuser = new JTextField();
		searchuser.setBounds(10, 56, 557, 25);
		contentPane.add(searchuser);
		searchuser.setColumns(10);

		JButton btnNewButton = new JButton("\u641C\u7D22");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//模糊搜索操作
				try {
					//获取返回值存入data变量
					Object[][] data = AdminDao.getSearchUserData(searchuser.getText());
					//判断是否有返回数据
					if (data != null) {
						//设置table
						table.setModel(new DefaultTableModel(data, new String[] { "用户名", "用户密码", "权限", "账户状态" }));
					}else{
						JOptionPane.showMessageDialog(null, "未搜索到该用户!");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//精确搜索操作 已弃用
				// try {
				// //新建用户对象用于存放获取到的数据
				// User userdata =AdminDao.getUserData(searchuser.getText());
				// //判断是否存在该用户
				// if(userdata!=null){
				// dispose();//关闭页面
				// EditUserData.main(user, searchuser.getText());
				// }else{
				// JOptionPane.showMessageDialog(null, "不存在该用户请重新输入!");
				// }
				// } catch (Exception e1) {
				// // TODO Auto-generated catch block
				// e1.printStackTrace();
				// }
			}
		});
		btnNewButton.setBounds(577, 57, 93, 25);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel = new JLabel("\u7528\u6237\u7BA1\u7406");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 23));
		lblNewLabel.setBounds(284, 10, 101, 36);
		contentPane.add(lblNewLabel);

		JButton button = new JButton("\u8FD4\u56DE\u4E3B\u9875");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();// 关闭页面
				HomePage.main(user);// 跳转至主页
			}
		});
		button.setBounds(10, 10, 106, 36);
		contentPane.add(button);

	}
}
