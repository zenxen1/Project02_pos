package com.iot.pos.pcs88pc;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.iot.pos.PosMain;

public class SaleDeskTop_South extends JPanel implements ItemListener, ActionListener, KeyListener {

	// ���� ����
	JPanel cost;
	JLabel sum, discount, result;
	//JLabel p_sum, p_discount, p_result;
	JButton bt_confirm, bt_cancel;
	Choice discounttype; // ����ī�� ����
	Choice paytype; // ���Ҽ���

	// ��������
	JPanel keyboard;
	SaleDeskTop_SouthKeypad deskTop_SouthKeypad;

	// ��������� ��ư
	JPanel p_east;
	JButton bt, bt1, bt2, bt3;

	SaleDeskTop saleDeskTop;

	JTextField tf_barcord;
	JButton bt_barcord;
	JTextField tf_count;
	JButton bt_count;
	
	HashMap<String, Integer> discountMap;
	HashMap<String, Integer> paytypeMap;
	
	
	int paytypenumber;
	int discountnumber;

	public SaleDeskTop_South(SaleDeskTop saleDeskTop) {
		this.saleDeskTop = saleDeskTop;

		setLayout(new BorderLayout());
		// ����
		
		cost = new JPanel();
		sum = new JLabel("�հ�  :   "+saleDeskTop.total);
		discount = new JLabel("����   :    ");
		result = new JLabel("�Ѱ�   :    ");
		//p_sum = new JLabel("�ܰ��� ���� ���;���.");
		//p_discount = new JLabel("�������� ���;���.");
		//p_result = new JLabel("�ܰ�x������");
		bt_confirm = new JButton("Ȯ��");
		bt_cancel = new JButton("���");

		discounttype = new Choice();
		paytype = new Choice();

		tf_barcord = new JTextField(10);
		bt_barcord = new JButton("�˻�");
		tf_count = new JTextField(5);
		bt_count = new JButton("����");
		
		discountMap = new HashMap<String, Integer>();
		paytypeMap = new HashMap<String, Integer>();

		sum.setPreferredSize(new Dimension(350, 50));
		discount.setPreferredSize(new Dimension(350, 50));
		result.setPreferredSize(new Dimension(350, 50));
		//p_sum.setPreferredSize(new Dimension(300, 50));
		//p_discount.setPreferredSize(new Dimension(300, 50));
		//p_result.setPreferredSize(new Dimension(300, 70));
		// discounttype.setPreferredSize(new Dimension(65, 20));
		// paytype.setPreferredSize(new Dimension(65, 20));

		discounttype.add("��  �á�");
		paytype.add("��  �á�");

		cost.add(bt_barcord);
		cost.add(tf_barcord);
		cost.add(bt_count);
		cost.add(tf_count);

		cost.add(discounttype);
		cost.add(paytype);

		cost.add(sum);
		//cost.add(p_sum);
		cost.add(discount);
		//cost.add(p_discount);
		cost.add(result);
		//cost.add(p_result);
		cost.add(bt_confirm);
		cost.add(bt_cancel);

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
		
		deskTop_SouthKeypad = new SaleDeskTop_SouthKeypad(this);
		keyboard = new JPanel();
		keyboard.add(deskTop_SouthKeypad);
		
		deskTop_SouthKeypad.setPreferredSize(new Dimension(360, 290));
		cost.setPreferredSize(new Dimension(400, 300));
		p_east.setPreferredSize(new Dimension(200, 200));
		keyboard.setPreferredSize(new Dimension(390, 300));
		add(cost, BorderLayout.WEST);
		add(p_east, BorderLayout.EAST);
		add(keyboard);

		setPreferredSize(new Dimension(980, 100));

		bt_barcord.addActionListener(this);
		bt_count.addActionListener(this);
		bt_confirm.addActionListener(this);
		bt_cancel.addActionListener(this);
		tf_barcord.addKeyListener(this);
		
		paytype.addItemListener(this);
		discounttype.addItemListener(this);
		// ī������ ����
		getDisCount();
		// ���Ҽ��� ����
		getPayType();
	}

	// ����ī�� ����
	public void getDisCount() {
		Connection con = PosMain.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "select * from discounttype";
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				discounttype.add(rs.getString("discount_card"));
				discountMap.put(rs.getString("discount_card"), rs.getInt("discounttype_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	// ���Ҽ��� ����
	public void getPayType() {
		Connection con = PosMain.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "select * from paytype";
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				paytype.add(rs.getString("pay_name"));
				paytypeMap.put(rs.getString("pay_name"), rs.getInt("paytype_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj.equals(bt_barcord)) {
			String barcord = tf_barcord.getText();
			saleDeskTop.p_center.model.getSerch(barcord);

			saleDeskTop.p_center.model.fireTableDataChanged();
			saleDeskTop.p_center.table.updateUI();
		} else if (obj.equals(bt_count)) {
			saleDeskTop.addSaleDetail();
		} else if (obj.equals(bt_confirm)) {
			saleDeskTop.addSale();
			
		}else if (obj.equals(bt_cancel)){
			saleDeskTop.initSale();
		}

	}

	public void itemStateChanged(ItemEvent e) {
		Object obj = e.getSource();
		if(obj.equals(paytype)){
			
			paytypenumber = paytypeMap.get(paytype.getSelectedItem());
		
		}else if(obj.equals(discounttype)){
			discountnumber = discountMap.get(discounttype.getSelectedItem());
			saleDeskTop.disCount();
		}
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("keypress");
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("keyrelea");
		//System.out.println(tf_barcord.getText());
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            // your code is scanned and you can access it using frame.getBarCode()
            // now clean the bar code so the next one can be read
           // frame.setBarCode(new String());
			System.out.println(tf_barcord.getText());
        } else {
            // some character has been read, append it to your "barcode cache"
            //frame.setBarCode(frame.getBarCode() + e.getKeyChar());
        }
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("keyTyped");
	}
}
