package com.sds.pos.faceistroll;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JPanel;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;
 
/**
 * A simple demonstration application showing how to create a bar chart overlaid
 * with a line chart.
 */
public class PolylineBarChart extends JPanel{
	ArrayList<String[]> data = new ArrayList<String[]>(); 
	ArrayList <Integer>showProduct = new ArrayList<Integer>();
	ArrayList <String>product = new ArrayList<String>();
	DefaultCategoryDataset dataset1;
    
	public PolylineBarChart() {
		setData(data);
		dataChange();
	}
	public void setData(ArrayList<String[]> data) {
		this.data = data;
		showProduct.removeAll(showProduct);
		product.removeAll(product);
		for(int i =0;i<data.size();i++){
			String[] record = data.get(i);
			System.out.println(i);
			showProduct.add(Integer.parseInt(record[1]));
			product.add(record[2]);
		}

	}
	
    public JFreeChart getChart() {
    	System.out.println("ȣ���߳�?????");
        // ������ ����
        dataset1 = new DefaultCategoryDataset();                // bar chart 1
        dataChange();

        // ������ ���� �� ����
        // ������ ����
        final BarRenderer renderer = new BarRenderer();
        final BarRenderer renderer12 = new BarRenderer();
        final LineAndShapeRenderer renderer2 = new LineAndShapeRenderer();
       
        // ���� �ɼ� ����
        final CategoryItemLabelGenerator generator = new StandardCategoryItemLabelGenerator();
        final ItemLabelPosition p_center = new ItemLabelPosition(
                ItemLabelAnchor.CENTER, TextAnchor.CENTER
            );
        final ItemLabelPosition p_below = new ItemLabelPosition(
                     ItemLabelAnchor.OUTSIDE6, TextAnchor.TOP_LEFT
                     );
        Font f = new Font("Gulim", Font.BOLD, 14);
        Font axisF = new Font("Gulim", Font.PLAIN, 14);
       
        // ������ ����
        // �׷��� 1
        renderer.setBaseItemLabelGenerator(generator);
        renderer.setBaseItemLabelsVisible(true);
        renderer.setBasePositiveItemLabelPosition(p_center);
        renderer.setBaseItemLabelFont(f);

        renderer.setSeriesPaint(0, new Color(0,162,255));
 
        // �׷��� 2       
        renderer12.setSeriesPaint(0, new Color(232,168,255));
        renderer12.setBaseItemLabelFont(f);
        renderer12.setBasePositiveItemLabelPosition(p_center);
        renderer12.setBaseItemLabelGenerator(generator);
        renderer12.setBaseItemLabelsVisible(true);
       
        // �׷��� 3       
        renderer2.setBaseItemLabelGenerator(generator);
        renderer2.setBaseItemLabelsVisible(true);
        renderer2.setBaseShapesVisible(true);
        renderer2.setDrawOutlines(true);
        renderer2.setUseFillPaint(true);
        renderer2.setBaseFillPaint(Color.WHITE);
        renderer2.setBaseItemLabelFont(f);
        renderer2.setBasePositiveItemLabelPosition(p_below);
        renderer2.setSeriesPaint(0,new Color(219,121,22));
        renderer2.setSeriesStroke(0,new BasicStroke(
                                               2.0f,
                                               BasicStroke.CAP_ROUND,
                                               BasicStroke.JOIN_ROUND,
                                               3.0f)
        );
       
        // plot ����
        final CategoryPlot plot = new CategoryPlot();
       
        // plot �� ������ ����
        plot.setDataset(dataset1);
        plot.setRenderer(renderer);

        // plot �⺻ ����
        plot.setOrientation(PlotOrientation.VERTICAL);             // �׷��� ǥ�� ����
        plot.setRangeGridlinesVisible(true);                       // X�� ���̵� ���� ǥ�ÿ���
        plot.setDomainGridlinesVisible(true);                      // Y�� ���̵� ���� ǥ�ÿ���
 
        // ������ ���� ���� : dataset ��� ������� ������ ( ��, ���� ����Ѱ� �Ʒ��� �� )
        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
       
        // X�� ����
        plot.setDomainAxis(new CategoryAxis());              // X�� ���� ����
        plot.getDomainAxis().setTickLabelFont(axisF); // X�� ���ݶ� ��Ʈ ����
        plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.STANDARD);       // ī�װ� �� ��ġ ����
 
        // Y�� ����
        plot.setRangeAxis(new NumberAxis());                 // Y�� ���� ����
        plot.getRangeAxis().setTickLabelFont(axisF);  // Y�� ���ݶ� ��Ʈ ����
       
        // ���õ� plot�� �������� chart ����
        final JFreeChart chart = new JFreeChart(plot);
        return chart;
    }
 
    public void dataChange(){
    	for(int i=0;i<data.size();i++){
        	dataset1.addValue(showProduct.get(i), "��ǰ", product.get(i));
        	// dataset2.addValue(totalmoney.get(i), "��Ż�ŷ��ݾ�", saledate.get(i));
        }
    }
}
