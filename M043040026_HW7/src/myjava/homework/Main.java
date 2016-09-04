package myjava.homework;

import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.JButton;
import javax.swing.JFrame;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import com.opencsv.CSVReader;

public class Main {

	public static void main(String[] args) {
		JFrame fr = new JFrame();
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton bt_opendata = new JButton();
		
		bt_opendata.setText("openfile");
		bt_opendata.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				JFreeChart[] chart;
				FileDialog openfile = new FileDialog(fr,"new",FileDialog.LOAD);
				openfile.setDirectory("C:\\");
				openfile.setFile("*.csv");
				openfile.show();
				String file_name = openfile.getFile();
				if(file_name == null){
					System.out.println("You didn't open a file");
				}else{
					csv_read csv = new csv_read(openfile.getDirectory() + "\\" + openfile.getFile());
					csv.read_data();
					chart = csv.draw_chart();
					fr.getContentPane().add(BorderLayout.WEST,new ChartPanel(chart[0]));
					fr.getContentPane().add(BorderLayout.EAST,new ChartPanel(chart[1]));
					fr.revalidate();
				}
			}
		});
		fr.setSize(1400, 500);
		fr.getContentPane().add(BorderLayout.NORTH,bt_opendata);
		fr.setVisible(true);
		//CSVReader reader = new CSVReader(new FileReader("C:/Users/Leo/Downloads/java_HW7"));
	}

}
