package com.iot.pos.pcs88pc;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class CenterGrid extends JPanel{
	JPanel p_north, p_center;
	JLabel la;
	JButton bt;
	
	public CenterGrid(){
		setLayout(new BorderLayout());
		p_north = new JPanel();
		p_center = new JPanel();
		la = new JLabel("磊林静绰 前格");
		bt = new JButton("祈笼");
		p_north.add(la);
		p_north.add(bt);
		
		p_center.setLayout(new GridLayout(3,3));
		
		Button[] bt = new Button[9]; 
		for(int i=0; i<bt.length; i++){
			bt[i]=new Button("惑前前格");	
			p_center.add(bt[i]);
		
		}
		
		add(p_north,BorderLayout.NORTH);
		add(p_center);
	}
}
