package JDP;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DrawGraph extends JPanel {
   private int MAX_SCORE ;			//자료의 범위
   private static final int BORDER_GAP = 30;		// 판넬과의 거리
   private static final Color GRAPH_COLOR = Color.BLACK;	//선 색깔
   private static final Color MIN_POINT_COLOR = Color.BLUE;	//점 색깔
   private static final Color MAX_POINT_COLOR = Color.RED;	//점 색깔
   private static final Stroke GRAPH_STROKE = new BasicStroke(3f);	//선 굵기 등
   private static final int GRAPH_POINT_WIDTH = 10;	//점 길이
   private  int Y_HATCH_CNT;	//y축 나눌 수
   private HashMap<String, String> mapLand;
   private HashMap<String, String> mapTemp;
   private List<Integer> minTemps = new ArrayList<Integer>();
   private List<Integer> maxTemps = new ArrayList<Integer>();
   
   private int DataSize = 8;
   private String max;
   private String min;
   private ForecastMidParser FM;
   private int setzero;
   private int daymaxTemp;
   private int dayminTemp;
   public DrawGraph(ForecastMidParser FM) {
     
	   	this.FM = FM;
		
		mapLand = FM.getLandHashMap();	//날씨 3~8일까지 오전 오후로, 
		mapTemp = FM.getTempHashMap();	//온도 (하루1개)
		
      
      
      
      for (int i = 3; i <= 10; i++) {
			min="taMin"+i;
			max="taMax"+i;
			minTemps.add(Integer.parseInt(mapTemp.get(min)));
			maxTemps.add(Integer.parseInt(mapTemp.get(max)));
      }
      
      
      daymaxTemp = -40;
      dayminTemp = 50;
      
      for(int i=3;i<=10;i++){
    	  	min="taMin"+i;
			max="taMax"+i;
			if(dayminTemp > Integer.parseInt(mapTemp.get(min))){
				dayminTemp =Integer.parseInt(mapTemp.get(min));
			}
			if(daymaxTemp < Integer.parseInt(mapTemp.get(max))){
				daymaxTemp = Integer.parseInt(mapTemp.get(max));
			}
      }
      if(daymaxTemp>0){
    	  daymaxTemp=(daymaxTemp/10)*10+10;
      }else{
    	  daymaxTemp=(daymaxTemp/10)*10;
      }
      if(dayminTemp>=0){
    	  dayminTemp=(dayminTemp/10)*10;
      }else{
    	  dayminTemp=(dayminTemp/10)*10-10;
      }
      
      
      MAX_SCORE = daymaxTemp-dayminTemp;
      Y_HATCH_CNT = MAX_SCORE/10;
      
      
   }

   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D)g;
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);	//화질 깨끗하게
     

      double xScale = ((double) getWidth() - 2 * BORDER_GAP) / (DataSize);
      double yScale = ((double) getHeight() - 2 * BORDER_GAP) / (MAX_SCORE - 1);
      
      
      if( dayminTemp < 0 && dayminTemp>=-10){
    	  setzero=-10;
      }else if(dayminTemp<-10 && dayminTemp>=-20){
    	  setzero=-20;
      }else if(dayminTemp>-20 && dayminTemp>=-30){
    	  setzero=-30;
      }else if(dayminTemp>=0 && dayminTemp<10){
    	  setzero=0;
      }else if(dayminTemp>=10 && dayminTemp<20){
    	  setzero=10;
      }else if(dayminTemp>=20 && dayminTemp<30){
    	  setzero=20;
      }else{setzero=0;}
      
      
      
      
      List<Point> minPoints = new ArrayList<Point>();
      for (int i = 0; i < DataSize; i++) {
         int x1 = (int) ((i+1) * xScale + BORDER_GAP);
         int y1 = (int) ((MAX_SCORE - (minTemps.get(i)-setzero)) * yScale + BORDER_GAP);	//30을 더해서 기준점 변경
         minPoints.add(new Point(x1, y1));
         
      }
      
      List<Point> maxPoints = new ArrayList<Point>();
      for (int i = 0; i < DataSize; i++) {
         int x1 = (int) ((i+1) * xScale + BORDER_GAP);
         int y1 = (int) ((MAX_SCORE - (maxTemps.get(i)-setzero)) * yScale + BORDER_GAP);	//30을 더해서 기준점 변경
         maxPoints.add(new Point(x1, y1));
         int x = minPoints.get(i).x - GRAPH_POINT_WIDTH / 2;
        
      }
      
      int var=-setzero/10;
      // create x and y axes 
      g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, BORDER_GAP, BORDER_GAP);//y축
     // g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, getWidth() - BORDER_GAP, getHeight() - BORDER_GAP);
      g2.drawLine(BORDER_GAP,  getHeight() - ((var* (getHeight() - BORDER_GAP * 2)) / Y_HATCH_CNT + BORDER_GAP)
    		  , getWidth() - BORDER_GAP,  getHeight() - ((var* (getHeight() - BORDER_GAP * 2)) / Y_HATCH_CNT + BORDER_GAP));//x축 선
      // create hatch marks for y axis. 
      for (int i = 0; i <= Y_HATCH_CNT; i++) {
         int x0 = BORDER_GAP;
         int x1 = GRAPH_POINT_WIDTH + BORDER_GAP;
         int y0 = getHeight() - (((i) * (getHeight() - BORDER_GAP * 2)) / Y_HATCH_CNT + BORDER_GAP);
         int y1 = y0;
         g2.drawLine(x0, y0, x1, y1);
         
         
      
         String yLabel = Integer.toString((i+setzero/10)*10);
         FontMetrics metrics = g2.getFontMetrics();
         int labelWidth = metrics.stringWidth(yLabel);
      	 g2.drawString(yLabel, x0 - labelWidth - 5, y0);
      	 
      	 if(i==Y_HATCH_CNT){
      		 g2.drawString("(°C)", x0 - labelWidth - 8, y0+12);
      	 }
         
      }
      
      
      
      // and for x axis
      for (int i = 0; i < DataSize ; i++) {
         int x0 = (i + 1) * (getWidth() - BORDER_GAP * 2) / (DataSize) + BORDER_GAP;
         int x1 = x0; 
         int y0 =  getHeight() - ( (var* (getHeight() - BORDER_GAP * 2) ) / Y_HATCH_CNT + BORDER_GAP )+GRAPH_POINT_WIDTH;
         int y1 = y0 - 2*GRAPH_POINT_WIDTH;
         g2.drawLine(x0, y0, x1, y1);
      }

      Stroke oldStroke = g2.getStroke();
      g2.setColor(GRAPH_COLOR);
      g2.setStroke(GRAPH_STROKE);
      for (int i = 0; i < minPoints.size() - 1; i++) {//최저 기온 그리기
         int x1 = minPoints.get(i).x;
         int y1 = minPoints.get(i).y;
         int x2 = minPoints.get(i + 1).x;
         int y2 = minPoints.get(i + 1).y;
         g2.drawLine(x1, y1, x2, y2);         
      }

      g2.setStroke(oldStroke);      
      g2.setColor(MIN_POINT_COLOR);
      for (int i = 0; i < minPoints.size(); i++) {
         int x = minPoints.get(i).x - GRAPH_POINT_WIDTH / 2;
         int y = minPoints.get(i).y - GRAPH_POINT_WIDTH / 2;
         int ovalW = GRAPH_POINT_WIDTH;
         int ovalH = GRAPH_POINT_WIDTH;
         g2.fillOval(x, y, ovalW, ovalH);
         
        
         
         min="taMin"+(i+3);
		 
		 String temper = mapTemp.get(min);
		 g2.drawString(temper, x+3, y-3);
      }
      
      g2.setColor(GRAPH_COLOR);
      g2.setStroke(GRAPH_STROKE);
      for (int i = 0; i < maxPoints.size() - 1; i++) {//최고 기온 그리기
          int x1 = maxPoints.get(i).x;
          int y1 = maxPoints.get(i).y;
          int x2 = maxPoints.get(i + 1).x;
          int y2 = maxPoints.get(i + 1).y;
          g2.drawLine(x1, y1, x2, y2);         
       }

       g2.setStroke(oldStroke);      
       g2.setColor(MAX_POINT_COLOR);
       for (int i = 0; i < maxPoints.size(); i++) {
          int x = maxPoints.get(i).x - GRAPH_POINT_WIDTH / 2;
          int y = maxPoints.get(i).y - GRAPH_POINT_WIDTH / 2;;
          int ovalW = GRAPH_POINT_WIDTH;
          int ovalH = GRAPH_POINT_WIDTH;
          g2.fillOval(x, y, ovalW, ovalH);
          
         
 		  max="taMax"+(i+3);
 		  String temper = mapTemp.get(max);
 		  g2.drawString(temper, x+3, y-3);
 		  
       }
      
   }
   
}