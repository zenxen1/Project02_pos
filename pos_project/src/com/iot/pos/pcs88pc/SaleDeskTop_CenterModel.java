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
	String[] columnTitle = { "No", "상품코드", "상품명", "단가", "수량" };
	ArrayList<String[]> list = new ArrayList<String[]>();
	ArrayList<String> sellpriceList = new ArrayList<String>();

	// 센터모델의 생성자 역할을 한다.
	public SaleDeskTop_CenterModel() {
		//showProduct();
	}

	// pstmt와 rs는 현재 지역변수로 선언되어있음. 전역변수일땐 null값을 주진 않는다.
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
				
				record[0]=Integer.toString(rs.getInt("product_id")); //숫자를 문자값으로 받아오기 
				record[1]=rs.getString("barcode"); // 문자값을 받아오기
				record[2]=rs.getString("product_name");
				record[3]=Integer.toString(rs.getInt("selling_price"));
				//record[4]=rs.get("");  수량은 직접입력값으로!!
				
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
				
				record[0]=Integer.toString(rs.getInt("product_id")); //숫자를 문자값으로 받아오기 
				record[1]=rs.getString("barcode"); // 문자값을 받아오기
				record[2]=rs.getString("product_name");
				record[3]=Integer.toString(rs.getInt("selling_price"));
				//record[4]=rs.get("");  수량은 직접입력값으로!!
				
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
		return list.size(); // 가로줄에 배열값만큼 크기를 정함. 
	}

	@Override
	public Object getValueAt(int row, int col) {
		
		return list.get(row)[col];  // 2줄로 표현하기(이중 배열) = String record=list.get(row);  return record[col];   
	}

}
