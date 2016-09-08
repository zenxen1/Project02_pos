package com.sds.pos.koreain14;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import com.iot.pos.PosMain;

public class ProductTableModel extends AbstractTableModel{
	String[] title = {"상품코드", "품목", "상품명", "바코드", "원가", "판매가", "제조사", "유통기한"};
	ArrayList<String[]> data = new ArrayList<String[]>(); 
	ProductInformation productInfo;
	
	public ProductTableModel(ProductInformation productInfo) {
		this.productInfo = productInfo;
		showProduct();
	}
	public void showProduct(){
		Connection con = PosMain.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select product_id, s.subgroup as s_title, product_name, barcode, prime_cost, selling_price, manufacturer, expiration_date";
		sql = sql + " from product p, subcategory s where p.subcategory_id=s.subcategory_id";
		
		System.out.println(sql);
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			data.removeAll(data);
			
			while(rs.next()){
				String[] record = new String[title.length];
				record[0] = Integer.toString(rs.getInt("product_id"));
				record[1] = rs.getString("s_title");
				record[2] = rs.getString("product_name");
				record[3] = rs.getString("barcode");
				record[4] = Integer.toString(rs.getInt("prime_cost"));
				record[5] = Integer.toString(rs.getInt("selling_price"));
				record[6] = rs.getString("manufacturer");
				record[7] = rs.getString("expiration_date");		
				
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
	
	//상품찾기
		public void searchProduct(){
			Connection con = PosMain.getConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String search=productInfo.txt_search.getText();
			
			String sql = "select product_id, subgroup, product_name, barcode, prime_cost, selling_price, manufacturer, expiration_date";
			sql = sql +" from product p, subcategory s where p.subcategory_id=s.subcategory_id and product_name like '%"+search+"%' order by product_id desc";
			System.out.println(sql);
			try {
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				data.removeAll(data);
				
				while(rs.next()){
					String[] record = new String[8];
					record[0]=Integer.toString(rs.getInt("product_id"));
					record[1]=rs.getString("subgroup");
					record[2]=rs.getString("product_name");
					record[3]=rs.getString("barcode");
					record[4]=Integer.toString(rs.getInt("prime_cost"));
					record[5]=Integer.toString(rs.getInt("selling_price"));
					record[6]=rs.getString("manufacturer");
					record[7]=rs.getString("expiration_date");
					
					System.out.println(record[0]);
					
					data.add(record);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
		} finally{
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
		
	public void sortProduct(){
		Connection con= PosMain.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		String sql ="select product_id, subgroup, product_name, barcode, prime_cost";
		sql = sql + ", selling_price, manufacturer, expiration_date";
		sql = sql + " from product p, subcategory s where p.SUBCATEGORY_ID=s.SUBCATEGORY_ID ";
		sql = sql + " and subgroup=?";
		
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, productInfo.ch_sub.getSelectedItem());
			
			System.out.println(sql);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				String[] record = new String[8];
				record[0]=Integer.toString(rs.getInt("product_id"));
				record[1]=rs.getString("subgroup");
				record[2]=rs.getString("product_name");
				record[3]=rs.getString("barcode");
				record[4]=Integer.toString(rs.getInt("prime_cost"));
				record[5]=Integer.toString(rs.getInt("selling_price"));
				record[6]=rs.getString("manufacturer");
				record[7]=rs.getString("expiration_date");
				
				data.add(record);
				System.out.println(record);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	public String getColumnName(int col) {
		return title[col];
	}
	
	public int getColumnCount() {
		return title.length;
	}

	public int getRowCount() {
		return data.size();
	}

	public Object getValueAt(int row, int col) {
		return data.get(row)[col];
	}

}
