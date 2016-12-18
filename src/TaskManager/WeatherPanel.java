package TaskManager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import Util.ForecastParser;

public class WeatherPanel extends JPanel {
	//field
	String location = "충무로";
	JPanel pn_temp;
	JTextArea ta_weatherInfo;
	JTextArea ta_tempNow;
	JTextArea ta_sensibleTemp;
	ImageIcon weatherImg;
	Image useImg;
	JLabel img;
	static int sky, fineDust;
	
	//기본 생성자
	WeatherPanel(){		
		setBackground(Color.WHITE);
		pn_temp = new JPanel();
		ta_weatherInfo = new JTextArea();
		ta_tempNow = new JTextArea();
		ta_sensibleTemp = new JTextArea();
		//날씨 정보 text
		ta_weatherInfo.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		ta_weatherInfo.setEditable(false);
		ta_weatherInfo.setBackground(Color.WHITE);
		ta_weatherInfo.setText(location + "\n");
		//SKY의 상태에 따라 이미지 출력
		switch(MainFrame.getWeather().get("SKY")){
		case "1": 
			sky = 1;
			ta_weatherInfo.append("맑음\n");
			weatherImg = new ImageIcon("맑음.jpg");
			useImg = weatherImg.getImage();
			useImg = useImg.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
			weatherImg = new ImageIcon(useImg);
			img = new JLabel("", weatherImg, SwingUtilities.CENTER);
		break;
		case "2": 
			sky = 2;
			ta_weatherInfo.append("구름조금\n"); 
			weatherImg = new ImageIcon("구름조금.jpg");
			useImg = weatherImg.getImage();
			useImg = useImg.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
			weatherImg = new ImageIcon(useImg);
			img = new JLabel("", weatherImg, SwingUtilities.CENTER);
			break;
		case "3":
			sky = 3;
			ta_weatherInfo.append("구름많음\n");
			weatherImg = new ImageIcon("구름많음.jpg");
			useImg = weatherImg.getImage();
			useImg = useImg.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
			weatherImg = new ImageIcon(useImg);
			img = new JLabel("", weatherImg, SwingUtilities.CENTER);
			break;
		case "4":
			if(MainFrame.getWeather().get("PTY").equals("1")){
				sky = 4;
				ta_weatherInfo.append("비\n");
				weatherImg = new ImageIcon("비.jpg");
				useImg = weatherImg.getImage();
				useImg = useImg.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
				weatherImg = new ImageIcon(useImg);
				img = new JLabel("", weatherImg, SwingUtilities.CENTER);
			}
			else if(MainFrame.getWeather().get("PTY").equals("2")){
				sky = 5;
				ta_weatherInfo.append("진눈깨비\n");
				weatherImg = new ImageIcon("");
				useImg = weatherImg.getImage();
				useImg = useImg.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
				weatherImg = new ImageIcon(useImg);
				img = new JLabel("", weatherImg, SwingUtilities.CENTER);
			}
			else if(MainFrame.getWeather().get("PTY").equals("3")){
				sky = 6;
				ta_weatherInfo.append("눈\n");
				weatherImg = new ImageIcon("눈.jpg");
				useImg = weatherImg.getImage();
				useImg = useImg.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
				weatherImg = new ImageIcon(useImg);
				img = new JLabel("", weatherImg, SwingUtilities.CENTER);
			}	
			else if(MainFrame.getWeather().get("PTY").equals("0")){
				sky = 7;
				ta_weatherInfo.append("흐림\n");
				weatherImg = new ImageIcon("흐림.jpg");
				useImg = weatherImg.getImage();
				useImg = useImg.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
				weatherImg = new ImageIcon(useImg);
				img = new JLabel("", weatherImg, SwingUtilities.CENTER);
			}
			 break;
		}
		//미세먼지 출력
		ta_weatherInfo.append("미세먼지: ");
		ta_weatherInfo.append(calculateFineDust(MainFrame.getFineDust().get("pm10Value")));
		ta_weatherInfo.append("  ");
		//현재 기온과 체감온도 출력
		pn_temp.setLayout(new BorderLayout());
		pn_temp.setSize(15, 15);
		ta_tempNow.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		ta_tempNow.setEditable(false);
		ta_tempNow.setText(MainFrame.getWeather().get("T1H")+"°C");
		ta_sensibleTemp.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		ta_sensibleTemp.setEditable(false);
		ta_sensibleTemp.setText("체감: " + calculateSensibleTemp() + "°C");
		
		pn_temp.add(BorderLayout.NORTH, ta_tempNow);
		pn_temp.add(BorderLayout.SOUTH, ta_sensibleTemp);
		add("LEFT", img);
		add("CENTER", ta_weatherInfo);
		add("RIGHT", pn_temp);
	}
	
	/**
	 * 체감온도 계산
	 * @return
	 */
	String calculateSensibleTemp(){
		double v = Double.parseDouble(MainFrame.getWeather().get("WSD"))*3.6;
		double t = Double.parseDouble(MainFrame.getWeather().get("T1H"));
		double senTemp = 13.12 + 0.6215*t - 11.37*Math.pow(v,0.16) + 0.3965*Math.pow(v,0.16)*t;
		
		return Double.toString((int)senTemp);
	}
	/**
	 * 미세먼지 농도를 통한 예보
	 */
	String calculateFineDust(String dust){
		int dustValue = Integer.parseInt(dust);
		if(dustValue <= 30){
			fineDust = 0;
			return "좋음";
		}
		else if(dustValue <= 80){
			fineDust = 1;
			return "보통";
		}
		else if(dustValue <=150){
			fineDust = 2;
			return "나쁨";
		}
		else{
			fineDust = 3;
			return "매우나쁨";
		}
	}
}