package com.iot.pos.pcs88pc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SaleDeskTop_North extends JPanel{

	JPanel p_center;
	JLabel la_sale;
	JTextField tf_login, tf_watch;
	JButton bt_login, bt_watch;
	JButton bt_main;
	
	public SaleDeskTop_North(){
		setLayout(new BorderLayout());
		
		p_center = new JPanel();
		la_sale = new JLabel("판매화면");
		tf_login = new JTextField(15);
		tf_watch = new JTextField(15);
		bt_login = new JButton("로그인한사람");
		bt_watch = new JButton("시계");
		bt_main = new JButton("메인화면");
		
		add(la_sale,BorderLayout.WEST);
		add(bt_main,BorderLayout.EAST);
		
		p_center.setLayout(new FlowLayout(FlowLayout.LEFT,40,10));
		p_center.add(bt_login);
		p_center.add(tf_login);
		p_center.add(bt_watch);
		p_center.add(tf_watch);
		p_center.setPreferredSize(new Dimension(300, 100));
		add(p_center);
		
	}
}