package com.aimuc.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.aimuc.dao.UserDao;
import com.aimuc.pojo.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Font;

public class Register extends JFrame {

	protected static final Component JPanel = null;
	private JPanel contentPane;
	private JTextField username;
	private JPasswordField password;
	private JPasswordField passwordagain;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
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
	public Register() {
		// �Զ���ҳ�����Ͻǹرհ�ť�¼�
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				dispose();// �ر�ҳ��
				new UserLogin().main(null);// ��ת����ҳ
			}
		});
		setTitle("\u7528\u6237\u6CE8\u518C");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 354, 256);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel title = new JLabel("\u7528\u6237\u6CE8\u518C");
		title.setFont(new Font("����", Font.PLAIN, 22));
		title.setBounds(122, 10, 138, 43);
		contentPane.add(title);

		JLabel label = new JLabel("\u7528\u6237\u540D\uFF1A");
		label.setBounds(57, 63, 67, 15);
		contentPane.add(label);

		JLabel lblNewLabel = new JLabel("\u5BC6  \u7801\uFF1A");
		lblNewLabel.setBounds(57, 96, 67, 15);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		lblNewLabel_1.setBounds(57, 134, 67, 15);
		contentPane.add(lblNewLabel_1);

		JButton back = new JButton("\u8FD4\u56DE");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();// �ر�ҳ��
				new UserLogin().main(null);// ��ת������ҳ��
			}
		});
		back.setBounds(57, 167, 101, 33);
		contentPane.add(back);

		JButton button = new JButton("\u6CE8\u518C");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] arr;// �����������ڴ��淵�ؽ��
				// �ж���Ϣ�Ƿ���д����
				if (username.getText().equals("") || new String(password.getPassword()).equals("")
						|| new String(passwordagain.getPassword()).equals("")) {
					JOptionPane.showMessageDialog(null, "�û�����������δ��д");
					return;
				} else if (!new String(password.getPassword()).equals(new String(passwordagain.getPassword()))) {
					JOptionPane.showMessageDialog(null, "�����������벻һ��");
				} else {
					// �½�User����洢��ѧ���Լ�������
					User user = new User(username.getText(), new String(password.getPassword()));
					try {
						// ��ȡ����ֵ����arr
						arr = UserDao.userReg(user);
						// �жϽ���Ƿ���ȷ
						if (arr[0].equals("success")) {
							JOptionPane.showMessageDialog(null, arr[1]);
							dispose();// �ر�ҳ��
							new UserLogin().main(null);// ��ת������ҳ
						} else {
							JOptionPane.showMessageDialog(null, arr[1]);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		button.setBounds(168, 167, 106, 33);
		contentPane.add(button);

		username = new JTextField();
		username.setBounds(122, 60, 152, 21);
		contentPane.add(username);
		username.setColumns(10);

		password = new JPasswordField();
		password.setBounds(122, 93, 152, 21);
		contentPane.add(password);

		passwordagain = new JPasswordField();
		passwordagain.setBounds(122, 131, 152, 21);
		contentPane.add(passwordagain);
	}
}
