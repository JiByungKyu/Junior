package HDP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

import org.json.simple.parser.ParseException;

import weather.ForecastParser;



public class Submain extends JFrame {
	
	private JTabbedPane jtp = new JTabbedPane();
	private twodayPanel PN_Weather;
	private newsPanel PN_News = new newsPanel();
	//private JPanel PN_Subway = new JPanel();
	
	public Submain(ForecastParser FP) throws IOException, ParseException{
		super("Weather Detail");
		PN_Weather = new twodayPanel(FP);
		PN_Weather.setBackground(Color.WHITE);
		
		
			
		PN_News.setBackground(Color.WHITE);
		
		
		
		jtp.addTab("날씨", PN_Weather);
		jtp.addTab("뉴스", PN_News);
		PN_News.setLayout(null);
		JLabel Media = new JLabel(PN_News.news.getTitle().get(0));
		Media.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		int pubDateLength = PN_News.news.getPubDate().length();
		JLabel getDate = new JLabel(PN_News.news.getPubDate().substring(0, pubDateLength-3)+" ");
		Media.setBounds(12, 10, 200, 30);
		getDate.setBounds(12, 38, 248, 25);
		PN_News.add(Media);
		PN_News.add(getDate);
		//jtp.addTab("지하철", PN_Subway);
		this.setSize(330,545);
		this.setLocation(1117,93);
		setResizable(false);      
		
		getContentPane().add(jtp,BorderLayout.CENTER);
		setVisible(true);
		
	}
}
