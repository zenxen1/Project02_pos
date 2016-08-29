package com.iot.pos.company;

import javax.swing.table.AbstractTableModel;

public class DealcomListModel extends AbstractTableModel{
	
	String[] columTitle ={"NO","거래업체","거래일자","지불할금액","지불금액","결재일"};
	
	public String getColumnName(int col) {
		return columTitle[col];
	}
	
	public int getColumnCount() {
		return columTitle.length;
	}

	public int getRowCount() {
		return 4;
	}

	public Object getValueAt(int row, int col) {
		return null;
	}

}
