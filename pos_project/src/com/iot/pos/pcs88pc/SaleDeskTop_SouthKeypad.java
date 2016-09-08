package com.iot.pos.pcs88pc;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SaleDeskTop_SouthKeypad extends JPanel implements ActionListener {
	
	JPanel p;
	JButton bt[] = new JButton[10];
	JButton equal, clear;
	//JLabel label;
	StringBuffer sb = new StringBuffer();
	SaleDeskTop_South deskTop_South;
	
	public SaleDeskTop_SouthKeypad(SaleDeskTop_South deskTop_South){
		this.deskTop_South=deskTop_South;
		
		setLayout(new BorderLayout());
		p = new JPanel(new GridLayout(4,4,1,1));
		//label = new JLabel("0",JLabel.RIGHT);
		equal = new JButton("Enter");
		clear = new JButton("C");
		
		for(int i=0; i<bt.length; i++){
			bt[i] = new JButton(Integer.toString(i));
	
		p.add(bt[i]);  
		p.add(clear); 
		p.add(equal);
		
		
		//setBounds(200, 200, 300, 300);
		
		//add(label,BorderLayout.NORTH);
		add(p, BorderLayout.CENTER);
		
        bt[i].addActionListener(this);	
        clear.addActionListener(this);
		//bsetSize(300,300);
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
			//System.out.println("0");
			sb.append(0);
		}else if(obj==bt[1]){
			sb.append(1);
			//System.out.println("1");
		}else if(obj==bt[2]){
			sb.append(2);
			//System.out.println("2");
		}else if(obj==bt[3]){
			//System.out.println("3");
			sb.append(3);
		}else if(obj==bt[4]){
			//System.out.println("4");
			sb.append(4);
		}else if(obj==bt[5]){
			//System.out.println("5");
			sb.append(5);
		}else if(obj==bt[6]){
			//System.out.println("6");
			sb.append(6);
		}else if(obj==bt[7]){
			//System.err.println("7");
			sb.append(7);
		}else if(obj==bt[8]){
			//System.out.println("8");
			sb.append(8);
		}else if(obj==bt[9]){
			//System.out.println("9");
			sb.append(9);
		}else if(obj.equals(clear)){
			
			sb.delete(0, 20);
		}
		deskTop_South.tf_barcord.setText(sb.toString());
		
	}

}
