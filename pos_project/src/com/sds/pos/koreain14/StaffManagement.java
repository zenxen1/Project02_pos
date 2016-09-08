package com.sds.pos.koreain14;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.ImageIcon;
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
	PosMain posmain;
	
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
	
	JLabel la1, la_empty;
	
	// ����
	JPanel p_north;
	
	// ����
	JPanel p_south;
	
	// ��ü�г�
	JPanel container;
	
	// �̹���, ������
	ImageIcon icon;
	JLabel la_img;
	
	public StaffManagement(	PosMain posmain){
		this.posmain = posmain;
		
		container = new JPanel();
		
		// ����
		la_empty = new JLabel("");
		
		
		p_north = new JPanel();
		p_north.setPreferredSize(new Dimension(760, 60));
		p_north.setBackground(new Color(123,182,212));
		
		
		// ����
		p_west = new JPanel();
		p_west.setPreferredSize(new Dimension(50, 960));
		p_west.setBackground(new Color(123,182,212));
		
		// ����
		model=new StaffTableModel(this);
		table = new JTable(model);
		scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(700, 400));
		
		
		// ����
		p_east = new JPanel();
		
		la_id = new JLabel("����");
		la_id.setFont(new Font("���� ���", Font.BOLD, 13));
		la_id.setPreferredSize(new Dimension(60, 20));
		t_id = new JTextField(10);
		la_pwd = new JLabel("��й�ȣ");
		la_pwd.setFont(new Font("���� ���", Font.BOLD, 13));
		la_pwd.setPreferredSize(new Dimension(60, 20));
		t_pwd = new JPasswordField(10);
		la_name = new JLabel("�̸�");
		la_name.setFont(new Font("���� ���", Font.BOLD, 13));
		la_name.setPreferredSize(new Dimension(60, 20));
		t_name = new JTextField(10);
		la_contact = new JLabel("����ó");
		la_contact.setFont(new Font("���� ���", Font.BOLD, 13));
		la_contact.setPreferredSize(new Dimension(60, 20));
		t_contact= new JTextField(10);
		
		la1 = new JLabel("");
		la1.setPreferredSize(new Dimension(60, 27));
		
		bt_regist = new JButton("���");
		bt_regist.setBackground(new Color(183, 197, 200));
		bt_regist.setFont(new Font("���� ���", Font.PLAIN, 12));
		bt_regist.setForeground(Color.white);
		
		bt_delete = new JButton("����");
		bt_delete.setBackground(new Color(183, 197, 200));
		bt_delete.setFont(new Font("���� ���", Font.PLAIN, 12));
		bt_delete.setForeground(Color.white);
		
		bt_main = new JButton("��������");
		
		bt_regist.addActionListener(this);
		bt_delete.addActionListener(this);
		
		bt_regist.setPreferredSize(new Dimension(60, 27));
		bt_delete.setPreferredSize(new Dimension(60, 27));
		bt_main.setPreferredSize(new Dimension(60, 27));
		
		p_east.setPreferredSize(new Dimension(210, 960));
		p_east.setBackground(new Color(123,182,212));
		
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
		
		setLayout(new BorderLayout());
		
		// ��ư�� ������ ����
		bt_regist.addActionListener(this);
		
		// ����
		p_south = new JPanel();
		//p_south.add(bt_main);
		la_empty.setPreferredSize(new Dimension(50, 1200));
		p_south.setPreferredSize(new Dimension(1024, 750));
		p_south.setBackground(new Color(123,182,212));
		
		// #�̹�����ư �ֱ�#
		icon=new ImageIcon("C:/project_rev/pos_project/res/home2.png");
		Image homeImage=icon.getImage();
		Image newHomeImg=homeImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		icon = new ImageIcon(newHomeImg);
		
		la_img = new JLabel(icon);
		
		// �̹����� ���콺 ����
		la_img.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				posmain.showContent(1);
			}
		});
				
		// ����
		/*p_north.add(la_img);*/
		
		// ���ʿ� �ֱ�
		p_south.add(la_empty);
		p_south.add(la_img);
		
		add(p_west, BorderLayout.WEST);
		add(scroll);
		add(p_east, BorderLayout.EAST);
		add(p_south, BorderLayout.SOUTH);
		add(p_north, BorderLayout.NORTH);
		
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
					
					model.getUsers();
				//	table.removeAll();
					model.fireTableDataChanged();
					table.updateUI();
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
	
	public void resetTxtbox(){
		t_id.setText("");
		t_pwd.setText("");
		t_name.setText("");
		t_contact.setText("");
		
	}
	
	public void actionPerformed(ActionEvent e) {
		Object obj=e.getSource();
		if(obj==bt_regist){
			registUser();
			resetTxtbox();
		} else if(obj==bt_delete){
			model.deleteUsers();
			model.getUsers();
			model.fireTableDataChanged();
			table.updateUI();
		} 
	}
}
