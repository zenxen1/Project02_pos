package com.iot.pos;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class SalesTable extends AbstractTableModel{
	String[] column = {"번호","날자 ","매출","금액"};
	ArrayList<String[]> data = new ArrayList<String[]>();
	
	
	
	public void createList(int number, String date, int sales, int sum){
		String[] str = {Integer.toString(number), date, Integer.toString(sales), Integer.toString(sum)};
		data.add(str);
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
