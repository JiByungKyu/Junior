package HDP;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import weather.ForecastParser;

public class twodayPanel extends JPanel {
	private ArrayList<String> dayList =  new ArrayList<String>();
	private int daytemplocation;
	public twodayPanel(ForecastParser FP){

		for(int i=0;i<FP.getNoTime();i++){
			//System.out.println(FP.mapSpace[i].size());
			Iterator<String> iter = FP.getMapSpace()[i].keySet().iterator();
			
			while (iter.hasNext()) {
			
				String key = (String) iter.next();
				System.out.print("key=" + key);
				System.out.println(" value=" + FP.getMapSpace()[i].get(key));
				if(key.equals("T3H")){
					JLabel LB_label = new JLabel(FP.getMapSpace()[i].get(key)+"°C");
					LB_label.setBounds(250, i*20+50, 30, 15);
					add(LB_label);
				}
				if(key.equals("fcstDate")&&!dayList.contains(FP.getMapSpace()[i].get(key))){
					int month = Integer.parseInt(FP.getMapSpace()[i].get(key).substring(4, 6));;
					int day = Integer.parseInt(FP.getMapSpace()[i].get(key).substring(6, 8));
					
					dayList.add(FP.getMapSpace()[i].get(key));
					JLabel LB_label = new JLabel(month+"월 "+day+"일");
					LB_label.setBounds(20, i*20+50, 100, 15);
					daytemplocation=i*20+50;
					add(LB_label);
				}
				
				if(key.equals("SKY")){
					ImageIcon weatherImg;
					Image useImg;
					JLabel img;
					if(FP.getMapSpace()[i].get(key).equals("1")){
						weatherImg = new ImageIcon("DP_sunny.jpg");
						useImg = weatherImg.getImage();
						useImg = useImg.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
						weatherImg = new ImageIcon(useImg);
						img = new JLabel("", weatherImg, SwingUtilities.CENTER);
						img.setBounds(200, i*20+50, 20, 20);
						add(img);
					}else if(FP.getMapSpace()[i].get(key).equals("2")){
						weatherImg = new ImageIcon("DP_lesscloud.jpg");
						useImg = weatherImg.getImage();
						useImg = useImg.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
						weatherImg = new ImageIcon(useImg);
						img = new JLabel("", weatherImg, SwingUtilities.CENTER);
						img.setBounds(200, i*20+50, 20, 20);
						add(img);
					}else if(FP.getMapSpace()[i].get(key).equals("3")){
						weatherImg = new ImageIcon("DP_manycloud.jpg");
						useImg = weatherImg.getImage();
						useImg = useImg.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
						weatherImg = new ImageIcon(useImg);
						img = new JLabel("", weatherImg, SwingUtilities.CENTER);
						img.setBounds(200, i*20+50, 20, 20);
						add(img);
					}else if(FP.getMapSpace()[i].get(key).equals("4")){
						if(FP.getMapSpace()[i].get("PTY").equals("0")){
							weatherImg = new ImageIcon("DP_cloud.jpg");
							useImg = weatherImg.getImage();
							useImg = useImg.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
							weatherImg = new ImageIcon(useImg);
							img = new JLabel("", weatherImg, SwingUtilities.CENTER);
							img.setBounds(200, i*20+50, 20, 20);
							add(img);
						}else if(FP.getMapSpace()[i].get("PTY").equals("1")){
							weatherImg = new ImageIcon("DP_rain.jpg");
							useImg = weatherImg.getImage();
							useImg = useImg.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
							weatherImg = new ImageIcon(useImg);
							img = new JLabel("", weatherImg, SwingUtilities.CENTER);
							img.setBounds(200, i*20+50, 20, 20);
							add(img);
						}else if(FP.getMapSpace()[i].get("PTY").equals("2")){
							weatherImg = new ImageIcon("DP_rain_snow.png");
							useImg = weatherImg.getImage();
							useImg = useImg.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
							weatherImg = new ImageIcon(useImg);
							img = new JLabel("", weatherImg, SwingUtilities.CENTER);
							img.setBounds(200, i*20+50, 20, 20);
							add(img);
						}else if(FP.getMapSpace()[i].get("PTY").equals("3")){
							weatherImg = new ImageIcon("DP_snow.jpg");
							useImg = weatherImg.getImage();
							useImg = useImg.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
							weatherImg = new ImageIcon(useImg);
							img = new JLabel("", weatherImg, SwingUtilities.CENTER);
							img.setBounds(200, i*20+50, 20, 20);
							add(img);
						}
					}
					
					
				}
				
				if(key.equals("fcstTime")){
					int hour = Integer.parseInt(FP.getMapSpace()[i].get(key).substring(0, 2));
					
					JLabel LB_label = new JLabel(hour+"시");
					LB_label.setBounds(150, i*20+50, 30, 15);
					add(LB_label);
				}
				
				if(key.equals("TMN")){	//최저
					
					JLabel LB_label = new JLabel(FP.getMapSpace()[i].get(key)+"°C");
					LB_label.setOpaque(true);
					LB_label.setBackground(Color.WHITE);
					LB_label.setForeground(Color.BLUE);
					LB_label.setBounds(90, daytemplocation, 30, 15);
					add(LB_label);
				}
				if(key.equals("TMX")){	//최고
					
					JLabel LB_label = new JLabel(FP.getMapSpace()[i].get(key)+"°C");
					LB_label.setOpaque(true);
					LB_label.setBackground(Color.WHITE);
					LB_label.setForeground(Color.RED);
					LB_label.setBounds(120, daytemplocation, 30, 15);
					add(LB_label);
				}
				
				
			}
		}
		
		/*JLabel LB_intro = new JLabel("이틀간 날씨");
		LB_intro.setFont(new Font("한컴돋움", Font.BOLD, 16));
		LB_intro.setBounds(50, 10, 98, 20);
		add(LB_intro);*/
		JLabel LB_date = new JLabel("날짜");
		LB_date.setBounds(20, 30, 30, 15);
		add(LB_date);
		JLabel LB_time = new JLabel("시간");
		LB_time.setBounds(150, 30, 30, 15);
		add(LB_time);
		JLabel LB_weather = new JLabel("날씨");
		LB_weather.setBounds(200, 30, 30, 15);
		add(LB_weather);
		JLabel LB_temp = new JLabel("온도");
		LB_temp.setBounds(245, 30, 30, 15);
		add(LB_temp);
		
		setBackground(Color.WHITE);
		setLayout(null);
		String getYear=Integer.toString(FP.getFE().make_SpaceDate()).substring(0, 4);
		String getMonth=Integer.toString(FP.getFE().make_SpaceDate()).substring(4, 6);
		String getDay=Integer.toString(FP.getFE().make_SpaceDate()).substring(6);
		String getTime=Integer.toString(FP.getFE().make_SpaceTime()/100);
		JLabel getDate= new JLabel(getYear+"년 "+getMonth+"월 "+getDay+"일 "
				+getTime+"시 정보");
		getDate.setBounds(10, 0, 163, 23);
		add(getDate);
		JButton button = new JButton("주간날씨보기");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new weekWeather();
			}
		});
		button.setBounds(178, 0, 113, 23);
		add(button);
	}
}