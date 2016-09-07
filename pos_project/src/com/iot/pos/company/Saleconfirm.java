package com.iot.pos.company;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

public class Saleconfirm extends JPanel implements ActionListener{
	JPanel p_north, p_south, p_west, p_east, p_center, p_graph;
	JLabel la_north;
	//Choice ch;
	JTable table;
	JScrollPane scroll;
	SaleconfirmModel model;
	JTextField tf_pre, tf_next;
	public JButton bt_main, bt_pre,bt_next,bt_serch;
	
	JFreeChart chart;
	ChartPanel gra;
	PolylineBarChart demo;
	
	/*
	 * String url = "jdbc:oracle:thin:@localhost:1521:XE"; String user =
	 * "posman"; String password = "posman";
	 */
	// Connection con;

	public Saleconfirm() {
		p_north = new JPanel();
		p_south = new JPanel();
		p_west = new JPanel();
		p_east = new JPanel();
		p_center = new JPanel();
		p_graph = new JPanel();
		bt_main = new JButton("메인화면");
		bt_pre = new JButton("이전달력");
		bt_next = new JButton("다음달력");
		bt_serch = new JButton("검색");
		//달력붙이기
		tf_pre = new JTextField(15);
		tf_next = new JTextField(15);
	
		
		la_north = new JLabel("매출확인(일일매출)");

		//ch = new Choice();
		//ch.add("일매출");
		//ch.add("월매출");
		//ch.add("년매출");

		// connect();

		demo = new PolylineBarChart();
		table = new JTable(model = new SaleconfirmModel(demo));
		scroll = new JScrollPane(table);
		//demo.setData(model.data);
		chart = demo.getChart();
		gra = new ChartPanel(chart);

		// p_center.setLayout(new FlowLayout());
		// chart
		
		//ChartFrame frame1 = new ChartFrame("Bar Chart", chart);
		gra.setPreferredSize(new Dimension(900, 340));
		// frame1.setVisible(true);
		

		//la_north.setPreferredSize(new Dimension(800, 100));
		p_south.setPreferredSize(new Dimension(900, 50));
		scroll.setPreferredSize(new Dimension(900, 350));
		p_graph.setPreferredSize(new Dimension(900, 350));
		//ch.setPreferredSize(new Dimension(80, 60));
		p_center.setPreferredSize(new Dimension(1000, 1000));

		p_graph.add(gra);
		p_north.add(bt_main);
		p_north.add(la_north);
		p_north.add(tf_pre);
		p_north.add(bt_pre);
		p_north.add(tf_next);
		p_north.add(bt_next);
		p_north.add(bt_serch);
		//p_north.add(ch);

		p_graph.setBackground(Color.YELLOW);

		p_center.add(scroll);
		p_center.add(p_graph);

		add(p_north, BorderLayout.NORTH);
		add(p_center, BorderLayout.CENTER);
		add(p_south, BorderLayout.SOUTH);
		add(p_east, BorderLayout.EAST);
		add(p_west, BorderLayout.WEST);
		
		bt_pre.addActionListener(this);
		bt_next.addActionListener(this);
		bt_serch.addActionListener(this);
		/*
		 * addWindowListener(new WindowAdapter() { public void
		 * windowClosing(WindowEvent e) { // db닫기!! if (con != null) { try {
		 * con.close(); } catch (SQLException e1) { e1.printStackTrace(); } } //
		 * 프로세스 죽이기! System.exit(0); } });
		 */
		// setDefaultCloseOperation(EXIT_ON_CLOSE);
		// setSize(1024,960);
		// setResizable(false);
		// setVisible(true);

	}
	public void nextCalendar(){
		SwingCalender calendar = new SwingCalender(tf_next);
	}
	public void preCalendar(){
		SwingCalender calendar = new SwingCalender(tf_pre);
		
	}
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj.equals(bt_pre)){
			preCalendar();
		}else if(obj.equals(bt_next)){
			nextCalendar();
		}else if(obj.equals(bt_serch)){
			model.getSerch(tf_pre.getText(),tf_next.getText());
			model.fireTableDataChanged();
			table.updateUI();
			//demo.dataChange();
			/*
			gra.repaint();
			gra.updateUI();
			chart.fireChartChanged();
			gra.setVisible(true);
			*/
			/*gra.removeAll();
			gra (chart);
			gra.revalidate();*/
			//gra.repaint();
		}
	}
	/*
	 * public void connect(){ try {
	 * Class.forName("oracle.jdbc.driver.OracleDriver"); con =
	 * DriverManager.getConnection(url, user, password); //setTitle("접속성공");
	 * if(con == null){ JOptionPane.showMessageDialog(this, "접속실패"); } } catch
	 * (ClassNotFoundException | SQLException e) { e.printStackTrace(); } }
	 */
	/*
	 * public static void main(String[] args) { new Saleconfirm(); }
	 */

}
