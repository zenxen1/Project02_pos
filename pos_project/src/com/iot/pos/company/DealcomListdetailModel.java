package com.iot.pos.company;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class DealcomListdetailModel extends AbstractTableModel{
	String[] columTilte = {"NO","거래내역","상품정보","수량"}; 
	
	ArrayList<String[]> list = new ArrayList<String[]>();
	
	public DealcomListdetailModel() {
	
	}

	
	
	@Override
	public String getColumnName(int col) {
		// TODO Auto-generated method stub
		return columTilte[col];
	}
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columTilte.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

}
