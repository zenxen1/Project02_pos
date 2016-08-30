package com.iot.pos;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.naming.spi.DirStateFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class EmployeeManagement extends JFrame {
	// ����
	JPanel p_west;
	
	// ����
	JScrollPane scroll;
	JTable table;
	EmployeeTableModel model;
	
	// ����
	JPanel p_east;
	JLabel la_id, la_pwd, la_name, la_contact;
	JTextField t_id, t_name, t_contact;
	JPasswordField t_pwd;
	JButton bt_regist, bt_delete;
	
	JLabel la1;
	
	// ����
	JPanel p_north;
	
	// ����
	JPanel p_south;
	
	public EmployeeManagement() {
		// ����
		p_north = new JPanel();
		p_north.setPreferredSize(new Dimension(1024, 100));
		// ����
		p_west = new JPanel();
		p_west.setPreferredSize(new Dimension(50, 960));
		
		// ����
		model=new EmployeeTableModel();
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
		
		// ����
		p_south = new JPanel();
		p_south.setPreferredSize(new Dimension(1024, 100));
		
		add(p_north, BorderLayout.NORTH);
		add(p_west, BorderLayout.WEST);
		add(scroll);
		add(p_east, BorderLayout.EAST);
		add(p_south, BorderLayout.SOUTH);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1024, 960);
		setVisible(true);
	}

	public static void main(String[] args) {
		new EmployeeManagement();
	}

}
