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
	//남쪽 계산기
	JPanel cost;
	JLabel sum, discount, result;
	JLabel p_sum, p_discount, p_result;
	JButton confirm, cancel;
	
	// 계산기좌판
	JPanel keyboard;
	
	//사용자정의 버튼
	JPanel p_east;
	JButton bt, bt1, bt2, bt3;
	
	
	public SouthSum(){
		setLayout(new BorderLayout());
		//계산기
		cost = new JPanel();
		sum = new JLabel("합계");
		discount = new JLabel("할인");
		result = new JLabel("총계");
		p_sum = new JLabel("1111");
		p_discount = new JLabel("111");
		p_result = new JLabel("111");
		confirm = new JButton("확인");
		cancel = new JButton("취소");
		
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
		
		//사용자정의 버튼
		p_east = new JPanel(new GridLayout(4, 1));
		bt =  new JButton("사용자정의");
		bt1 = new JButton("사용자정의");
		bt2 = new JButton("사용자정의");
		bt3 = new JButton("사용자정의");
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

