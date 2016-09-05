package com.sds.pos.faceistroll;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.iot.pos.PosMain;

public class SalesTableModel extends AbstractTableModel{
	String[] column = {"번호","날짜 ","매출","금액","담당자"};
	ArrayList<String[]> data = new ArrayList<String[]>();
	
	
	public SalesTableModel() {
		showSale();
	}
	
	public void showSale(){
		Connection con = PosMain.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select sale_id, sale_date, total_money, payment, s.user_id";
		sql = sql + " from sale s, pos_user p where s.user_id=p.user_id";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			data.removeAll(data);
			while(rs.next()){
				String[] record = new String[column.length];	
				record[0] = Integer.toString(rs.getInt("sale_id"));
				record[1] = rs.getString("sale_date");
				record[2] = Integer.toString(rs.getInt("total_money"));
				record[3] = Integer.toString(rs.getInt("payment"));
				record[4] = Integer.toString(rs.getInt("user_id"));
				data.add(record);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	
	public String getColumnName(int col) {
		return column[col];
	}
	
	public int getColumnCount() {
		return column.length;
	}

	public int getRowCount() {
		return data.size();
	}

	public Object getValueAt(int arg0, int arg1) {
		return data.get(arg0)[arg1];
	}
	
}
