package com.iot.pos.pcs88pc;

import javax.swing.table.AbstractTableModel;

public class CenterTableModel extends AbstractTableModel{
	String[] ColumTitle = {"No","상품코드","상품명","수량","단가","할인"};
	int col = 6;
	
	public CenterTableModel(){
		
		
	}

	public String getColumnCount(int col) {
		
		return ColumTitle[col];
	}
	public int getColumnCount() {
		
		return 6;
	}

	public int getRowCount() {
		return 3;
	}

	public Object getValueAt(int arg0, int arg1) {
		
		return null;
	}

}
