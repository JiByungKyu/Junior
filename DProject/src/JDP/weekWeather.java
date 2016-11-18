package JDP;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;



public class weekWeather extends JFrame {
	List<Integer> scores = new ArrayList<Integer>();		//데이터 저장된 리스트
	int maxDataPoints;		//데이터 갯수
	DrawGraph PN_Graph;		//그래프 패널
	public weekWeather(){
		setScores();
		PN_Graph = new DrawGraph(scores);
		
		
		PN_Graph.setBounds(25, 25, 350, 280);
		this.setTitle("주간 날씨");
		this.setLayout(null);
		
		this.setSize(400,550);
		this.add(PN_Graph);
		this.setLocation(500,400);
		this.setVisible(true);
	}
	
	private void setScores(){
		maxDataPoints=16;
		for (int i = 0; i < maxDataPoints ; i++) {	
		       scores.add(i*3-30);
		}
		
	}
	
}
