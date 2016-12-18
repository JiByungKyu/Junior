package SubInfo;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import Util.ForecastMidParser;


/** 홍대표
 * 주간 날씨를 보여주는 프레임이다.
 * DrawGraph 클래스를 사용하여 그래프를 그렸고 날짜별 날씨를 받아와 나타내 준다.
 * 날씨 정보가 스트링으로 들어와 아이콘으로 나타내 주었다.
 */
public class weekWeather extends JFrame { 	//주간 날씨를 보여주는 프레임 

	private DrawGraph PN_Graph;		//그래프 패널
	private ForecastMidParser FM;	//중기예보 파서
	private HashMap<String, String> mapLand;	// 온도 해쉬맵
	private List<String> AMweather = new ArrayList<String>();	//오전 날씨 저장
	private List<String> PMweather = new ArrayList<String>();	//오후 날씨 저장
	
	public weekWeather(){
		this.setBackground(Color.WHITE);
		try {
			FM = new ForecastMidParser();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mapLand=FM.getLandHashMap();
		PN_Graph = new DrawGraph(FM);
		PN_Graph.setBounds(12, 10, 371, 268);
		PN_Graph.setLayout(null);
		
		
		this.setTitle("주간 날씨");
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.WHITE);
		
		JLabel LB_AMWeather = new JLabel("오전");	//오전 레이블
		JLabel LB_PMWeather = new JLabel("오후"); // 오후 레이블
		
		LB_AMWeather.setBounds(22, 317, 30, 30);
		LB_PMWeather.setBounds(22, 357, 30, 30);
		
		this.setSize(411,441);
		getContentPane().add(LB_AMWeather);
		getContentPane().add(LB_PMWeather);
		getContentPane().add(PN_Graph);
		for(int i=0;i<8;i++){
			String afterdays = getDate(i+3); //몇일 후 날짜를 반환해주는 메소드 호출
			int month=Integer.parseInt(afterdays.substring(4, 6));
			int day=Integer.parseInt(afterdays.substring(6, 8));
			JLabel label = new JLabel(month+"/"+day);
			label.setBounds(60+40*i, 298, 40, 15);
			getContentPane().add(label);
		}
		String am;
		String pm;
		String wf;
		for (int i = 3; i <= 10; i++) {
			if(i<=7){			//3~7일까지는 오전, 오후날씨가 다 옴
				am ="wf"+i+"Am";
				pm ="wf"+i+"Pm";		
				AMweather.add(mapLand.get(am));
				PMweather.add(mapLand.get(pm));
			}
			else{	//8~10일까지는 하루의 날씨만 와서 저장함
				wf="wf"+i;
				AMweather.add(mapLand.get(wf));
			}
				
		}
		
		
		for(int i=0;i<5;i++){	//3~7일 날씨 아이콘 셋팅
			JLabel AMimg = setweathericon(i, "AM");
			JLabel PMimg = setweathericon(i, "PM");
			AMimg.setBounds(60+40*i, 317, 30, 30);
			PMimg.setBounds(60+40*i, 357, 30, 30);
			this.add(AMimg);
			this.add(PMimg);
		}
		
		
		for(int i=5;i<8;i++){	//8~10일 날씨 아이콘 셋팅
			JLabel AMimg = setweathericon(i, "ALL");
			AMimg.setBounds(60+40*i, 337, 30, 30);
			this.add(AMimg);
		}
		
		
		this.setLocation(1105,93);
		
		this.setVisible(true);
	}
	/*
	 * 날씨 아이콘 세팅해주는 메소드
	 */
	public JLabel setweathericon(int i, String Time){	
		ImageIcon weatherImg;
		Image useImg;
		JLabel img;
		if(Time.equals("AM")){ //오전 날씨 아이콘 셋팅
			if((AMweather.get(i).contains("구름"))&&(AMweather.get(i).contains("비"))){	//구름과 비라는 단어가잇을 때
				weatherImg = new ImageIcon("DP_cloud_rain.jpg");
				useImg = weatherImg.getImage();
				useImg = useImg.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
				weatherImg = new ImageIcon(useImg);
				img = new JLabel("", weatherImg, SwingUtilities.CENTER);
				img.setBounds(60+40*i, 317, 30, 30);
				return img;
			}
			else if((AMweather.get(i).contains("눈"))&&(AMweather.get(i).contains("비"))){//눈과 비라는 단어가 있을때
				weatherImg = new ImageIcon("DP_rain_snow.png");
				useImg = weatherImg.getImage();
				useImg = useImg.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
				weatherImg = new ImageIcon(useImg);
				img = new JLabel("", weatherImg, SwingUtilities.CENTER);
				img.setBounds(60+40*i, 317, 30, 30);
				return img;
			}
			else if((AMweather.get(i).contains("눈"))){		//눈이라는 단어가 있을 때
				weatherImg = new ImageIcon("DP_snow.jpg");
				useImg = weatherImg.getImage();
				useImg = useImg.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
				weatherImg = new ImageIcon(useImg);
				img = new JLabel("", weatherImg, SwingUtilities.CENTER);
				img.setBounds(60+40*i, 317, 30, 30);
				return img;
			}if(AMweather.get(i).contains("비")){	//비라는 단어가 있을 때 
				weatherImg = new ImageIcon("DP_rain.jpg");
				useImg = weatherImg.getImage();
				useImg = useImg.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
				weatherImg = new ImageIcon(useImg);
				img = new JLabel("", weatherImg, SwingUtilities.CENTER);
				img.setBounds(60+40*i, 317, 30, 30);
				return img;
			}
			else if((AMweather.get(i).contains("구름"))){	//구름만 있을 때
				if((AMweather.get(i).contains("많음"))){	//구름에 양에따라
					weatherImg = new ImageIcon("DP_manycloud.jpg");
				}else if((AMweather.get(i).contains("조금"))){
					weatherImg = new ImageIcon("DP_lesscloud.jpg");
				}else{
					weatherImg = new ImageIcon("DP_cloud.jpg");
				}
				useImg = weatherImg.getImage();
				useImg = useImg.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
				weatherImg = new ImageIcon(useImg);
				img = new JLabel("", weatherImg, SwingUtilities.CENTER);
				img.setBounds(60+40*i, 317, 30, 30);
				return img;
			}else {		//걸리는 날씨가 없으면 맑음 표시
				weatherImg = new ImageIcon("DP_sunny.jpg");
				useImg = weatherImg.getImage();
				useImg = useImg.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
				weatherImg = new ImageIcon(useImg);
				img = new JLabel("", weatherImg, SwingUtilities.CENTER);
				return img;
			}
			
			
			
		}else if(Time.equals("PM")){	//오후 아이콘도 똑같이 한다.
			if((PMweather.get(i).contains("구름"))&&(AMweather.get(i).contains("비"))){
				weatherImg = new ImageIcon("DP_cloud_rain.jpg");
				useImg = weatherImg.getImage();
				useImg = useImg.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
				weatherImg = new ImageIcon(useImg);
				img = new JLabel("", weatherImg, SwingUtilities.CENTER);
				img.setBounds(60+40*i, 317, 30, 30);
				return img;
			}
			else if((PMweather.get(i).contains("눈"))&&(PMweather.get(i).contains("비"))){
				weatherImg = new ImageIcon("DP_rain_snow.png");
				useImg = weatherImg.getImage();
				useImg = useImg.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
				weatherImg = new ImageIcon(useImg);
				img = new JLabel("", weatherImg, SwingUtilities.CENTER);
				img.setBounds(60+40*i, 317, 30, 30);
				return img;
			}
			else if(PMweather.get(i).contains("비")){
				weatherImg = new ImageIcon("DP_rain.jpg");
				useImg = weatherImg.getImage();
				useImg = useImg.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
				weatherImg = new ImageIcon(useImg);
				img = new JLabel("", weatherImg, SwingUtilities.CENTER);
				img.setBounds(60+40*i, 357, 30, 30);
				return img;
			}else if(PMweather.get(i).contains("눈")){
				weatherImg = new ImageIcon("DP_snow.jpg");
				useImg = weatherImg.getImage();
				useImg = useImg.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
				weatherImg = new ImageIcon(useImg);
				img = new JLabel("", weatherImg, SwingUtilities.CENTER);
				img.setBounds(60+40*i, 357, 30, 30);
				return img;
			}else if((PMweather.get(i).contains("구름"))){
				if((PMweather.get(i).contains("많음"))){
					weatherImg = new ImageIcon("DP_manycloud.jpg");
				}else if((PMweather.get(i).contains("조금"))){
					weatherImg = new ImageIcon("DP_lesscloud.jpg");
				}else{
					weatherImg = new ImageIcon("DP_cloud.jpg");
				}
				useImg = weatherImg.getImage();
				useImg = useImg.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
				weatherImg = new ImageIcon(useImg);
				img = new JLabel("", weatherImg, SwingUtilities.CENTER);
				img.setBounds(60+40*i, 357, 30, 30);
				return img;
			}else{
				weatherImg = new ImageIcon("DP_sunny.jpg");
				useImg = weatherImg.getImage();
				useImg = useImg.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
				weatherImg = new ImageIcon(useImg);
				img = new JLabel("", weatherImg, SwingUtilities.CENTER);
				return img;
			}			
		}else{
			if((AMweather.get(i).contains("구름"))&&(AMweather.get(i).contains("비"))){
				weatherImg = new ImageIcon("DP_cloud_rain.jpg");
				useImg = weatherImg.getImage();
				useImg = useImg.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
				weatherImg = new ImageIcon(useImg);
				img = new JLabel("", weatherImg, SwingUtilities.CENTER);
				img.setBounds(60+40*i, 317, 30, 30);
				return img;
			}
			else if((AMweather.get(i).contains("눈"))&&(AMweather.get(i).contains("비"))){
				weatherImg = new ImageIcon("DP_rain_snow.png");
				useImg = weatherImg.getImage();
				useImg = useImg.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
				weatherImg = new ImageIcon(useImg);
				img = new JLabel("", weatherImg, SwingUtilities.CENTER);
				img.setBounds(60+40*i, 317, 30, 30);
				return img;
			}
			if(AMweather.get(i).contains("비")){
				weatherImg = new ImageIcon("DP_rain.jpg");
				useImg = weatherImg.getImage();
				useImg = useImg.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
				weatherImg = new ImageIcon(useImg);
				img = new JLabel("", weatherImg, SwingUtilities.CENTER);
				return img;
			}else if((AMweather.get(i).contains("눈"))){
				weatherImg = new ImageIcon("DP_snow.jpg");
				useImg = weatherImg.getImage();
				useImg = useImg.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
				weatherImg = new ImageIcon(useImg);
				img = new JLabel("", weatherImg, SwingUtilities.CENTER);
				return img;
			}else if((AMweather.get(i).contains("구름"))){
				if((AMweather.get(i).contains("많음"))){
					weatherImg = new ImageIcon("DP_manycloud.jpg");
				}else if((AMweather.get(i).contains("조금"))){
					weatherImg = new ImageIcon("DP_lesscloud.jpg");
				}else{
					weatherImg = new ImageIcon("DP_cloud.jpg");
				}
				useImg = weatherImg.getImage();
				useImg = useImg.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
				weatherImg = new ImageIcon(useImg);
				img = new JLabel("", weatherImg, SwingUtilities.CENTER);
				return img;
			}else{
				weatherImg = new ImageIcon("DP_sunny.jpg");
				useImg = weatherImg.getImage();
				useImg = useImg.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
				weatherImg = new ImageIcon(useImg);
				img = new JLabel("", weatherImg, SwingUtilities.CENTER);
				return img;
			}
			
		}
	}

	
	
	public static String getDate ( int iDay ) {	//iDay 후의 날짜를 반환해주는 메소드
		 Calendar temp=Calendar.getInstance ( );
		 String inTime   = new java.text.SimpleDateFormat("HHmmss").format(new java.util.Date());
		 if(Integer.parseInt(inTime)<60000){//시간대가 자정~ 6시면 1일 빼줌(파서 정보와 호환하기 위해)
			 iDay-=1;
		 }
		 temp.add ( Calendar.DAY_OF_MONTH, iDay );
		 int nYear = temp.get ( Calendar.YEAR );
		 int nMonth = temp.get ( Calendar.MONTH ) + 1;
		 int nDay = temp.get ( Calendar.DAY_OF_MONTH );
		 StringBuffer sbDate=new StringBuffer ( );
		 sbDate.append ( nYear );
		 if ( nMonth < 10 ) sbDate.append ( "0" );
		 sbDate.append ( nMonth );
		 if ( nDay < 10 ) sbDate.append ( "0" );
		 sbDate.append ( nDay );
		 return sbDate.toString ( );
		 }
}
