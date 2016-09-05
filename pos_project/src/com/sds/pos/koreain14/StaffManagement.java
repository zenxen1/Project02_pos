package com.sds.pos.koreain14;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.iot.pos.PosMain;

public class StaffManagement extends JPanel implements ActionListener{
	
	// 서쪽
	JPanel p_west;
	
	// 센터
	JScrollPane scroll;
	JTable table;
	StaffTableModel model;
	
	// 동쪽
	JPanel p_east;
	JLabel la_id, la_pwd, la_name, la_contact;
	JTextField t_id, t_name, t_contact;
	JPasswordField t_pwd;
	JButton bt_regist, bt_delete;
	public JButton bt_main;
	
	JLabel la1;
	
	// 북쪽
	JPanel p_north;
	
	// 남쪽
	JPanel p_south;
	
	// 전체패널
	JPanel container;
	
	public StaffManagement(){
		container = new JPanel();
		
		// 서쪽
		p_west = new JPanel();
		p_west.setPreferredSize(new Dimension(50, 960));
		
		// 센터
		model=new StaffTableModel();
		table = new JTable(model);
		scroll = new JScrollPane(table);
		
		// 동쪽
		p_east = new JPanel();
		
		la_id = new JLabel("계정");
		la_id.setPreferredSize(new Dimension(60, 20));
		t_id = new JTextField(10);
		la_pwd = new JLabel("비밀번호");
		la_pwd.setPreferredSize(new Dimension(60, 20));
		t_pwd = new JPasswordField(10);
		la_name = new JLabel("이름");
		la_name.setPreferredSize(new Dimension(60, 20));
		t_name = new JTextField(10);
		la_contact = new JLabel("연락처");
		la_contact.setPreferredSize(new Dimension(60, 20));
		bt_main = new JButton("메인화면");
		t_contact= new JTextField(10);
		
		la1 = new JLabel("");
		la1.setPreferredSize(new Dimension(60, 27));
		bt_regist = new JButton("등록");
		bt_regist.setPreferredSize(new Dimension(60, 27));
		bt_delete = new JButton("삭제");
		bt_delete.setPreferredSize(new Dimension(60, 27));
		
		p_east.setPreferredSize(new Dimension(210, 960));
		
		p_east.add(la_id);
		p_east.add(t_id);
		p_east.add(la_pwd);
		p_east.add(t_pwd);
		p_east.add(la_name);
		p_east.add(t_name);
		p_east.add(la_contact);
		p_east.add(t_contact);
		
		p_east.add(la1);
		p_east.add(bt_regist);
		p_east.add(bt_delete);
		p_east.add(bt_main);
		
		setLayout(new BorderLayout());
		
		// 버튼과 리스너 연결
		bt_regist.addActionListener(this);
		
		// 남쪽
		p_south = new JPanel();
		p_south.setPreferredSize(new Dimension(1024, 100));
		
		add(p_west, BorderLayout.WEST);
		add(scroll);
		add(p_east, BorderLayout.EAST);
		add(p_south, BorderLayout.SOUTH);
		
		
	}

	public void registUser(){
		Connection con=PosMain.getConnection();
		PreparedStatement pstmt=null;
		
		String sql="insert into pos_user(user_id, user_account, user_password, user_name, user_phone)";
		sql=sql + " values(seq_user.nextval, ?,?,?,?)";
		
		try {
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1, t_id.getText());
			pstmt.setString(2, t_pwd.getPassword().toString());
			pstmt.setString(3, t_name.getText());
			pstmt.setString(4, t_contact.getText());
			
			if(t_id.getText().equals("")){
				JOptionPane.showMessageDialog(this, "아이디를 입력하세요");
			} else if(t_pwd.getPassword().equals("")){
				JOptionPane.showMessageDialog(this, "비밀번호를 입력하세요");
			} else if(t_name.getText().equals("")){
				JOptionPane.showMessageDialog(this, "이름을 입력하세요");
			} else if(t_contact.getText().equals("")){
				JOptionPane.showMessageDialog(this, "연락처를 입력하세요");
			} else{
				int result=pstmt.executeUpdate();

				if(result!=0){
					JOptionPane.showMessageDialog(this, "회원등록이 완료되었습니다.");
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		registUser();
	}
}
