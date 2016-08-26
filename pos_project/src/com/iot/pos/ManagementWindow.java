package com.iot.pos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ManagementWindow extends JFrame implements ActionListener{

	JPanel p, p2, p3, p4,p5;
	JButton bt;
	JButton[] bt_menu;
	JLabel[] la;
	String[] bt_title = {
			"매출관리/확인",
			"판매내역",
			"거래내역",
			"상품정보",
			"직원관리",
			"거래내역"
			};
	
	public ManagementWindow() {
		
		bt = new JButton("메인화면");
		bt_menu = new JButton[6];
		p = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		p4 = new JPanel();
		p5 = new JPanel();
		p.setPreferredSize(new Dimension(600, 450));
		p2.setPreferredSize(new Dimension(120, 100));
		p3.setPreferredSize(new Dimension(100, 100));
		p4.setPreferredSize(new Dimension(100, 100));
		p5.setPreferredSize(new Dimension(100, 100));
		for(int i = 0; i<bt_menu.length; i++){
			p.add(bt_menu[i] = new JButton(bt_title[i]));
			p.setLayout(new GridLayout(2, 3,30,30));
			bt_menu[i].setPreferredSize(new Dimension(150, 100));
			bt_menu[i].setFont(new Font("굴림", Font.BOLD, 15));
			bt_menu[i].addActionListener(this);

		}
		
		bt.setPreferredSize(new Dimension(100, 50));
		p2.add(bt);
		
		add(p2, BorderLayout.EAST);
		add(p, BorderLayout.CENTER);
		add(p3, BorderLayout.NORTH);
		add(p4, BorderLayout.WEST);
		add(p5, BorderLayout.SOUTH);
		
		
		setSize(1024, 768);
		setVisible(true);
		
	}
	
	public void goMain(){
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}
	
	public static void main(String[] args) {
		new ManagementWindow();
	}

}
