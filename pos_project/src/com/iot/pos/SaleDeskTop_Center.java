package com.iot.pos;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class SaleDeskTop_Center extends JPanel {
	
	JPanel p, p1;
	JLabel la_name;
	JButton bt;
	CenterTableModel model;
	JScrollPane scroll;
	
	public SaleDeskTop_Center(){
		
		setLayout(new BorderLayout());
		
		p = new JPanel();
		p1 = new JPanel();
		la_name = new JLabel("자주쓰는 목록");
		bt = new JButton("편집");
		
		JTable table = new JTable(model=new CenterTableModel());
		p.add(table,BorderLayout.WEST);
		p.setPreferredSize(new Dimension(400,500));
		scroll = new JScrollPane(table);
		
		add(p);
		
		setLayout(new GridLayout(3,3));
		Button[] bt_product = new Button[9];
		
		for(int i=0; i<bt_product.length; i++){
			bt_product[i]=new Button("상품 \n품목");
			bt_product[i].setPreferredSize(new Dimension(100,100));
			p1.add(bt_product[i],BorderLayout.EAST);
			
			
		}
		
		
	
		
	}
	
	public static void main(String[] args){
		new SaleDeskTop_Center();
	}
}
