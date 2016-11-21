package JDP;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;



public class weekWeather extends JFrame {
	private List<Integer> scores = new ArrayList<Integer>();		//데이터 저장된 리스트
	//private int maxDataPoints;		//데이터 갯수
	private DrawGraph PN_Graph;		//그래프 패널
	
	private List<Integer> points_x;
	
	public weekWeather(){

		PN_Graph = new DrawGraph();
		PN_Graph.setBounds(12, 10, 371, 268);
		PN_Graph.setLayout(null);
		
		this.setTitle("주간 날씨");
		getContentPane().setLayout(null);
		
		JLabel LB_AMWeather = new JLabel("<html>  오전  <br>  날씨  </html>");
		JLabel LB_PMWeather = new JLabel("<html>  오후  <br>  날씨  </html>");
		
		LB_AMWeather.setBounds(22, 310, 35, 30);
		LB_PMWeather.setBounds(22, 346, 35, 30);
		
		points_x = PN_Graph.getpoints_x();
		
		for(int i=3;i<3+points_x.size();i++){
			String afterdays = getDate(i);
			int month=Integer.parseInt(afterdays.substring(4, 5));
			int day=Integer.parseInt(afterdays.substring(6, 7));
			JLabel dayz = new JLabel(month+"/"+day);
			dayz.setBounds(points_x.get(i-3)-10,350,50,40);
			getContentPane().add(dayz);
		}
		
		this.setSize(411,441);
		getContentPane().add(LB_AMWeather);
		getContentPane().add(LB_PMWeather);
		getContentPane().add(PN_Graph);
		
		this.setLocation(500,400);
		this.setVisible(true);
	}
	
	
	public static String getDate ( int iDay ) {
		 Calendar temp=Calendar.getInstance ( );
		 String inTime   = new java.text.SimpleDateFormat("HHmmss").format(new java.util.Date());
		 if(Integer.parseInt(inTime)<60000){
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
