package com.aimuc.ui;

import java.awt.BorderLayout;
import java.awt.Button;
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
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class AddStudent extends JFrame {

	protected static final Component JPanel = null;
	private JPanel contentPane;
	private JTextField StudentId;
	private JTextField SID;
	private JPasswordField pwd;
	private JPasswordField pwd_again;
	private JTextField StudentName;
	private JTextField age;

	/**
	 * Launch the application.
	 */
	public static void main(User user) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddStudent frame = new AddStudent(user);
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					// ҳ��ͼ��
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
	public AddStudent(User user) {
		// �Զ���ҳ�����Ͻǹرհ�ť�¼�
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				dispose();// �ر�ҳ��
				HomePage.main(user);// ��ת����ҳ
			}
		});
		setResizable(false);
		setTitle("\u6DFB\u52A0\u5B66\u751F");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 436, 368);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("\u5B66\u53F7\uFF1A");
		label.setBounds(70, 25, 70, 25);
		contentPane.add(label);

		StudentId = new JTextField();
		StudentId.setBounds(161, 25, 167, 25);
		contentPane.add(StudentId);
		StudentId.setColumns(10);

		JLabel label_1 = new JLabel("\u5BC6\u7801\uFF1A");
		label_1.setBounds(70, 60, 70, 25);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		label_2.setBounds(70, 95, 70, 25);
		contentPane.add(label_2);

		JLabel lblNewLabel = new JLabel("\u8EAB\u4EFD\u8BC1\u53F7:");
		lblNewLabel.setBounds(70, 136, 54, 15);
		contentPane.add(lblNewLabel);

		SID = new JTextField();
		SID.setBounds(161, 133, 167, 21);
		contentPane.add(SID);
		SID.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("\u6027\u522B:");
		lblNewLabel_1.setBounds(70, 168, 54, 15);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("\u540D\u79F0:");
		lblNewLabel_2.setBounds(70, 203, 54, 15);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("\u5E74\u9F84:");
		lblNewLabel_3.setBounds(70, 234, 54, 15);
		contentPane.add(lblNewLabel_3);

		pwd = new JPasswordField();
		pwd.setBounds(161, 62, 167, 21);
		contentPane.add(pwd);

		pwd_again = new JPasswordField();
		pwd_again.setBounds(161, 97, 167, 21);
		contentPane.add(pwd_again);

		StudentName = new JTextField();
		StudentName.setBounds(161, 200, 167, 21);
		contentPane.add(StudentName);
		StudentName.setColumns(10);

		age = new JTextField();
		age.setBounds(161, 231, 167, 21);
		contentPane.add(age);
		age.setColumns(10);

		JRadioButton nan = new JRadioButton("\u7537");
		nan.setSelected(true);
		nan.setBounds(161, 164, 83, 23);
		contentPane.add(nan);

		JRadioButton nv = new JRadioButton("\u5973");
		nv.setBounds(246, 164, 82, 23);
		contentPane.add(nv);

		ButtonGroup button = new ButtonGroup();
		button.add(nan);
		button.add(nv);

		JButton btnEnter = new JButton("\u6DFB \u52A0");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String arr[];// �����������ڴ��淵�ؽ��
				// �ж�ѧ��ѡ����Ա�
				String sex = (nan.isSelected() ? nan.getText() : nv.getText());
				int stuage;// ����ѧ���������
				try {
					// ǿ������ת��
					stuage = Integer.parseInt(age.getText());
				} catch (Exception e1) {
					// ����
					JOptionPane.showMessageDialog(null, "�������������������ͣ�");
					return;
				}
				// �ж�ѧ���Ƿ�Ϊint����
				if (!isNumeric(StudentId.getText())) {
					JOptionPane.showMessageDialog(null, "ѧ�ű���Ϊ�������ͣ�");
					return;
				}
				// �ж��������Ϣ�Ƿ�����
				if (StudentId.getText().equals("") || SID.getText().equals("") || stuage <= 0
						|| StudentName.getText().equals("") || new String(pwd.getPassword()).equals("")
						|| new String(pwd_again.getPassword()).equals("")) {
					JOptionPane.showMessageDialog(null, "����д�������ύ");
				} else {
					// �½�ѧ������
					Student student = new Student(StudentId.getText(), SID.getText(), new String(pwd.getPassword()),
							sex, StudentName.getText(), stuage);
					try {
						// �����ؽ����������
						arr = UserDao.addStudent(student);
						// �жϽ���Ƿ�ɹ�
						if (arr[0].equals("success")) {
							JOptionPane.showMessageDialog(null, arr[1]);
							dispose();
							HomePage.main(user);
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
		btnEnter.setBounds(70, 262, 118, 41);
		contentPane.add(btnEnter);

		JButton btnCancel = new JButton("\u6E05 \u7A7A");
		btnCancel.addActionListener(new ActionListener() {
			// ��ҳ����д����������Ϊ��
			public void actionPerformed(ActionEvent e) {
				StudentId.setText(null);
				pwd.setText(null);
				pwd_again.setText(null);
				SID.setText(null);
				StudentName.setText(null);
				age.setText(null);
			}
		});

		btnCancel.setBounds(210, 262, 118, 41);
		contentPane.add(btnCancel);
	}

	// �жϴ���Ĳ����Ƿ�Ϊ��������
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}
}
