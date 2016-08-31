package com.sds.pos.faceistroll;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class OftenUsedProduct extends JFrame implements ActionListener{
	
	Choice ch, ch_sub;
	JTextField txt_search;
	JButton bt_search, bt_regist;
	JPanel p_north, p_center, p_west, p_east, p_south;

	OftenUsedTableModel info;
	JTable t_info;
	JScrollPane scroll;

	
	public OftenUsedProduct() {
		ch = new Choice();
		ch_sub = new Choice();
		txt_search = new JTextField(25);
		bt_search = new JButton("�˻�");
		bt_regist = new JButton("���");
		info = new OftenUsedTableModel();
		t_info = new JTable(info);
		scroll = new JScrollPane(t_info);
		p_north = new JPanel();
		p_east = new JPanel();
		p_south = new JPanel();
		p_west = new JPanel();
		p_center = new JPanel();
		
		ch.add("�����з�");
		ch.add("����Ļ�");
		ch.add("�Ｎ�丮");
		ch.add("����");
		ch.add("���̽�ũ��");
		ch.add("��ǰ");
		ch.add("����");
		ch.add("��Ȱ��ǰ");
		
		ch_sub.add("�����з�");
		
		
		txt_search.setPreferredSize(new Dimension(400, 29));
		
		p_north.add(ch);
		p_north.add(ch_sub);
		p_north.add(txt_search);
		p_north.add(bt_search);
		p_north.add(bt_regist);
		
		add(p_north, BorderLayout.NORTH);
		add(scroll, BorderLayout.CENTER);
		add(p_south, BorderLayout.SOUTH);
		add(p_east, BorderLayout.EAST);
		add(p_west, BorderLayout.WEST);
		
		bt_search.addActionListener(this);
		bt_regist.addActionListener(this);	
		
		
		setSize(1028, 768);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		
	}
	
	//�˻�
	public void searchProduct(){
		
		
		
	}
	
	
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == bt_search){
			System.out.println("�˻�");
		}else if(obj == bt_regist){
			System.out.println("���");
		}
	}
	
	public static void main(String[] args) {
		new OftenUsedProduct();
	}


}
