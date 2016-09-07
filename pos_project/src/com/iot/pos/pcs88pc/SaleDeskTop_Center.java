package com.iot.pos.pcs88pc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import com.iot.pos.PosMain;

public class SaleDeskTop_Center extends JPanel implements TableModelListener{
	Connection con = PosMain.getConnection(); 
	CenterModel model;
	JPanel center;
	JTable table;
	JScrollPane scroll;
	CenterGrid grid;
	
	
	public SaleDeskTop_Center(){
		center = new JPanel(new BorderLayout());
		table = new JTable(model=new CenterModel());
		scroll = new JScrollPane(table);
		
		scroll.setPreferredSize(new Dimension(540, 450));
		table.setRowHeight(50);
		
		grid = new CenterGrid();
		
		center.setPreferredSize(new Dimension(550, 450));
		grid.setPreferredSize(new Dimension(400, 450));
		
		center.add(scroll);
		
		add(center);
		add(grid);
		
		setBackground(Color.RED);
		
		setVisible(true);
		setSize(1000,600);
		
	}

	/*public void tableUpdate(){
		PreparedStatement pstmt=null;
		
		int row=table.getSelectedRow();
		int col=0;
		String product_id=(String)table.getValueAt(row, col); 
		
		String sql="update product set product_name=?,price=?,stock=?,detail=?";
		sql=sql+" where product_id=?";
	}
	
	@Override
	public void tableChanged(TableModelEvent e) {
		
	}
*/
}
