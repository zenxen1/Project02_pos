package com.iot.pos.company;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

public class Saleconfirm extends JPanel {
	JPanel p_north, p_south, p_west, p_east, p_center, p_graph;
	JLabel la_north;
	Choice ch;
	JTable table;
	JScrollPane scroll;
	SaleconfirmModel model;
	public JButton bt_main;

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

		la_north = new JLabel("매출확인(일일매출)");

		ch = new Choice();
		ch.add("일매출");
		ch.add("월매출");
		ch.add("년매출");

		// connect();
		table = new JTable(model = new SaleconfirmModel());
		scroll = new JScrollPane(table);

		// p_center.setLayout(new FlowLayout());
		// chart
		PolylineBarChart demo = new PolylineBarChart(model.data);
		JFreeChart chart = demo.getChart();
		ChartPanel gra = new ChartPanel(chart);
		//ChartFrame frame1 = new ChartFrame("Bar Chart", chart);
		gra.setPreferredSize(new Dimension(900, 340));
		// frame1.setVisible(true);

		la_north.setPreferredSize(new Dimension(800, 100));
		p_south.setPreferredSize(new Dimension(900, 50));
		scroll.setPreferredSize(new Dimension(900, 350));
		p_graph.setPreferredSize(new Dimension(900, 350));
		ch.setPreferredSize(new Dimension(80, 60));
		p_center.setPreferredSize(new Dimension(1000, 1000));

		p_graph.add(gra);
		p_north.add(bt_main);
		p_north.add(la_north);
		p_north.add(ch);

		p_graph.setBackground(Color.YELLOW);

		p_center.add(scroll);
		p_center.add(p_graph);

		add(p_north, BorderLayout.NORTH);
		add(p_center, BorderLayout.CENTER);
		add(p_south, BorderLayout.SOUTH);
		add(p_east, BorderLayout.EAST);
		add(p_west, BorderLayout.WEST);
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
