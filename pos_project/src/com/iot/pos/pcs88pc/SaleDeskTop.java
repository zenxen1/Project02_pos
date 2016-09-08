package com.iot.pos.pcs88pc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.iot.pos.PosMain;

public class SaleDeskTop extends JPanel {
	
	SaleDeskTop_North p_north;
	SaleDeskTop_Center p_center;
	SaleDeskTop_South p_south;
	public JButton bt_main;
	
	FirstLogin firstLogin;
	ArrayList<String> countList = new ArrayList<String>();
	
	int total;
	int result;
	int maxsaleid;

	public SaleDeskTop(FirstLogin firstLogin){
		this.firstLogin = firstLogin;
		p_north = new SaleDeskTop_North();
		
		bt_main = p_north.bt_main; 
		
		p_center = new SaleDeskTop_Center();
		p_south = new SaleDeskTop_South(this);
		
		p_north.setPreferredSize(new Dimension(980,50));
		add(p_north,BorderLayout.NORTH);
		
		p_center.setPreferredSize(new Dimension(980,500));
		add(p_center);
		
	
		p_south.setPreferredSize(new Dimension(980, 300));
		add(p_south,BorderLayout.SOUTH);
		
		
		//setDefaultCloseOperation(EXIT_ON_CLOSE); �ǳڷ� �ٲ����Ƿ� ���������� �ʿ䰡 ����.
		this.setPreferredSize(new Dimension(1000, 950));
		setBackground(Color.CYAN);
		setVisible(true);
		
		
	}
	
	public void sum(){
		int x =0;
		int y =0;
		total = 0;
		for(int i=0;i<countList.size();i++){
			//�ܰ�
			x = Integer.parseInt(p_center.model.sellpriceList.get(i));
			//����
			y = Integer.parseInt(countList.get(i));
			
			total = total +(x*y);
			//System.out.println(x+"*"+y+"="+total);
			System.out.println(countList.size());
		}
		
	}
	public void disCount(){
		double discount =total * (double)0.1;
		p_south.discount.setText("����   :    "+discount);
		result = total - (int)discount;
		p_south.result.setText("�Ѱ�   :   "+result);
		
	}
	public void addSale(){
		
		Connection con = PosMain.getConnection();
		PreparedStatement pstmt = null;
		String sql = "insert into SALE (SALE_ID, TOTAL_MONEY, SALE_DATE, SALE_TIME, USER_ID, PAYMENT, PAYTYPE_ID, DISCOUNTTYPE_ID)";
		//                                                     �ݾ�                      ���ұݾ�,
		sql = sql + "values (seq_sale.nextval, ?, '9/7','9:00',?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, total);
			//String[] record = firstLogin.loginlist.get(0); USER ��� ��� �ּ�ó�� Integer.parseInt(record[0])
			pstmt.setInt(2, 1); //���� ��ú���
			pstmt.setInt(3, result);
			pstmt.setInt(4, p_south.paytypenumber);
			pstmt.setInt(5, p_south.discountnumber);
			//System.out.println(p_south.discountnumber);
			//System.out.println(p_south.paytypenumber);
			int result = pstmt.executeUpdate();
			if(result>0){
				System.out.println("sale ��ϿϷ�");
			}
			
			initSale();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public void initSale(){
		//����� �ʱ�ȭ
		p_center.model.sellpriceList.removeAll(p_center.model.sellpriceList);
		countList.removeAll(countList);
		total=0;
		result=0;
		
		p_south.tf_barcord.setText("");
		p_south.tf_count.setText("");
		p_south.sum.setText("�հ�   :  ");
		p_south.discount.setText("����   :    ");
		p_south.result.setText("�Ѱ�   :   ");
		
		
		//���ڵ� ���̺� �ʱ�ȭ
		p_center.model.list.removeAll(p_center.model.list);
		p_center.model.fireTableDataChanged();
		p_center.table.updateUI();
	}
	public void maxsaleid(){
		Connection con = PosMain.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		String sql = "select max(sale_id) from sale";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				maxsaleid = rs.getInt("max(sale_id)");
			}
			//System.out.println(Integer.toString(maxsaleid));
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void addSaleDetail(){
		maxsaleid();
		Connection con = PosMain.getConnection();
		PreparedStatement pstmt = null;
		String sql = "insert into SALEDETAIL (SALEDETAIL_ID, SALE_ID, PRODUCT_ID, SALE_COUNT)";
		//                                                       ��ǰ�ڵ�
		sql = sql + " values (seq_saledetail.nextval,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, (maxsaleid+1));//sail_id
			String[] record = p_center.model.list.get(0);
			pstmt.setInt(2, Integer.parseInt(record[0]));
			pstmt.setInt(3, Integer.parseInt(p_south.tf_count.getText()));//����
			int result = pstmt.executeUpdate();
			if(result>0){
				System.out.println("saledetail ��ϿϷ�");
			}
			countList.add(p_south.tf_count.getText());
			
			//���
			sum();
			p_south.sum.setText("�հ�   :  "+total);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	

	/*
	public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.add(new SaleDeskTop());
		System.out.println("�ǸŰ��� Ȯ�� ");
		frame.setSize(1024, 960);
		frame.setVisible(true);
	}
	*/
}
