package com.iot.pos.pcs88pc;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.iot.pos.PosMain;

public class SouthSum extends JPanel implements ItemListener {
	Connection con = PosMain.getConnection();

	// ���� ����
	JPanel cost;
	JLabel sum, discount, result;
	JLabel p_sum, p_discount, p_result;
	JButton confirm, cancel;
	Choice discounttype; // ����ī�� ����
	Choice paytype; // ���Ҽ���

	// ��������
	JPanel keyboard;

	// ��������� ��ư
	JPanel p_east;
	JButton bt, bt1, bt2, bt3;

	public SouthSum() {
		setLayout(new BorderLayout());
		// ����
		cost = new JPanel();
		sum = new JLabel("�հ�");
		discount = new JLabel("����");
		result = new JLabel("�Ѱ�");
		p_sum = new JLabel("�ܰ��� ���� ���;���.");
		p_discount = new JLabel("�������� ���;���.");
		p_result = new JLabel("�ܰ�x������");
		confirm = new JButton("Ȯ��");
		cancel = new JButton("���");

		discounttype = new Choice();
		paytype = new Choice();

		sum.setPreferredSize(new Dimension(90, 70));
		discount.setPreferredSize(new Dimension(90, 70));
		result.setPreferredSize(new Dimension(90, 70));
		p_sum.setPreferredSize(new Dimension(300, 70));
		p_discount.setPreferredSize(new Dimension(300, 70));
		p_result.setPreferredSize(new Dimension(300, 70));
		discounttype.setPreferredSize(new Dimension(65, 20));
		paytype.setPreferredSize(new Dimension(65, 20));

		discounttype.add("��  �á�");
		paytype.add("��  �á�");

		cost.add(discounttype);
		cost.add(paytype);

		cost.add(sum);
		cost.add(p_sum);
		cost.add(discount);
		cost.add(p_discount);
		cost.add(result);
		cost.add(p_result);
		cost.add(confirm);
		cost.add(cancel);

		// ��������� ��ư
		p_east = new JPanel(new GridLayout(4, 1));
		bt = new JButton("���������");
		bt1 = new JButton("���������");
		bt2 = new JButton("���������");
		bt3 = new JButton("���������");
		p_east.add(bt);
		p_east.add(bt1);
		p_east.add(bt2);
		p_east.add(bt3);

		p_east.setBackground(Color.RED);

		keyboard = new JPanel(new GridLayout(4, 5));
		Button[] bt = new Button[20];
		for (int i = 0; i < bt.length; i++) {
			bt[i] = new Button(i + "");
			keyboard.add(bt[i]);

		}

		cost.setPreferredSize(new Dimension(400, 300));
		p_east.setPreferredSize(new Dimension(200, 200));
		keyboard.setPreferredSize(new Dimension(200, 300));
		add(cost, BorderLayout.WEST);
		add(p_east, BorderLayout.EAST);
		add(keyboard);

		setPreferredSize(new Dimension(980, 100));
		
		//ī������ ����
		getDisCount();
		//���Ҽ��� ����
		getPayType();
	}

	//����ī�� ���� 
	public void getDisCount() {
		PreparedStatement pstmt=null;
		ResultSet rs=null;

		try {
			String sql = "select * from discounttype";
			pstmt=con.prepareStatement(sql);

			rs=pstmt.executeQuery();

			while(rs.next()){
				discounttype.add(rs.getString("discount_card"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}			
		}
	}

	//���Ҽ��� ����
	public void getPayType(){
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		
		try {
			String sql="select * from paytype";
			pstmt=con.prepareStatement(sql);

			rs=pstmt.executeQuery();
			
			while(rs.next()){
				paytype.add(rs.getString("pay_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}			
		}
	}
		
	@Override
	public void itemStateChanged(ItemEvent e) {
		Choice getDisCount=(Choice)e.getSource();
		Choice getPayType=(Choice)e.getSource();
	}
}
