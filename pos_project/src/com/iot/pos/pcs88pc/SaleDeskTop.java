package com.iot.pos.pcs88pc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SaleDeskTop extends JPanel {
	
	SaleDeskTop_North p_north;
	SaleDeskTop_Center p_center;
	SouthSum sum;
	public JButton bt_main;

	public SaleDeskTop(){
		p_north = new SaleDeskTop_North();
		
		bt_main = p_north.bt_main; 
		
		p_center = new SaleDeskTop_Center();
		sum = new SouthSum();
		
		p_north.setPreferredSize(new Dimension(980,50));
		add(p_north,BorderLayout.NORTH);
		
		p_center.setPreferredSize(new Dimension(980,500));
		add(p_center);
		
	
		sum.setPreferredSize(new Dimension(980, 300));
		add(sum,BorderLayout.SOUTH);
		
		
		//setDefaultCloseOperation(EXIT_ON_CLOSE); ï¿½Ç³Ú·ï¿½ ï¿½Ù²ï¿½ï¿½ï¿½ï¿½Ç·ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½Ê¿ä°¡ ï¿½ï¿½ï¿½ï¿½.
		this.setPreferredSize(new Dimension(1000, 950));
		setBackground(Color.CYAN);
		setVisible(true);
	}
	
	/*
	public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.add(new SaleDeskTop());
		System.out.println("ÆÇ¸Å°è»ê±â È®ÀÎ ");
		frame.setSize(1024, 960);
		frame.setVisible(true);
	}
	*/
}
