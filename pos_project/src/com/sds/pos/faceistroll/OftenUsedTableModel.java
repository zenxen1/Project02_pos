package com.sds.pos.faceistroll;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import com.iot.pos.PosMain;

public class OftenUsedTableModel extends AbstractTableModel {
	String[] title = { "product_id", "품목", "상품명", "바코드", "원가", "판매가", "제조사", "유통기한" };
	ArrayList<String[]> data = new ArrayList<String[]>();
	OftenUsedProduct oftenUsedProduct;

	public OftenUsedTableModel(OftenUsedProduct oftenUsedProduct) {
		this.oftenUsedProduct = oftenUsedProduct;
		showProduct();
	}

	public void showProduct() {
		Connection con = PosMain.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select product_id, s.subgroup as subcategory_name, product_name, barcode, prime_cost, selling_price, manufacturer, expiration_date";
		sql = sql + " from product p, subcategory s where p.subcategory_id=s.subcategory_id";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			data.removeAll(data);
			while (rs.next()) {
				String[] record = new String[title.length];
				record[0] = Integer.toString(rs.getInt("product_id"));
				record[1] = rs.getString("subcategory_name");
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
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public void searchProduct() {
		Connection con = PosMain.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		data.removeAll(data);
		try {
			String sql = "select product_id, p.subcategory_id as subcategory_id, product_name, barcode, prime_cost, selling_price, manufacturer, expiration_date";
			sql = sql + " from product p, subcategory s where p.subcategory_id=s.subcategory_id and product_name=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, oftenUsedProduct.txt_search.getText());
			rs = pstmt.executeQuery();
			while (rs.next()) {
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
