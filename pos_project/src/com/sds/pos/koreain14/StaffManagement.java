package com.sds.pos.koreain14;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.iot.pos.PosMain;

public class StaffManagement extends JPanel implements ActionListener{
	
	// ����
	JPanel p_west;
	
	// ����
	JScrollPane scroll;
	JTable table;
	StaffTableModel model;
	
	// ����
	JPanel p_east;
	JLabel la_id, la_pwd, la_name, la_contact;
	JTextField t_id, t_name, t_contact;
	JPasswordField t_pwd;
	JButton bt_regist, bt_delete;
	public JButton bt_main;
	
	JLabel la1;
	
	// ����
	JPanel p_north;
	
	// ����
	JPanel p_south;
	
	// ��ü�г�
	JPanel container;
	
	public StaffManagement(){
		container = new JPanel();
		
		// ����
		p_west = new JPanel();
		p_west.setPreferredSize(new Dimension(50, 960));
		
		// ����
		model=new StaffTableModel();
		table = new JTable(model);
		scroll = new JScrollPane(table);
		
		// ����
		p_east = new JPanel();
		
		la_id = new JLabel("����");
		la_id.setPreferredSize(new Dimension(60, 20));
		t_id = new JTextField(10);
		la_pwd = new JLabel("��й�ȣ");
		la_pwd.setPreferredSize(new Dimension(60, 20));
		t_pwd = new JPasswordField(10);
		la_name = new JLabel("�̸�");
		la_name.setPreferredSize(new Dimension(60, 20));
		t_name = new JTextField(10);
		la_contact = new JLabel("����ó");
		la_contact.setPreferredSize(new Dimension(60, 20));
		bt_main = new JButton("����ȭ��");
		t_contact= new JTextField(10);
		
		la1 = new JLabel("");
		la1.setPreferredSize(new Dimension(60, 27));
		bt_regist = new JButton("���");
		bt_regist.setPreferredSize(new Dimension(60, 27));
		bt_delete = new JButton("����");
		bt_delete.setPreferredSize(new Dimension(60, 27));
		
		p_east.setPreferredSize(new Dimension(210, 960));
		
		p_east.add(la_id);
		p_east.add(t_id);
		p_east.add(la_pwd);
		p_east.add(t_pwd);
		p_east.add(la_name);
		p_east.add(t_name);
		p_east.add(la_contact);
		p_east.add(t_contact);
		
		p_east.add(la1);
		p_east.add(bt_regist);
		p_east.add(bt_delete);
		p_east.add(bt_main);
		
		setLayout(new BorderLayout());
		
		// ��ư�� ������ ����
		bt_regist.addActionListener(this);
		
		// ����
		p_south = new JPanel();
		p_south.setPreferredSize(new Dimension(1024, 100));
		
		add(p_west, BorderLayout.WEST);
		add(scroll);
		add(p_east, BorderLayout.EAST);
		add(p_south, BorderLayout.SOUTH);
		
		
	}

	public void registUser(){
		Connection con=PosMain.getConnection();
		PreparedStatement pstmt=null;
		
		String sql="insert into pos_user(user_id, user_account, user_password, user_name, user_phone)";
		sql=sql + " values(seq_user.nextval, ?,?,?,?)";
		
		try {
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1, t_id.getText());
			pstmt.setString(2, t_pwd.getPassword().toString());
			pstmt.setString(3, t_name.getText());
			pstmt.setString(4, t_contact.getText());
			
			if(t_id.getText().equals("")){
				JOptionPane.showMessageDialog(this, "���̵� �Է��ϼ���");
			} else if(t_pwd.getPassword().equals("")){
				JOptionPane.showMessageDialog(this, "��й�ȣ�� �Է��ϼ���");
			} else if(t_name.getText().equals("")){
				JOptionPane.showMessageDialog(this, "�̸��� �Է��ϼ���");
			} else if(t_contact.getText().equals("")){
				JOptionPane.showMessageDialog(this, "����ó�� �Է��ϼ���");
			} else{
				int result=pstmt.executeUpdate();

				if(result!=0){
					JOptionPane.showMessageDialog(this, "ȸ������� �Ϸ�Ǿ����ϴ�.");
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		registUser();
	}
}
