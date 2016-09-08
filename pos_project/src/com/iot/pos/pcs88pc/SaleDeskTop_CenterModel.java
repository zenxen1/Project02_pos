package com.iot.pos.pcs88pc;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.iot.pos.PosMain;

public class SaleDeskTop_CenterModel extends AbstractTableModel {
	String[] columnTitle = { "No", "��ǰ�ڵ�", "��ǰ��", "�ܰ�", "����" };
	ArrayList<String[]> list = new ArrayList<String[]>();
	ArrayList<String> sellpriceList = new ArrayList<String>();

	// ���͸��� ������ ������ �Ѵ�.
	public SaleDeskTop_CenterModel() {
		//showProduct();
	}

	// pstmt�� rs�� ���� ���������� ����Ǿ�����. ���������϶� null���� ���� �ʴ´�.
	public void showProduct() {
		Connection con = PosMain.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "select product_id, barcode, product_name, selling_price from product";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				String[] record = new String[columnTitle.length];
				
				record[0]=Integer.toString(rs.getInt("product_id")); //���ڸ� ���ڰ����� �޾ƿ��� 
				record[1]=rs.getString("barcode"); // ���ڰ��� �޾ƿ���
				record[2]=rs.getString("product_name");
				record[3]=Integer.toString(rs.getInt("selling_price"));
				//record[4]=rs.get("");  ������ �����Է°�����!!
				
				list.add(record);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
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
	
	public void getSerch(String barcord){
		Connection con = PosMain.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select product_id, barcode, product_name, selling_price from product where barcode = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, barcord);
			rs = pstmt.executeQuery();
			while(rs.next()){
				String[] record = new String[columnTitle.length];
				
				record[0]=Integer.toString(rs.getInt("product_id")); //���ڸ� ���ڰ����� �޾ƿ��� 
				record[1]=rs.getString("barcode"); // ���ڰ��� �޾ƿ���
				record[2]=rs.getString("product_name");
				record[3]=Integer.toString(rs.getInt("selling_price"));
				//record[4]=rs.get("");  ������ �����Է°�����!!
				
				list.add(record);
				sellpriceList.add(record[3]);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public int getColumnCount() {
		return columnTitle.length;
	}

	@Override
	public String getColumnName(int col) {
		return columnTitle[col];
	}

	@Override
	public int getRowCount() {
		return list.size(); // �����ٿ� �迭����ŭ ũ�⸦ ����. 
	}

	@Override
	public Object getValueAt(int row, int col) {
		
		return list.get(row)[col];  // 2�ٷ� ǥ���ϱ�(���� �迭) = String record=list.get(row);  return record[col];   
	}

}
