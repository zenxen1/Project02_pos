package com.iot.pos.company;

import javax.swing.table.AbstractTableModel;

public class SaleconfirmModel extends AbstractTableModel{
	String[] columTitle = {"��¥","����","�ݾ�","����"};
	
	public String getColumnName(int col) {
		return columTitle[col];
	}
	
	public int getColumnCount() {
		return columTitle.length;
	}

	public int getRowCount() {
		return 100;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		return null;
	}

}
