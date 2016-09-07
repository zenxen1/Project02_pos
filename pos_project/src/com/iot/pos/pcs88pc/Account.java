package com.iot.pos.pcs88pc;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Account extends JPanel implements ActionListener {
	
	JPanel p;
	JButton bt[] = new JButton[10];
	JButton equal, clear;
	JLabel label;
	
	public Account(){
		
		setLayout(new BorderLayout());
		p = new JPanel(new GridLayout(4,4,1,1));
		label = new JLabel("0",JLabel.RIGHT);
		equal = new JButton("=");
		clear = new JButton("C");
		
		for(int i=0; i<bt.length; i++){
			bt[i] = new JButton(Integer.toString(i));
	
		p.add(bt[i]);  
		p.add(clear); 
		p.add(equal);
		
	
		setBounds(200, 200, 300, 300);
		
		add(label,BorderLayout.NORTH);
		add(p, BorderLayout.CENTER);
		
        bt[i].addActionListener(this);		
		setSize(300,300);
		setVisible(true);
		
		}
		
	}
	
	/*public void showContent(int current){
		for(int i=0; i<bt.length; i++){
			if(i==current){
				bt[i].setVisible(true);
			}else
				bt[i].setVisible(false);
		}
	}*/
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj=e.getSource();//정보를 받아오기 위함
		for(int i=0; i<bt.length; i++){
			 
		}
		if(obj==bt[0]){
			System.out.println("0");
		}else if(obj==bt[1]){
			System.out.println("1");
		}else if(obj==bt[2]){
			System.out.println("2");
		}else if(obj==bt[3]){
			System.out.println("3");
		}else if(obj==bt[4]){
			System.out.println("4");
		}else if(obj==bt[5]){
			System.out.println("5");
		}else if(obj==bt[6]){
			System.out.println("6");
		}else if(obj==bt[7]){
			System.err.println("7");
		}else if(obj==bt[8]){
			System.out.println("8");
		}else if(obj==bt[9]){
			System.out.println("9");
		}
	}

}
