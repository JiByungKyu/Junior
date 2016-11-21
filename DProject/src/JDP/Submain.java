package JDP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

import org.json.simple.parser.ParseException;



public class Submain extends JFrame {
	
	private JTabbedPane jtp = new JTabbedPane();
	private twodayPanel PN_Weather;
	private newsPanel PN_News = new newsPanel();
	//private JPanel PN_Subway = new JPanel();
	
	public Submain(ForecastParser FP) throws IOException, ParseException{
		super("Weather Detail");
		PN_Weather = new twodayPanel(FP);
		//PN_Weather.setBackground(Color.WHITE);
		
		
			
		PN_News.setBackground(Color.WHITE);
		
		
		
		jtp.addTab("날씨", PN_Weather);
		jtp.addTab("뉴스", PN_News);
		PN_News.setLayout(null);
		//jtp.addTab("지하철", PN_Subway);
		this.setSize(330,545);
		this.setLocation(1105,93);
		setResizable(false);      
		
		getContentPane().add(jtp,BorderLayout.CENTER);
		setVisible(true);
		
	}
}
