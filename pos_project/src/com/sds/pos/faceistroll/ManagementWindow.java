package com.sds.pos.faceistroll;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.iot.pos.PosMain;

public class ManagementWindow extends JPanel implements ActionListener{
	JPanel p_all;
	JPanel p_center, p_east, p_north, p_west,p_south;
	JPanel p_row1, p_row2;
	public JButton bt_main;
	public JButton[] bt_menu;
	
	JLabel la_img, la_empty;
	ImageIcon icon;
	
	JLabel la_north;
	JLabel[] la;
	String[] bt_title = {
			"품별매출관리",
			"날짜별매출관리",
			"거래내역",
			"업체등록",
			"상품정보",
			"직원관리"
			};
	SalesManagement salesManagement;
	PosMain posMain;
	
	public ManagementWindow(PosMain posMain) {
		this.posMain=posMain;
		
		p_all = new JPanel();
		p_all.setBackground(new Color(123, 182, 212));
		
		bt_main = new JButton("메인화면");
		bt_menu = new JButton[bt_title.length];
		
		la_north = new JLabel("");
		
		p_center = new JPanel();
		p_east = new JPanel();
		p_north = new JPanel();
		p_west = new JPanel();
		p_south = new JPanel();
		
		// #이미지버튼 넣기#
		icon=new ImageIcon("C:/project_rev/pos_project/res/home2.png");
		Image homeImage=icon.getImage();
		Image newHomeImg=homeImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		icon = new ImageIcon(newHomeImg);
		
		la_img = new JLabel(icon);
		la_empty = new JLabel("");
		la_empty.setPreferredSize(new Dimension(60, 50));
		
		
		p_row1 = new JPanel();
		p_row2 = new JPanel();
		p_row1.setBackground(new Color(123, 182, 212));
		p_row2.setBackground(new Color(123, 182, 212));
		
		p_center.setPreferredSize(new Dimension(1024, 450));
		p_center.setBackground(new Color(123,182,212));
		p_center.setLayout(new FlowLayout());
		
		p_east.setPreferredSize(new Dimension(110, 860));
		p_east.setBackground(new Color(123,182,212));
		
		p_north.setPreferredSize(new Dimension(1024, 300));
		p_north.setBackground(new Color(123,182,212));
		
		p_west.setPreferredSize(new Dimension(100, 860));
		p_west.setBackground(new Color(123,182,212));
		
		p_south.setPreferredSize(new Dimension(100, 100));
		p_south.setBackground(new Color(123,182,212));
		
		for(int i = 0; i<bt_menu.length; i++){
			bt_menu[i] = new JButton(bt_title[i]);
			
			p_center.add(bt_menu[i]);
			
			bt_menu[i].setPreferredSize(new Dimension(160, 130));
			bt_menu[i].setFont(new Font("맑은 고딕", Font.BOLD, 15));
			bt_menu[i].setBackground(new Color(160,206,222));
			bt_menu[i].setForeground(Color.white);
			bt_menu[i].addActionListener(this);			
		}
	
		p_row1.add(bt_menu[3]);
		p_row1.add(bt_menu[4]);
		p_row1.add(bt_menu[5]);
		
		// #홈버튼 붙이기#
		p_row1.add(la_north);
		p_row1.add(la_empty);
		p_row1.add(la_img);
		
		p_center.add(p_row1);
		
		p_row1.setPreferredSize(new Dimension(800, 500));
			
		
		//#북쪽 화면 구성#
		la_north.setPreferredSize(new Dimension(900, 200));

		//p_north.add(la_north);
		//p_north.add(la_img);
		
		// #남쪽에 홈버튼 구성#
		//p_south.add(la_north);
		//p_south.add(la_img);
		
		//#동쪽 화면구성#
		
		p_all.setLayout(new BorderLayout());
		p_all.add(p_north, BorderLayout.NORTH);
		p_all.add(p_center, BorderLayout.CENTER);
		p_all.add(p_west, BorderLayout.WEST);
		p_all.add(p_east, BorderLayout.EAST);
		p_all.add(p_south, BorderLayout.SOUTH);
		
		//add(p_south,BorderLayout.SOUTH);
		
		// 홈버튼 구성!
		add(p_all);
		la_img.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				posMain.showContent(1);
			}
		});
	}
	
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == bt_main){
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
