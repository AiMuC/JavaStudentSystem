package com.aimuc.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.aimuc.dao.UserDao;
import com.aimuc.pojo.Student;
import com.aimuc.pojo.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Font;

public class ResetPassword extends JFrame {

	protected static final Component JPanel = null;
	private JPanel contentPane;
	private JTextField UserName;
	private JTextField SID;
	private JPasswordField password;
	private JPasswordField passwordagain;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResetPassword frame = new ResetPassword();
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
	 */
	public ResetPassword() {
		// �Զ���ҳ�����Ͻǹرհ�ť�¼�
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				dispose();// �رյ�ǰҳ��
				new UserLogin().main(null);// ��ת���������
			}
		});
		setTitle("\u91CD\u7F6E\u5BC6\u7801");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 405, 302);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel title = new JLabel("\u5B66\u751F\u91CD\u7F6E\u5BC6\u7801");
		title.setFont(new Font("����", Font.PLAIN, 22));
		title.setBounds(130, 10, 160, 31);
		contentPane.add(title);

		JLabel label = new JLabel("\u5B66\u53F7\uFF1A");
		label.setBounds(46, 54, 54, 15);
		contentPane.add(label);

		JLabel label_2 = new JLabel("\u65B0\u5BC6\u7801\uFF1A");
		label_2.setBounds(46, 128, 84, 15);
		contentPane.add(label_2);

		JLabel label_3 = new JLabel("\u8EAB\u4EFD\u8BC1\u540E\u516D\u4F4D\uFF1A");
		label_3.setBounds(46, 89, 104, 15);
		contentPane.add(label_3);

		JLabel lblNewLabel = new JLabel("\u786E\u8BA4\u5BC6\u7801");
		lblNewLabel.setBounds(46, 165, 54, 15);
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("\u8FD4\u56DE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new UserLogin().main(null);
			}
		});
		btnNewButton.setBounds(46, 210, 126, 40);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("\u63D0\u4EA4");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String arr[];// ����һ���������ڴ洢����ֵ
				// �ж���Ϣ�Ƿ���д����
				if (UserName.getText().equals("") || SID.getText().equals("")
						|| new String(passwordagain.getPassword()).equals("")
						|| new String(password.getPassword()).equals("")) {
					JOptionPane.showMessageDialog(null, "��Ϣ��д������!������!");
				} else {
					// �ж����������Ƿ���дһ��
					if (new String(password.getPassword()).equals(new String(passwordagain.getPassword()))) {
						// ����ѧ����������û��Լ��������֤
						Student student = new Student(UserName.getText(), new String(password.getPassword()),
								SID.getText());
						try {
							// ��ȡ����ֵ��������
							arr = UserDao.resetPassword(student);
							// �жϽ���Ƿ�ɹ�
							if (arr[0].equals("success")) {
								JOptionPane.showMessageDialog(null, arr[1]);
								dispose();// �رյ�ǰҳ��
								new UserLogin().main(null);// ��ת���������
							} else {
								JOptionPane.showMessageDialog(null, arr[1]);
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(null, "����������д��һ��");
					}
				}
			}
		});
		btnNewButton_1.setBounds(224, 210, 126, 40);
		contentPane.add(btnNewButton_1);

		UserName = new JTextField();
		UserName.setBounds(147, 51, 203, 21);
		contentPane.add(UserName);
		UserName.setColumns(10);

		SID = new JTextField();
		SID.setBounds(147, 86, 203, 21);
		contentPane.add(SID);
		SID.setColumns(10);

		password = new JPasswordField();
		password.setBounds(147, 125, 203, 21);
		contentPane.add(password);

		passwordagain = new JPasswordField();
		passwordagain.setBounds(147, 162, 203, 21);
		contentPane.add(passwordagain);
	}

}
