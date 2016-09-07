package com.iot.pos.pcs88pc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.iot.pos.PosMain;

public class FirstLogin extends JPanel implements ActionListener{
	JPanel p_center;
	JLabel la_number, la_company, la_id, la_pw;
	JTextField t_number, t_id, t_pw;
	JButton bt;
	Dimension dim;
	Dimension dim2;
	PosMain main;
	
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="posman";
	String password="posman";
	
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public FirstLogin(PosMain main){
		this.main = main;
	
		setLayout(new FlowLayout(FlowLayout.LEFT,180,300));
		p_center = new JPanel();
		//p_center.setLayout(null);
		//la_number = new JLabel("����� ��Ϲ�ȣ :");
		la_company = new JLabel("IOT Pos Project Team");
		la_id = new JLabel("ID");
		la_pw = new JLabel("Pw");
		
		//t_number = new JTextField(20);
		t_id = new JTextField(20);
		t_pw = new JTextField(20);
		
		bt = new JButton("�α���");
		dim = new Dimension(120, 30);
		dim2 = new Dimension(260, 30);
		la_company.setPreferredSize(new Dimension(380, 50));
		//la_number.setPreferredSize(dim);
		la_id.setPreferredSize(dim);
		la_pw.setPreferredSize(dim);
		//t_number.setPreferredSize(dim2);
		t_id.setPreferredSize(dim2);
		t_pw.setPreferredSize(dim2);
		
		p_center.setPreferredSize(new Dimension(400,300));
		
		p_center.setBackground(Color.CYAN);
		/*
		la_number.setBounds(180, 10, 100, 50);
		la_company.setBounds(10, 50, 100, 20);
		la_id.setBounds(150, 50, 200, 20);
		la_pw.setBounds(10, 100, 100, 20);
		t_id.setBounds(150, 100, 200, 20);
		t_pw.setBounds(50, 150, 100, 50);
		bt.setBounds(160, 150, 100, 50);
		*/
	
		p_center.add(la_company);
		//p_center.add(la_number);
		//p_center.add(t_number);
		
		p_center.add(la_id);
		p_center.add(t_id);
		p_center.add(la_pw);
		p_center.add(t_pw);
		p_center.add(bt);
		
		//�α��ι�ư ����
		bt.addActionListener(this);
		
		add(p_center);
		
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		//setResizable(false);
		setSize(1024,960);
		setVisible(true);
	}
	/*
	//DB����
	public void ConnectDB(){	
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			this.setTitle("���Ӽ���");
			con=DriverManager.getConnection(url, user, password);
				if(con==null){
				JOptionPane.showMessageDialog(this, "���� ����!!");
				return;
			}
				this.setTitle("���Ӽ���");
		} catch (SQLException e){	
			e.printStackTrace();
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		}
	}	
	*/
	
	//�α��� �ϱ� 
	public void PosUser(){
			Connection con = PosMain.getConnection();
		try {
			
			String sql="select * from pos_user where user_acount=? and user_password=?";
			pstmt=con.prepareStatement(sql);
			
			//1���� ���̵� �� , 2���� ��й�ȣ ������ ���� 
			pstmt.setString(1, t_id.getText());  //���̽��� �ƴϿ��� get���� �޾ƿ� �� ����.
			pstmt.setString(2, t_pw.getText());
		
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				System.out.println("�α��� ����");
				main.showContent(1); //�α��� ������ ����ȭ������ �̵� 
				//dispose();
			}else{
				System.out.println("�α��� ����");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}   
	}
	
	public void actionPerformed(ActionEvent e) {
		PosUser();
		
		
	}
	/*
	public static void main(String[] args) {
		new FirstLogin();
	}
	*/

}
