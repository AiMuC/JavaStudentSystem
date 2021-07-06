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
					// ����ҳ��ͼ��
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
			setTitle("ѧ����Ϣ����ϵͳ-��ǰȨ��:����Ա");
			break;
		case "0":
			setTitle("ѧ����Ϣ����ϵͳ-��ǰȨ��:��ʦ");
			break;
		case "-1":
			setTitle("ѧ����Ϣ����ϵͳ-��ǰȨ��:ѧ��");
			break;
		default:
			setTitle("ѧ����Ϣ����ϵͳ");
			break;
		}
//		setTitle("ѧ����Ϣ����ϵͳ");
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

		// �ж��Ƿ�Ϊѧ���û����� ������ʾ�ð�ť
		if (!user.Getusertype().equals("-1")) {

			JMenuItem AddStudent = new JMenuItem("����ѧ���û�");
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

		//��ѧ���û���ʾ�޸ĸ�����Ϣ��ť
		if(user.Getusertype().equals("-1")){
			JMenuItem ChangeStudentData = new JMenuItem("ѧ����Ϣ�޸�");
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
		
		// ������Ա�û���ʾ
		if (user.Getusertype().equals("1")) {
			JMenu UserAdmin = new JMenu("\u7528\u6237\u7BA1\u7406");
			menuBar.add(UserAdmin);

			JMenuItem AddUser = new JMenuItem("\u65B0\u589E\u7528\u6237");
			UserAdmin.add(AddUser);

			AddUser.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					dispose();// �ر�ҳ��
					new AddUser(user).main(user);// Я��������ת������û�ҳ
				}
			});

			JMenuItem ChangeUserData = new JMenuItem("�û�����");
			UserAdmin.add(ChangeUserData);
			ChangeUserData.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					dispose();// �ر�ҳ��
					UserList.main(user);
				}
			});
		}

		JMenu menu = new JMenu("\u8D26\u6237\u7BA1\u7406");
		menuBar.add(menu);

		JMenuItem ChangeUserPwd = new JMenuItem("�޸��˻�����");
		menu.add(ChangeUserPwd);

		ChangeUserPwd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();// �ر�ҳ��
				ResetMyPwd.main(user);// Я��������ת���޸�����ҳ
			}
		});

		JMenuItem ExitLogin = new JMenuItem("�˳���ǰ�˻�");
		menu.add(ExitLogin);

		ExitLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();// �ر�ҳ��
				JOptionPane.showMessageDialog(null, "�˳�����ɹ�");
				new UserLogin().main(null);// ��ת������ҳ��
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
						JOptionPane.showMessageDialog(null, "�����ѧ�ű���Ϊ��������!");
						return;
					}
					//ģ���������� ��ģ�����������table��
					try {
						Object[][] data = AdminDao.getSearchStudentData(searchstuid.getText());
						if(data!=null){
							table.setModel(new DefaultTableModel(data, new String[] { "\u5B66\u53F7",
									"\u59D3\u540D", "\u8EAB\u4EFD\u8BC1\u53F7", "\u6027\u522B", "\u5E74\u9F84" }));
						}else{
							JOptionPane.showMessageDialog(null, "δ��������ѧ��!");
							table.setModel(new DefaultTableModel(AdminDao.getAllStudentData(), new String[] { "\u5B66\u53F7",
									"\u59D3\u540D", "\u8EAB\u4EFD\u8BC1\u53F7", "\u6027\u522B", "\u5E74\u9F84" }));
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "ϵͳ����!");
						e1.printStackTrace();
					}
					//��ȷ�������� ֱ����ת�༭��
//					try {
//						Student student = AdminDao.getStudentData(searchstuid.getText());
//						if(student!=null){
//							dispose();
//							EditStudentData.main(user,searchstuid.getText());
//						}else{
//							JOptionPane.showMessageDialog(null, "�����ڸ�ѧ������������!");
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
					return false;// ��������༭
				}
			};
			
			//���ñ���ֹ�϶�
			table.getTableHeader().setReorderingAllowed(false);
			// ���ñ��߶�
			table.setRowHeight(30);
			
			// table.setEnabled(false);
			scrollPane.setViewportView(table);
			table.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 2) {
						// �����λ��
						int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint());
						// �����λ��
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
