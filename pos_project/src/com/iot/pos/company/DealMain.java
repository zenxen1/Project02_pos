package com.iot.pos.company;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DealMain extends JFrame{
	String[] menuTitle ={"업체등록","매출관리"};
	JButton[] menu;
	JPanel p_north;
	JPanel[] content;
	JPanel center;
	
	public DealMain() {
		menu = new JButton[2];
		content = new JPanel[menu.length];
		
		p_north = new JPanel();
		p_north.setLayout(new GridLayout(1, menu.length));
		
		for(int i=0;i<menu.length;i++){
			menu[i] = new JButton(menuTitle[i]);
			p_north.add(menu[i]);
		}
		
		content[0] = new Saleconfirm();
		content[1] = new DealcomRegist();
		
		center = new JPanel();
		
		add(p_north,BorderLayout.NORTH);
		
		/*
		center.add(content[0]);
		content[0].setPreferredSize(new Dimension(1024, 960));
		content[0].setVisible(true);
		*/
		center.add(content[1]);
		content[1].setPreferredSize(new Dimension(1024, 960));
		content[1].setVisible(true);
		
		add(center);
		
		//showContent(0);
		setSize(1024,960);
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new DealMain();
	}

}
