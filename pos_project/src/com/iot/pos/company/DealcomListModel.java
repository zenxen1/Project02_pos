package com.iot.pos.company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class DealcomListModel extends AbstractTableModel{
	
	String[] columTitle ={"deal_id","거래업체","매입금","지불금","거래일자"};
	
	ArrayList<String[]>data = new ArrayList<String[]>();
	
	//Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public DealcomListModel() {
		//this.con = con;
		
		selectAll();
	}
	
	public void selectAll(){
		try {
			Connection con = PosMain.getConnection();
			String sql = "select * from deal order by deal_id";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			
			data.removeAll(data);
			
			while(rs.next()){
				String[] record = new String[columTitle.length];
				record[0] = Integer.toString(rs.getInt("deal_id"));
				record[1] = Integer.toString(rs.getInt("company_id"));
				record[2] = Integer.toString(rs.getInt("deal_money"));
				record[3] = Integer.toString(rs.getInt("paid_money"));
				record[4] = rs.getString("deal_date");
				
				//System.out.println(con);
				data.add(record);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rs !=null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt !=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public String getColumnName(int col) {
		return columTitle[col];
	}
	
	public int getColumnCount() {
		return columTitle.length;
	}

	public int getRowCount() {
		return data.size();
	}

	public Object getValueAt(int row, int col) {
		String[] record = data.get(row);
		return record[col];
	}

}
