package com.iot.pos;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FirstLogin extends JFrame implements ActionListener{

	JPanel p_east;
	JLabel la_number, la_company, la_id, la_pw;
	JTextField t_number, t_id, t_pw;
	JButton bt;
	
	public FirstLogin(){
		p_east = new JPanel();
		
		la_number = new JLabel("사업자 등록번호 :");
		la_company = new JLabel("IOT Pos Project Team");
		la_id = new JLabel("ID");
		la_pw = new JLabel("Pw");
		
		t_number = new JTextField(20);
		t_id = new JTextField(30);
		t_pw = new JTextField(30);
		
		bt = new JButton("로그인");
		
	/*	la_number.setBounds(180, 10, 100, 50);
		la_company.setBounds(10, 50, 100, 20);
		la_id.setBounds(150, 50, 200, 20);
		la_pw.setBounds(10, 100, 100, 20);
		t_id.setBounds(150, 100, 200, 20);
		t_pw.setBounds(50, 150, 100, 50);
		bt.setBounds(160, 150, 100, 50);*/
		
		p_east.add(la_number);
		p_east.add(t_number);
		p_east.add(la_company);
		p_east.add(la_id);
		p_east.add(t_id);
		p_east.add(la_pw);
		p_east.add(t_pw);
		p_east.add(bt);
		
		bt.addActionListener(this);
		
		p_east.setPreferredSize(new Dimension(500,800));
		add(p_east, BorderLayout.EAST);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400,300);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("로그인을 하였습니다.");
		
	}
	
	
	public static void main(String[] args) {
		new FirstLogin();
	}

}
