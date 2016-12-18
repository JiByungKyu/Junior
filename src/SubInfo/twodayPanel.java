package SubInfo;

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

import TaskManager.MainFrame;
import Util.ForecastParser;
/** 홍대표
 * 동내 예보(약 2일간의 자세한 날씨) 정보를 보여주는 패널이다.
 * 날짜의 시간대별(3시간 간격)로 온도, 날씨, 체감온도 등등을 보여준다.
 */
public class twodayPanel extends JPanel 	//이틀간 날씨(동네 예보)를 보여주는 패널이다.
{
	private ArrayList<String> dayList =  new ArrayList<String>();
	private int daytemplocation;	//최고, 최저기온 위치 설정 변수
	private ForecastParser FP;		//동네 예보 파서
	
	public twodayPanel()
	{
		FP = MainFrame.getParser();
		for(int i=0;i<FP.getNoTime();i++) // 파서 시간대별  정보갯수 받아오기
		{
			Iterator<String> iter = FP.getMapSpace()[i].keySet().iterator(); //iterator를 이용해서 검사함
			while (iter.hasNext()) 	//하나의 시간대 정보 검사
			{
				String key = (String) iter.next();
				if(key.equals("T3H"))	//온도 정보 설정
				{
					JLabel LB_label = new JLabel(FP.getMapSpace()[i].get(key)+"°C");
					LB_label.setBounds(200, i*20+50, 30, 15);
					add(LB_label);
				}
				if(key.equals("fcstDate")&&!dayList.contains(FP.getMapSpace()[i].get(key)))// 날짜 설정
				{
					int month = Integer.parseInt(FP.getMapSpace()[i].get(key).substring(4, 6));;
					int day = Integer.parseInt(FP.getMapSpace()[i].get(key).substring(6, 8));
					
					dayList.add(FP.getMapSpace()[i].get(key));
					JLabel LB_label = new JLabel(month+"월 "+day+"일");
					LB_label.setBounds(20, i*20+50, 100, 15);
					daytemplocation=(i+1)*20+50;
					add(LB_label);
				}
				
				if(key.equals("SKY")) // 하늘 상태 (아이콘) 설정
				{
					ImageIcon weatherImg;
					Image useImg;
					JLabel img;
					if(FP.getMapSpace()[i].get(key).equals("1"))	//맑음
					{
						weatherImg = new ImageIcon("DP_sunny.jpg");
						useImg = weatherImg.getImage();
						useImg = useImg.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
						weatherImg = new ImageIcon(useImg);
						img = new JLabel("", weatherImg, SwingUtilities.CENTER);
						img.setBounds(150, i*20+50, 20, 20);
						add(img);
					}else if(FP.getMapSpace()[i].get(key).equals("2"))	//구름조금
					{
						weatherImg = new ImageIcon("DP_lesscloud.jpg");
						useImg = weatherImg.getImage();
						useImg = useImg.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
						weatherImg = new ImageIcon(useImg);
						img = new JLabel("", weatherImg, SwingUtilities.CENTER);
						img.setBounds(150, i*20+50, 20, 20);
						add(img);
					}else if(FP.getMapSpace()[i].get(key).equals("3"))	//구름많음
					{
						weatherImg = new ImageIcon("DP_manycloud.jpg");
						useImg = weatherImg.getImage();
						useImg = useImg.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
						weatherImg = new ImageIcon(useImg);
						img = new JLabel("", weatherImg, SwingUtilities.CENTER);
						img.setBounds(150, i*20+50, 20, 20);
						add(img);
					}else if(FP.getMapSpace()[i].get(key).equals("4"))		//흐림
					{
						if(FP.getMapSpace()[i].get("PTY").equals("0"))	//흐리기만함
						{
							weatherImg = new ImageIcon("DP_cloud.jpg");
							useImg = weatherImg.getImage();
							useImg = useImg.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
							weatherImg = new ImageIcon(useImg);
							img = new JLabel("", weatherImg, SwingUtilities.CENTER);
							img.setBounds(150, i*20+50, 20, 20);
							add(img);
						}else if(FP.getMapSpace()[i].get("PTY").equals("1"))	//흐리고 비옴
						{
							weatherImg = new ImageIcon("DP_rain.jpg");
							useImg = weatherImg.getImage();
							useImg = useImg.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
							weatherImg = new ImageIcon(useImg);
							img = new JLabel("", weatherImg, SwingUtilities.CENTER);
							img.setBounds(150, i*20+50, 20, 20);
							add(img);
						}else if(FP.getMapSpace()[i].get("PTY").equals("2"))	//흐리고 눈,비옴
						{
							weatherImg = new ImageIcon("DP_rain_snow.png");
							useImg = weatherImg.getImage();
							useImg = useImg.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
							weatherImg = new ImageIcon(useImg);
							img = new JLabel("", weatherImg, SwingUtilities.CENTER);
							img.setBounds(150, i*20+50, 20, 20);
							add(img);
						}else if(FP.getMapSpace()[i].get("PTY").equals("3"))	//흐리고 눈옴
						{
							weatherImg = new ImageIcon("DP_snow.jpg");
							useImg = weatherImg.getImage();
							useImg = useImg.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
							weatherImg = new ImageIcon(useImg);
							img = new JLabel("", weatherImg, SwingUtilities.CENTER);
							img.setBounds(150, i*20+50, 20, 20);
							add(img);
						}
					}
				}
				
				if(key.equals("fcstTime"))	//시간 설정
				{
					int hour = Integer.parseInt(FP.getMapSpace()[i].get(key).substring(0, 2));
					
					JLabel LB_label = new JLabel(hour+"시");
					LB_label.setBounds(100, i*20+50, 30, 15);
					add(LB_label);
				}
				
				if(key.equals("TMN"))	//최저 온도 설정
				{	
					
					JLabel LB_label = new JLabel(FP.getMapSpace()[i].get(key)+"°C");
					LB_label.setOpaque(true);
					LB_label.setBackground(Color.WHITE);
					LB_label.setForeground(Color.BLUE);
					LB_label.setBounds(20, daytemplocation, 30, 15);
					add(LB_label);
				}
				if(key.equals("TMX"))	//최고 온도 설정
				{	
					
					JLabel LB_label = new JLabel(FP.getMapSpace()[i].get(key)+"°C");
					LB_label.setOpaque(true);
					LB_label.setBackground(Color.WHITE);
					LB_label.setForeground(Color.RED);
					LB_label.setBounds(50, daytemplocation, 30, 15);
					add(LB_label);
				}
			}
			JLabel realtemp = new JLabel(calculateSensibleTemp(i)+"°C");//체감온도 설정
			realtemp.setBounds(260, i*20+50, 30, 15);
			add(realtemp);
		}
		JLabel LB_sensibleTemp = new JLabel("체감온도");
		LB_sensibleTemp.setBounds(245, 30, 60, 15);
		add(LB_sensibleTemp);
		JLabel LB_date = new JLabel("날짜");
		LB_date.setBounds(20, 30, 30, 15);
		add(LB_date);
		JLabel LB_time = new JLabel("시간");
		LB_time.setBounds(100, 30, 30, 15);
		add(LB_time);
		JLabel LB_weather = new JLabel("날씨");
		LB_weather.setBounds(150, 30, 30, 15);
		add(LB_weather);
		JLabel LB_temp = new JLabel("온도");
		LB_temp.setBounds(200, 30, 30, 15);
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
		button.addActionListener(new ActionListener() 	//주간날씨보기 클릭시 새 프레임 띄워서 보여줌
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				new weekWeather();
			}
		});
		button.setBounds(178, 0, 113, 23);
		add(button);
	}
	/**
	 * 체감온도 계산
	 * @return
	 */
	public String calculateSensibleTemp(int index)	
	{
		double v = Double.parseDouble(FP.getMapSpace()[index].get("WSD"))*3.6;
		double t = Double.parseDouble(FP.getMapSpace()[index].get("T3H"));
		double senTemp = 13.12 + 0.6215*t - 11.37*Math.pow(v,0.16) + 0.3965*Math.pow(v,0.16)*t;
		
		return Integer.toString((int)senTemp);
	}
}
