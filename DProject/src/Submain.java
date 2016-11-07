
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Label;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import java.awt.Canvas;

public class Submain extends JFrame {
	
	private JTabbedPane jtp = new JTabbedPane();
	private JPanel PN_Weather = new JPanel();
	private JPanel PN_News = new JPanel();
	private JPanel PN_Subway = new JPanel();
	public Submain(){
		super("contents detail");
		PN_News.setBackground(Color.WHITE);
		PN_Weather.setBackground(Color.WHITE);
		
		jtp.addTab("날씨", PN_Weather);
		PN_Weather.setLayout(null);
		JPanel PN_Center = new JPanel();
		PN_Center.setBounds(189, 5, 1, 1);
		PN_Center.setLayout(null);
		
		PN_Center.setBackground(Color.BLACK);
		PN_Center.setLayout(null);
		
		
		JLabel POP = new JLabel("강수 확률 : ");
		POP.setBounds(1483,225,70,50);
		PN_Center.add(POP);
		
		List<Integer> scores = new ArrayList<Integer>();
	      Random random = new Random();
	      int maxDataPoints = 10;
	      int maxScore = 70;
	      for (int i = 0; i < maxDataPoints ; i++) {
	         scores.add(i*5-10);
	      }
	      DrawGraph mainPanel = new DrawGraph(scores);
	      mainPanel.setBounds(25, 25, 320, 270);
	    
		PN_Weather.add(mainPanel);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		PN_Weather.add(PN_Center);
		
		Canvas canvas = new Canvas();
		canvas.setBounds(72, 99, 167, 177);
		canvas.createImage(null);
		PN_Weather.add(canvas);
		
		
		jtp.addTab("뉴스", PN_News);
		PN_News.setLayout(null);
		
		jtp.setBackground(Color.GREEN);
		
		setSize(400,550);//사이즈 정의
		setLocation(1070,90);//켜졌을때의 프레임의 위치
		//setResizable(false);//크기조정 불가
		
		getContentPane().add(jtp);
		PN_Subway.setBackground(Color.WHITE);
		jtp.addTab("지하철", PN_Subway);
		PN_Subway.setLayout(null);
		
		setVisible(true);
		//this.pack();
	}
}
