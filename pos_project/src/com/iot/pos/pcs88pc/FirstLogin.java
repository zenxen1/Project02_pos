package com.iot.pos.pcs88pc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FirstLogin extends JFrame implements ActionListener{
	
	JPanel p_center;
	JLabel la_number, la_company, la_id, la_pw;
	JTextField t_number, t_id, t_pw;
	JButton bt;
	Dimension dim;
	Dimension dim2;
	public FirstLogin(){
		setLayout(new FlowLayout(FlowLayout.CENTER,180,300));
		p_center = new JPanel();
		//p_center.setLayout(null);
		la_number = new JLabel("사업자 등록번호 :");
		la_company = new JLabel("IOT Pos Project Team");
		la_id = new JLabel("ID");
		la_pw = new JLabel("Pw");
		
		t_number = new JTextField(20);
		t_id = new JTextField(20);
		t_pw = new JTextField(20);
		
		bt = new JButton("로그인");
		dim = new Dimension(120, 30);
		dim2 = new Dimension(260, 30);
		la_company.setPreferredSize(new Dimension(380, 50));
		la_number.setPreferredSize(dim);
		la_id.setPreferredSize(dim);
		la_pw.setPreferredSize(dim);
		t_number.setPreferredSize(dim2);
		t_id.setPreferredSize(dim2);
		t_pw.setPreferredSize(dim2);
		
		p_center.setPreferredSize(new Dimension(400,300));
		
		p_center.setBackground(Color.CYAN);
		/*
		la_number.setBounds(180, 10, 100, 50);
		la_company.setBounds(10, 50, 100, 20);
		la_id.setBounds(150, 50, 200, 20);
		la_pw.setBounds(10, 100, 100, 20);
		t_id.setBounds(150, 100, 200, 20);
		t_pw.setBounds(50, 150, 100, 50);
		bt.setBounds(160, 150, 100, 50);
		*/
	
		p_center.add(la_company);
		p_center.add(la_number);
		p_center.add(t_number);
		
		p_center.add(la_id);
		p_center.add(t_id);
		p_center.add(la_pw);
		p_center.add(t_pw);
		p_center.add(bt);
		
		bt.addActionListener(this);
		
		
		add(p_center);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1024,960);
		setResizable(false);
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
