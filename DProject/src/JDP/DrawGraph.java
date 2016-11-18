package JDP;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DrawGraph extends JPanel {
   private static final int MAX_SCORE = 70;			//자료의 범위
   private static final int PREF_W = 800;
   private static final int PREF_H = 650;
   private static final int BORDER_GAP = 30;		// 판넬과의 거리
   private static final Color GRAPH_COLOR = Color.BLACK;	//선 색깔
   private static final Color GRAPH_POINT_COLOR = new Color(150, 50, 50, 180);	//점 색깔
   private static final Stroke GRAPH_STROKE = new BasicStroke(3f);	//선 굵기 등
   private static final int GRAPH_POINT_WIDTH = 10;	//점 길이
   private static final int Y_HATCH_CNT = 7;	//y축 나눌 수
   private List<Integer> scores;

   public DrawGraph(List<Integer> scores) {
      this.scores = scores;
   }

   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D)g;
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);	//화질 깨끗하게

      double xScale = ((double) getWidth() - 2 * BORDER_GAP) / (scores.size() - 1);
      double yScale = ((double) getHeight() - 2 * BORDER_GAP) / (MAX_SCORE - 1);

      List<Point> graphPoints = new ArrayList<Point>();
      for (int i = 0; i < scores.size(); i++) {
         int x1 = (int) (i * xScale + BORDER_GAP);
         int y1 = (int) ((MAX_SCORE - (scores.get(i)+30)) * yScale + BORDER_GAP);	//30을 더해서 기준점 변경
         graphPoints.add(new Point(x1, y1));
      }

      // create x and y axes 
      g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, BORDER_GAP, BORDER_GAP);
     // g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, getWidth() - BORDER_GAP, getHeight() - BORDER_GAP);
      g2.drawLine(BORDER_GAP,  getHeight() - ((3* (getHeight() - BORDER_GAP * 2)) / Y_HATCH_CNT + BORDER_GAP)
    		  , getWidth() - BORDER_GAP,  getHeight() - ((3* (getHeight() - BORDER_GAP * 2)) / Y_HATCH_CNT + BORDER_GAP));//x축 선
      // create hatch marks for y axis. 
      for (int i = 0; i <= Y_HATCH_CNT; i++) {
         int x0 = BORDER_GAP;
         int x1 = GRAPH_POINT_WIDTH + BORDER_GAP;
         int y0 = getHeight() - (((i) * (getHeight() - BORDER_GAP * 2)) / Y_HATCH_CNT + BORDER_GAP);
         int y1 = y0;
         g2.drawLine(x0, y0, x1, y1);
         
         String yLabel = Integer.toString((i-3)*10);
         FontMetrics metrics = g2.getFontMetrics();
         int labelWidth = metrics.stringWidth(yLabel);
         g2.drawString(yLabel, x0 - labelWidth - 5, y0);//+(((getHeight() - BORDER_GAP * 2)) / Y_HATCH_CNT ));
         
      }

      // and for x axis
      for (int i = 0; i < scores.size() - 1; i++) {
         int x0 = (i + 1) * (getWidth() - BORDER_GAP * 2) / (scores.size() - 1) + BORDER_GAP;
         int x1 = x0;
         int y0 =  getHeight() - ((3* (getHeight() - BORDER_GAP * 2)) / Y_HATCH_CNT + BORDER_GAP)+GRAPH_POINT_WIDTH;
         int y1 = y0 - 2*GRAPH_POINT_WIDTH;
         g2.drawLine(x0, y0, x1, y1);
      }

      Stroke oldStroke = g2.getStroke();
      g2.setColor(GRAPH_COLOR);
      g2.setStroke(GRAPH_STROKE);
      for (int i = 0; i < graphPoints.size() - 1; i++) {
         int x1 = graphPoints.get(i).x;
         int y1 = graphPoints.get(i).y;
         int x2 = graphPoints.get(i + 1).x;
         int y2 = graphPoints.get(i + 1).y;
         g2.drawLine(x1, y1, x2, y2);         
      }

      g2.setStroke(oldStroke);      
      g2.setColor(GRAPH_POINT_COLOR);
      for (int i = 0; i < graphPoints.size(); i++) {
         int x = graphPoints.get(i).x - GRAPH_POINT_WIDTH / 2;
         int y = graphPoints.get(i).y - GRAPH_POINT_WIDTH / 2;;
         int ovalW = GRAPH_POINT_WIDTH;
         int ovalH = GRAPH_POINT_WIDTH;
         g2.fillOval(x, y, ovalW, ovalH);
      }
   }
}