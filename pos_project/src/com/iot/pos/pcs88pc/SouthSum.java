package com.iot.pos.pcs88pc;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class SouthSum extends JPanel{
	//���� ����
	JPanel cost;
	JLabel sum, discount, result;
	JLabel p_sum, p_discount, p_result;
	JButton confirm, cancel;
	
	// ��������
	JPanel keyboard;
	
	//��������� ��ư
	JPanel p_east;
	JButton bt, bt1, bt2, bt3;
	
	
	public SouthSum(){
		setLayout(new BorderLayout());
		//����
		cost = new JPanel();
		sum = new JLabel("�հ�");
		discount = new JLabel("����");
		result = new JLabel("�Ѱ�");
		p_sum = new JLabel("1111");
		p_discount = new JLabel("111");
		p_result = new JLabel("111");
		confirm = new JButton("Ȯ��");
		cancel = new JButton("���");
		
		sum.setPreferredSize(new Dimension(90, 70));
		discount.setPreferredSize(new Dimension(90, 70));		
		result.setPreferredSize(new Dimension(90, 70));
		p_sum.setPreferredSize(new Dimension(300, 70));
		p_discount.setPreferredSize(new Dimension(300, 70));
		p_result.setPreferredSize(new Dimension(300, 70));
		
		cost.add(sum);
		cost.add(p_sum);
		cost.add(discount);
		cost.add(p_discount);
		cost.add(result);
		cost.add(p_result);
		cost.add(confirm);
		cost.add(cancel);
		
		//��������� ��ư
		p_east = new JPanel(new GridLayout(4, 1));
		bt =  new JButton("���������");
		bt1 = new JButton("���������");
		bt2 = new JButton("���������");
		bt3 = new JButton("���������");
		p_east.add(bt);
		p_east.add(bt1);
		p_east.add(bt2);
		p_east.add(bt3);
		
		p_east.setBackground(Color.RED);
		
		keyboard= new JPanel(new GridLayout(4,5));
		Button[] bt = new Button[20]; 
		for(int i=0; i<bt.length; i++){
			bt[i]=new Button(i+"1");	
			keyboard.add(bt[i]);
			
		}
	
		cost.setPreferredSize(new Dimension(400, 300));
		p_east.setPreferredSize(new Dimension(200, 200));
		keyboard.setPreferredSize(new Dimension(200, 300));
		add(cost,BorderLayout.WEST);
		add(p_east,BorderLayout.EAST);
		add(keyboard);
		
		setPreferredSize(new Dimension(980, 100));
	}
}

