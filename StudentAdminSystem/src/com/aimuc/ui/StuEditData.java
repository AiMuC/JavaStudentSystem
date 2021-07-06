package com.aimuc.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.aimuc.dao.AdminDao;
import com.aimuc.dao.StudentDao;
import com.aimuc.pojo.Student;
import com.aimuc.pojo.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class StuEditData extends JFrame {

	private JPanel contentPane;
	private JTextField age;
	private JTextField SID;
	private JTextField stuname;
	private JTextField stuid;

	/**
	 * Launch the application.
	 */
	public static void main(User user) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StuEditData frame = new StuEditData(user);
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					// 设置页面左上角ico图标
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
	 * @throws Exception 
	 */
	public StuEditData(User user) throws Exception {

		// 自定义页面右上角关闭按钮事件
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				dispose();// 关闭页面
				HomePage.main(user);// 携带参数跳转至首页
			}
		});
		Student student=AdminDao.getStudentData(user.Getname());
		if(student==null){
			dispose();
			JOptionPane.showMessageDialog(null, "未知错误,请返回重试!");
		}
		setResizable(false);
		setTitle("\u4FE1\u606F\u4FEE\u6539");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 456, 363);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u4FE1\u606F\u4FEE\u6539");
		label.setFont(new Font("宋体", Font.PLAIN, 22));
		label.setBounds(168, 10, 110, 52);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u6211\u7684\u5B66\u53F7:");
		label_1.setFont(new Font("宋体", Font.PLAIN, 18));
		label_1.setBounds(59, 63, 87, 28);
		contentPane.add(label_1);
		
		JLabel lblNewLabel = new JLabel("\u59D3\u540D:");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel.setBounds(95, 101, 51, 28);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u8EAB\u4EFD\u8BC1\u53F7:");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(59, 137, 87, 28);
		contentPane.add(lblNewLabel_1);
		
		JLabel label_2 = new JLabel("\u5E74\u9F84:");
		label_2.setFont(new Font("宋体", Font.PLAIN, 18));
		label_2.setBounds(92, 175, 51, 28);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("\u6027\u522B:");
		label_3.setFont(new Font("宋体", Font.PLAIN, 18));
		label_3.setBounds(95, 213, 51, 28);
		contentPane.add(label_3);
		
		JRadioButton nan = new JRadioButton("\u7537");
		nan.setBounds(152, 218, 87, 23);
		if (student.Getsex().equals("男")) {
			nan.setSelected(true);
		}
		contentPane.add(nan);
		
		JRadioButton nv = new JRadioButton("\u5973");
		nv.setBounds(241, 218, 121, 23);
		if (student.Getsex().equals("女")) {
			nv.setSelected(true);
		}
		contentPane.add(nv);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(nan);
		buttonGroup.add(nv);
		
		age = new JTextField();
		age.setText(student.Getage()+"");
		age.setBounds(153, 179, 200, 25);
		contentPane.add(age);
		age.setColumns(10);
		
		SID = new JTextField();
		SID.setText(student.getSID());
		SID.setBounds(152, 143, 201, 25);
		contentPane.add(SID);
		SID.setColumns(10);
		
		stuname = new JTextField();
		stuname.setText(student.Getname());
		stuname.setBounds(152, 107, 201, 25);
		contentPane.add(stuname);
		stuname.setColumns(10);
		
		stuid = new JTextField();
		stuid.setText(student.getStuid());
		stuid.setEnabled(false);
		stuid.setBounds(152, 69, 200, 25);
		contentPane.add(stuid);
		stuid.setColumns(10);
		
		JButton button = new JButton("\u4FDD\u5B58\u4FEE\u6539");
		button.addActionListener(new ActionListener() {
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
						|| stuname.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "请填写完整后提交");
				//调用修改方法
				}else{
					//新建学生对象
					Student student = new Student(stuid.getText(),stuname.getText(),SID.getText(),sex,stuage+"");
					try {
						//获取返回值存入数组
						String arr[]=StudentDao.changeMyData(student);
						//判断是否执行成功
						if(arr[0].equals("success")){
							dispose();
							JOptionPane.showMessageDialog(null, arr[1]);
							HomePage.main(user);
						}else{
							JOptionPane.showMessageDialog(null, arr[1]);
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		button.setBounds(59, 261, 350, 52);
		contentPane.add(button);
	}
}
