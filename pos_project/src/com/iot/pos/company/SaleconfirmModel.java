package com.iot.pos.company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.iot.pos.PosMain;

public class SaleconfirmModel extends AbstractTableModel{
	String[] columTitle = {"ID","토탈거래금액","판매일","판매시간","직원","지불금액","지불수단","할인수단","regdata"};
	//Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	ArrayList<String[]> data = new ArrayList<String[]>(); 
	PolylineBarChart demo;
	
	public SaleconfirmModel(PolylineBarChart demo) {
		//this.con = con;
		this.demo=demo;
		//selectAll();
	}
	
	public void selectAll(){
		String sql = "select sale_id,total_money,sale_date,sale_time, p.user_name as name,payment, t.pay_name as payname, d.discount_card as discount, s.regdate";
		sql = sql + " from sale s,pos_user p, paytype t, discounttype d where s.user_id = p.user_id and s.paytype_id = t.paytype_id and s.discounttype_id = d.discounttype_id order by sale_id asc";
		//System.out.println(sql);
		try {
			Connection con = PosMain.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				String[] record = new String[columTitle.length];
				record[0] = Integer.toString(rs.getInt("sale_id"));
				record[1] = Integer.toString(rs.getInt("total_money"));
				record[2] = rs.getString("sale_date");
				record[3] = rs.getString("sale_time");
				record[4] = rs.getString(("name"));
				record[5] = Integer.toString(rs.getInt("payment"));
				record[6] = rs.getString("payname");
				record[7] = rs.getString("discount");
				record[8] = rs.getString("regdate");				
				
				data.add(record);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void getSerch(String pre, String next){
		Connection con = PosMain.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println(pre);
		System.out.println(next);
		
		StringBuffer sb = new StringBuffer();	
		sb.append("select sale_id,total_money,sale_date,sale_time, p.user_name as name,payment, t.pay_name as payname, d.discount_card as discount, s.regdate");
		sb.append(" from sale s,pos_user p, paytype t, discounttype d where s.user_id = p.user_id and s.paytype_id = t.paytype_id and s.discounttype_id = d.discounttype_id ");
		sb.append(" and s.regdate > to_date(?,'YYYYMMDD') and s.regdate < to_date(?,'YYYYMMDD')");
		sb.append(" order by sale_id asc");
		System.out.println(sb.toString());
		try {
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setString(1, pre);
			pstmt.setString(2, next);
			rs = pstmt.executeQuery();
			
			data.removeAll(data);
			
			while(rs.next()){
				String[] record = new String[columTitle.length];
				record[0] = Integer.toString(rs.getInt("sale_id"));
				record[1] = Integer.toString(rs.getInt("total_money"));
				record[2] = rs.getString("sale_date");
				record[3] = rs.getString("sale_time");
				record[4] = rs.getString(("name"));
				record[5] = Integer.toString(rs.getInt("payment"));
				record[6] = rs.getString("payname");
				record[7] = rs.getString("discount");
				record[8] = rs.getString("regdate");				
				
				data.add(record);
			}
			System.out.println("aaaaaaa"+data.size());
			
			demo.setData(data);
			demo.dataChange();
		
		
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt!=null){
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
