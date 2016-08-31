package com.iot.pos.pcs88pc;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

public class SaleDeskTop extends JFrame {
	
	SaleDestTop_North p_north;
	SaleDeskTop_Center p_center;

	public SaleDeskTop(){
		p_north = new SaleDestTop_North();
		p_center = new SaleDeskTop_Center();
		
		p_north.setPreferredSize(new Dimension(1024,50));
		add(p_north,BorderLayout.NORTH);
		
		p_center.setPreferredSize(new Dimension(1024,500));
		add(p_center,BorderLayout.WEST);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1024,960);
		setVisible(true);
	}
	
	public static void main(String[] args){
		new SaleDeskTop();
		
	}
}
