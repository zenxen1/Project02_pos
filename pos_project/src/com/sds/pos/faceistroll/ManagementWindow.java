package com.sds.pos.faceistroll;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class ManagementWindow extends JPanel implements ActionListener{
	JPanel p_all;
	JPanel p_center, p_east, p_north, p_west,p_south;
	JPanel p_row1, p_row2;
	public JButton bt;
	public JButton[] bt_menu;
	JLabel la_north;
	JLabel[] la;
	String[] bt_title = {
			"매출관리(상품별)",
			"매출관리(일자별)",
			"거래내역",
			"업체등록",
			"상품정보",
			"직원관리"
			};
	SalesManagement salesManagement;
	
	public ManagementWindow() {
		p_all = new JPanel();
		bt = new JButton("메인화면");
		bt_menu = new JButton[bt_title.length];
		la_north = new JLabel("           ");
		p_center = new JPanel();
		p_east = new JPanel();
		p_north = new JPanel();
		p_west = new JPanel();
		p_south = new JPanel();
		p_row1 = new JPanel();
		p_row2 = new JPanel();
		
		p_center.setPreferredSize(new Dimension(600, 450));
		p_center.setLayout(new FlowLayout());
		p_east.setPreferredSize(new Dimension(120, 100));
		p_north.setPreferredSize(new Dimension(100, 100));
		p_west.setPreferredSize(new Dimension(100, 100));
		p_south.setPreferredSize(new Dimension(100, 100));
		
		for(int i = 0; i<bt_menu.length; i++){
			bt_menu[i] = new JButton(bt_title[i]);
			//p.setLayout(new GridLayout(2, 3,30,30));
			
			p_center.add(bt_menu[i]);
			
			bt_menu[i].setPreferredSize(new Dimension(150, 100));
			bt_menu[i].setFont(new Font("굴림", Font.BOLD, 15));
			bt_menu[i].addActionListener(this);			
		}
	
		p_row1.add(bt_menu[3]);
		p_row1.add(bt_menu[4]);
		p_center.add(p_row1);
		
		p_row1.setPreferredSize(new Dimension(800, 250));
			
		bt.setPreferredSize(new Dimension(100, 50));
			
		la_north.setPreferredSize(new Dimension(850, 20));
		p_north.add(la_north);
		p_north.add(bt);
		
		p_all.setLayout(new BorderLayout());
		p_all.add(p_north, BorderLayout.NORTH);
		p_all.add(p_center, BorderLayout.CENTER);
		p_all.add(p_west, BorderLayout.WEST);
		p_all.add(p_east, BorderLayout.EAST);
		p_all.add(p_south, BorderLayout.SOUTH);
		
		add(p_all);
		bt.addActionListener(this);		
	}
	
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == bt){
			System.out.println("메인화면");
		}else if(obj == bt_menu[0]){
			System.out.println("매출관리/확인");
			salesManagement = new SalesManagement();
		}else if(obj == bt_menu[1]){
			System.out.println("판매내역");
		}else if(obj == bt_menu[2]){
			System.out.println("거래내역");
		}else if(obj == bt_menu[3]){
			System.out.println("상품정보");
		}else if(obj == bt_menu[4]){
			System.out.println("직원관리");
		}
	}
	
}
