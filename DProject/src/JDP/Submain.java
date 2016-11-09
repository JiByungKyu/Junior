package JDP;

import java.awt.Color;
import java.io.IOException;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.json.simple.parser.ParseException;

public class Submain extends JFrame {
	
	//private JTabbedPane jtp = new JTabbedPane();
	private JPanel PN_Weather = new JPanel();
	//private JPanel PN_News = new JPanel();
	//private JPanel PN_Subway = new JPanel();
	public Submain() throws IOException, ParseException{
		super("Weather Detail");
		ForecastParser FP= new ForecastParser();											//�궗�슜踰뺤쓣 紐⑤Ⅴ寃잙떎
		this.setLayout(null);
		for(int i=0;i<FP.noTime;i++){
			//System.out.println(FP.mapSpace[i].size());
			Iterator<String> iter = FP.mapSpace[i].keySet().iterator();
			
			while (iter.hasNext()) {
			
				String key = (String) iter.next();
				System.out.print("key=" + key);
				System.out.println(" value=" + FP.mapSpace[i].get(key));
				if(key.equals("T3H")){
					JLabel LB_label = new JLabel(FP.mapSpace[i].get(key)+"°C");
					LB_label.setBounds(250, i*20+40, 150, 50);
					PN_Weather.add(LB_label);
				}
				if()
				
			}
		}
		JLabel LB_label = new JLabel("온도");
		LB_label.setBounds(245, 10, 150, 50);
		PN_Weather.add(LB_label);
		//weather.Space
		
		//PN_News.setBackground(Color.WHITE);
		PN_Weather.setBackground(Color.WHITE);
		PN_Weather.setLayout(null);
		PN_Weather.setBounds(10, 10, 290, 450);
		
		this.setSize(330,550);//�궗�씠利� �젙�쓽
		this.setLocation(1070,90);//耳쒖죱�쓣�븣�쓽 �봽�젅�엫�쓽 �쐞移�
		//setResizable(false);//�겕湲곗“�젙 遺덇�
		
		this.add(PN_Weather);
		setVisible(true);
		
	}
}
