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

public class WeatherPanel extends JPanel {
	String location = "충무로";
	String rain = "강수확률: ";
	
	JPanel pn_temp;
	JTextArea ta_weatherInfo;
	JTextArea ta_tempNow;
	ImageIcon weatherImg;
	Image useImg;
	JLabel img;
	
	WeatherPanel(){		
		setBackground(Color.WHITE);
		pn_temp = new JPanel();
		ta_weatherInfo = new JTextArea();
		ta_tempNow = new JTextArea();
		
		weatherImg = new ImageIcon("sunny.jpg");
		useImg = weatherImg.getImage();
		useImg = useImg.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
		weatherImg = new ImageIcon(useImg);
		img = new JLabel("", weatherImg, SwingUtilities.CENTER);
		
		
		ta_weatherInfo.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		ta_weatherInfo.setEditable(false);
		ta_weatherInfo.setBackground(Color.WHITE);
		ta_weatherInfo.setText(location + "\n");
		ta_weatherInfo.append("맑음\n");
		ta_weatherInfo.append(rain);
		ta_weatherInfo.append("%       ");
		
		pn_temp.setLayout(new BorderLayout());
		pn_temp.setSize(15, 15);
		ta_tempNow.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		ta_tempNow.setText("8°C");
		
		pn_temp.add(BorderLayout.NORTH, ta_tempNow);
		
		add("LEFT", img);
		add("CENTER", ta_weatherInfo);
		add("RIGHT", pn_temp);
	}
}