package com.sds.pos.koreain14;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class ProductTableModel extends AbstractTableModel{
	String[] title = {"product_id", "품목", "상품명", "바코드", "원가", "판매가", "제조사", "유통기한"};
	ArrayList<String[]> data = new ArrayList<String[]>(); 
	ProductInformation oftenUsedProduct;
	
	
	public ProductTableModel() {
		//showProduct();
	}
	/*public void showProduct(){
		Connection con = AppMain.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select product_id, p.subcategory_id as subcategory_id, product_name, barcode, prime_cost, selling_price, manufacturer, expiration_date";
		sql = sql + " from product p, subcategory s where p.subcategory_id=s.subcategory_id";		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			data.removeAll(data);
			while(rs.next()){
				String[] record = new String[title.length];
				record[0] = Integer.toString(rs.getInt("product_id"));
				record[1] = Integer.toString(rs.getInt("subcategory_id"));
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
	
	public void searchProduct(){
		String sql = "select product_id, p.subcategory_id as subcategory_id, product_name, barcode, prime_cost, selling_price, manufacturer, expiration_date";
		sql = sql +" from product p, subcategory s where p.subcategory_id=s.subcategory_id and product_name="+"'"+oftenUsedProduct.txt_search.getText()+"'"+" order by desc";
		Connection con = AppMain.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		if(oftenUsedProduct.ch_top.getSelectedIndex() == 0){
			System.out.println("상위 카테고리를 선택해주세요");
		}else if(oftenUsedProduct.ch_sub.getSelectedIndex() == 0){
			System.out.println("하위 카테고리를 선택해주세요");
		}else if(oftenUsedProduct.txt_search.equals(oftenUsedProduct.txt_search.getText())){		
			data.removeAll(data);
			try {
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next()){
					String[] record = new String[title.length];
					record[0] = Integer.toString(rs.getInt("product_id"));
					record[1] = Integer.toString(rs.getInt("subcategory_id"));
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
			}
			
		}
	}
	*/
	public String getColumnName(int col) {
		return title[col];
	}
	
	public int getColumnCount() {
		return title.length;
	}

	public int getRowCount() {
		return data.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		return data.get(rowIndex)[columnIndex];
	}

}
