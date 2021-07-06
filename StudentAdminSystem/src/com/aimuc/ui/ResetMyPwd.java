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
import com.aimuc.pojo.Student;
import com.aimuc.pojo.User;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ResetMyPwd extends JFrame {

	private JPanel contentPane;
	private JTextField oldpassword;
	private JPasswordField npwd;
	private JPasswordField npwdagain;

	/**
	 * Launch the application.
	 */
	public static void main(User user) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResetMyPwd frame = new ResetMyPwd(user);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
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
	 */
	public ResetMyPwd(User user) {
		setResizable(false);
		// �Զ���ҳ�����Ͻǹرհ�ť�¼�
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				dispose();// �ر�ҳ��
				HomePage.main(user);// ��ת����ҳ
			}
		});
		setTitle("\u91CD\u7F6E\u5BC6\u7801");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 442, 288);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("\u91CD\u7F6E\u5BC6\u7801");
		label.setFont(new Font("����", Font.PLAIN, 19));
		label.setBounds(174, 0, 124, 66);
		contentPane.add(label);

		JButton button = new JButton("\u8FD4\u56DE");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				HomePage.main(user);
			}
		});
		button.setBounds(10, 190, 185, 41);
		contentPane.add(button);

		JButton button_1 = new JButton("\u63D0\u4EA4");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String arr[];// �����������ڴ��淵�ؽ��
				// �ж����û�Ȩ�� -1Ϊѧ���û� ������Ϊ��ʦ/����Ա
				if (user.Getusertype().equals("-1")) {
					// �ж���Ϣ�Ƿ���д����
					if (oldpassword.equals("") || new String(npwd.getPassword()).equals("")
							|| new String(npwdagain.getPassword()).equals("")) {
						JOptionPane.showMessageDialog(null, "����д���������ύ");
					} else {
						// �½�ѧ������洢��ѧ���Լ�������
						Student student = new Student(user.Getname(), new String(npwd.getPassword()));
						try {
							// ��ȡ����ֵ����arr
							arr = UserDao.resetPwdForStudent(student, oldpassword.getText());
							// �жϽ���Ƿ���ȷ
							if (arr[0].equals("success")) {
								JOptionPane.showMessageDialog(null, arr[1]);
								dispose();// �ر�ҳ��
								HomePage.main(user);// Я��������ת����ҳ
							} else {
								JOptionPane.showMessageDialog(null, arr[1]);
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				} else {
					// ��ͨ�û��һ�����
					// �ж���Ϣ�Ƿ���д����
					if (oldpassword.equals("") || new String(npwd.getPassword()).equals("")
							|| new String(npwdagain.getPassword()).equals("")) {
						JOptionPane.showMessageDialog(null, "����д���������ύ");
					} else {
						// �½�User����洢��ѧ���Լ�������
						User userpwd = new User(user.Getname(), new String(npwd.getPassword()));
						try {
							// ��ȡ����ֵ����arr
							arr = UserDao.resetPwdForUser(userpwd, oldpassword.getText());
							// �жϽ���Ƿ���ȷ
							if (arr[0].equals("success")) {
								JOptionPane.showMessageDialog(null, arr[1]);
								dispose();// �ر�ҳ��
								HomePage.main(user);// Я��������ת����ҳ
							} else {
								JOptionPane.showMessageDialog(null, arr[1]);
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
		button_1.setBounds(206, 190, 210, 41);
		contentPane.add(button_1);

		JLabel label_1 = new JLabel("\u539F\u5BC6\u7801\uFF1A");
		label_1.setBounds(33, 69, 88, 15);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("\u65B0\u5BC6\u7801\uFF1A");
		label_2.setBounds(33, 108, 76, 15);
		contentPane.add(label_2);

		JLabel label_3 = new JLabel("\u786E\u8BA4\u65B0\u5BC6\u7801\uFF1A");
		label_3.setBounds(33, 147, 88, 15);
		contentPane.add(label_3);

		oldpassword = new JTextField();
		oldpassword.setBounds(113, 66, 279, 21);
		contentPane.add(oldpassword);
		oldpassword.setColumns(10);

		npwd = new JPasswordField();
		npwd.setBounds(113, 105, 279, 21);
		contentPane.add(npwd);

		npwdagain = new JPasswordField();
		npwdagain.setBounds(113, 144, 279, 21);
		contentPane.add(npwdagain);
	}
}
