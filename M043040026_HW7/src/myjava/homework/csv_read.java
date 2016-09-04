package myjava.homework;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.RectangleInsets;

import com.opencsv.CSVReader;
import com.orsoncharts.label.StandardPieLabelGenerator;

public class csv_read {
	CSVReader reader;
	HashMap<String, id_information> people_list;
	int[] product, sex;

	public csv_read(String file_path) {
		try {
			reader = new CSVReader(new FileReader(file_path));
			people_list = new HashMap<String, id_information>();
			product = new int[4];
			product[1] = 0;
			product[2] = 0;
			product[3] = 0;
			sex = new int[2];
			sex[0] = 0;
			sex[1] = 0;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void read_data() {
		String[] nextline;
		int first_time = 0;
		try {
			while ((nextline = reader.readNext()) != null) {
				// System.out.println(nextline[0]+":"+nextline[1]+":"+nextline[2]);
				if (people_list.containsKey(nextline[0])) {
					id_information tmp = people_list.get(nextline[0]);
					if (nextline[1].equals("Product_A")) {
						tmp.product[1] = 1;
					}
					if (nextline[1].equals("Product_B")) {
						tmp.product[2] = 1;
					} 
					if (nextline[1].equals("Product_C")) {
						tmp.product[3] = 1;
					}
					people_list.put(nextline[0], tmp);
				} else {
					if (first_time == 0)
						first_time++;
					else
						people_list.put(nextline[0], new id_information(nextline[1], nextline[2]));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public JFreeChart[] draw_chart() {
		// calculate sex , product number
		int count = 0;
		for (Map.Entry<String, id_information> entry : people_list.entrySet()) {
			if (entry.getValue().sex == 0) {
				this.sex[0]++;
			} else if (entry.getValue().sex == 1) {
				this.sex[1]++;
			}

			if (entry.getValue().product[1] == 1) {
				this.product[1]++;
			}
			if (entry.getValue().product[2] == 1) {
				this.product[2]++;
			}
			if (entry.getValue().product[3] == 1) {
				this.product[3]++;
			}
			/*if((entry.getValue().product[1] + entry.getValue().product[2] + entry.getValue().product[3])>1){
				count++;
				System.out.println(entry.getKey());
			}*/
		}
		// draw piechart
		DefaultPieDataset piechart_data_product = new DefaultPieDataset();
		piechart_data_product.setValue("Product_A", (double) this.product[1]);
		piechart_data_product.setValue("Product_B", (double) this.product[2]);
		piechart_data_product.setValue("Product_C", (double) this.product[3]);

		DefaultPieDataset piechart_data_sex = new DefaultPieDataset();
		piechart_data_sex.setValue("Female", (double) this.sex[0]);
		piechart_data_sex.setValue("Male", (double) this.sex[1]);

		JFreeChart[] chart = new JFreeChart[2];
		PiePlot plot;
		chart[0] = ChartFactory.createPieChart3D("Product", piechart_data_product, true, true, false);
		chart[1] = ChartFactory.createPieChart3D("Sex", piechart_data_sex, true, true, false);

		plot = (PiePlot) chart[0].getPlot();
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} : {1} - {2}"));
		/*
		 * plot.setLegendLabelGenerator( new StandardPieSectionLabelGenerator(
		 * "{0} : {1} - {2}"));
		 */
		// plot.setSimpleLabels(true);
		plot.setSimpleLabelOffset(new RectangleInsets(-20, -20, -20, -20));
		plot = (PiePlot) chart[1].getPlot();
		/*
		 * plot.setLegendLabelGenerator( new StandardPieSectionLabelGenerator(
		 * "{0} : {1} - {2}"));
		 */
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} : {1} - {2}"));
		// plot.setSimpleLabels(true);
		plot.setSimpleLabelOffset(new RectangleInsets(-20, -20, -20, -20));
		//System.out.println(count);
		return chart;
	}
}
