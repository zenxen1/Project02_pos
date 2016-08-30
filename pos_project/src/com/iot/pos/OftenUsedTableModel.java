package com.iot.pos;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class TableInfo extends AbstractTableModel{
	String[] title = {"상품코드","품명","단가","판매가","제조사","유통기한"};
	ArrayList<String[]> data = new ArrayList<String[]>(); 
	
	
	
	public void createList(int code, String name, int u_cost, int u_price, String maker, String e_date) {
		String[] str = {Integer.toString(code), name, Integer.toString(u_cost), Integer.toString(u_price), maker, e_date};
		data.add(str);
		
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
