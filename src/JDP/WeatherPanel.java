package JDP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import weather.ForecastParser;

public class WeatherPanel extends JPanel {
	String location = "충무로";
	
	JPanel pn_temp;
	JTextArea ta_weatherInfo;
	JTextArea ta_tempNow;
	JTextArea ta_sensibleTemp;
	ImageIcon weatherImg;
	Image useImg;
	JLabel img;
	
	WeatherPanel(){		
		setBackground(Color.WHITE);
		pn_temp = new JPanel();
		ta_weatherInfo = new JTextArea();
		ta_tempNow = new JTextArea();
		ta_sensibleTemp = new JTextArea();
		
		ta_weatherInfo.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		ta_weatherInfo.setEditable(false);
		ta_weatherInfo.setBackground(Color.WHITE);
		ta_weatherInfo.setText(location + "\n");
		
		switch(MainFrame.getMapGrib().get("SKY")){
		case "1": 
			ta_weatherInfo.append("맑음\n");
			weatherImg = new ImageIcon("sunny.jpg");
			useImg = weatherImg.getImage();
			useImg = useImg.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
			weatherImg = new ImageIcon(useImg);
			img = new JLabel("", weatherImg, SwingUtilities.CENTER);
		break;
		case "2": 
			ta_weatherInfo.append("구름조금\n"); 
			weatherImg = new ImageIcon("");
			useImg = weatherImg.getImage();
			useImg = useImg.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
			weatherImg = new ImageIcon(useImg);
			img = new JLabel("", weatherImg, SwingUtilities.CENTER);
			break;
		case "3":
			ta_weatherInfo.append("구름많음\n");
			weatherImg = new ImageIcon("");
			useImg = weatherImg.getImage();
			useImg = useImg.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
			weatherImg = new ImageIcon(useImg);
			img = new JLabel("", weatherImg, SwingUtilities.CENTER);
			break;
		case "4":
		if(MainFrame.getMapGrib().get("PTY") == "1"){
			ta_weatherInfo.append("비\n");
			weatherImg = new ImageIcon("");
			useImg = weatherImg.getImage();
			useImg = useImg.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
			weatherImg = new ImageIcon(useImg);
			img = new JLabel("", weatherImg, SwingUtilities.CENTER);
		}
		else if(MainFrame.getMapGrib().get("PTY") == "2"){
			ta_weatherInfo.append("진눈깨비\n");
			weatherImg = new ImageIcon("");
			useImg = weatherImg.getImage();
			useImg = useImg.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
			weatherImg = new ImageIcon(useImg);
			img = new JLabel("", weatherImg, SwingUtilities.CENTER);
		}
		else if(MainFrame.getMapGrib().get("PTY") == "3"){
			ta_weatherInfo.append("눈\n");
			weatherImg = new ImageIcon("");
			useImg = weatherImg.getImage();
			useImg = useImg.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
			weatherImg = new ImageIcon(useImg);
			img = new JLabel("", weatherImg, SwingUtilities.CENTER);
		}
		else if(MainFrame.getMapGrib().get("PTY") == "0"){
			ta_weatherInfo.append("흐림\n");
			weatherImg = new ImageIcon("");
			useImg = weatherImg.getImage();
			useImg = useImg.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
			weatherImg = new ImageIcon(useImg);
			img = new JLabel("", weatherImg, SwingUtilities.CENTER);
		}
			 break;
		}
		
		ta_weatherInfo.append("");
		ta_weatherInfo.append("%       ");
		
		pn_temp.setLayout(new BorderLayout());
		pn_temp.setSize(15, 15);
		ta_tempNow.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		ta_tempNow.setText(MainFrame.getMapGrib().get("T1H")+"°C");
		ta_sensibleTemp.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		ta_sensibleTemp.setText("체감: " + calculateSensibleTemp() + "°C");
		
		pn_temp.add(BorderLayout.NORTH, ta_tempNow);
		pn_temp.add(BorderLayout.SOUTH, ta_sensibleTemp);
		add("LEFT", img);
		add("CENTER", ta_weatherInfo);
		add("RIGHT", pn_temp);
	}
	
	String calculateSensibleTemp(){
		double v = Double.parseDouble(MainFrame.getMapGrib().get("WSD"))*3.6;
		double t = Double.parseDouble(MainFrame.getMapGrib().get("T1H"));
		double senTemp = 13.12 + 0.6215*t - 11.37*Math.pow(v,0.16) + 0.3965*Math.pow(v,0.16)*t;
		
		return Double.toString((int)senTemp);
	}
}