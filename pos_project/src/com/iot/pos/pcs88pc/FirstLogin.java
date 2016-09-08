package com.iot.pos.pcs88pc;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.iot.pos.PosMain;

public class FirstLogin extends JPanel implements ActionListener{
	JPanel p_center;
	JLabel la_number, la_company, la_id, la_pw, la_empty, la_empty2, la_empty3, la_empty4;
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

	ArrayList<String[]> loginlist;
	
	public String loginname;
	
	public FirstLogin(PosMain main){
		this.main = main;
	
		setLayout(new FlowLayout(FlowLayout.LEFT,330,250));
		p_center = new JPanel();
		
		// #���� ����#
		la_company = new JLabel("IOT Pos Project Team", SwingConstants.CENTER);
		la_company.setFont(new Font("�������", Font.BOLD, 20));
		la_company.setForeground(Color.white);
		
		// #�α���ȭ�� ����#
		la_id = new JLabel("���̵�     ");
		la_id.setHorizontalAlignment(SwingConstants.RIGHT);
		la_id.setFont(new Font("���� ���", Font.BOLD, 15));
		la_id.setPreferredSize(new Dimension(50, 50));
		
		la_pw = new JLabel("��й�ȣ  ");
		la_pw.setHorizontalAlignment(SwingConstants.RIGHT);
		la_pw.setFont(new Font("���� ���", Font.BOLD, 15));
		la_pw.setPreferredSize(new Dimension(50, 50));
		
		// #���� �ִ� ��#
		la_empty = new JLabel("");
		la_empty.setPreferredSize(new Dimension(350, 30));
		la_empty2 = new JLabel("");
		la_empty2.setPreferredSize(new Dimension(350, 10));
		la_empty3 = new JLabel("");
		la_empty3.setPreferredSize(new Dimension(350, 10));
		la_empty4 = new JLabel("");
		la_empty4.setPreferredSize(new Dimension(220, 10));
		
		//t_number = new JTextField(20);
		t_id = new JTextField(18);
		t_pw = new JTextField(18);
		
		bt = new JButton("�α���");

		loginlist = new ArrayList<String[]>();

		bt.setBackground(new Color(183, 197, 200));
		bt.setFont(new Font("���� ���", Font.PLAIN, 12));
		bt.setForeground(Color.white);
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
		
		p_center.setBackground(new Color(160,206,222));
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
		
		
		// #��, ��ư ���̱�!!#
		p_center.add(la_empty);
		p_center.add(la_id);
		p_center.add(t_id);
		p_center.add(la_empty2);
		p_center.add(la_pw);
		p_center.add(t_pw);
		p_center.add(la_empty3);
		p_center.add(la_empty4);
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
			loginlist.removeAll(loginlist);

			if(rs.next()){
				String[] record = new String[2];
				record[0] = Integer.toString(rs.getInt("user_id"));
				record[1] = rs.getString("user_name");
				
				loginlist.add(record);
				loginname = record[1];
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
