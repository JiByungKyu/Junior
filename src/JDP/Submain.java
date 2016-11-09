package JDP;

import java.awt.Color;
import java.io.IOException;
import java.util.Iterator;

import javax.swing.JFrame;
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
		
		for(int i=0;i<FP.noTime;i++){
			//System.out.println(FP.mapSpace[i].size());
			Iterator<String> iter = FP.mapSpace[i].keySet().iterator();
			
			while (iter.hasNext()) {
			
				String key = (String) iter.next();
				System.out.print("key=" + key);
				System.out.println(" value=" + FP.mapSpace[i].get(key));
			}
		}
		//weather.Space
		
		//PN_News.setBackground(Color.WHITE);
		PN_Weather.setBackground(Color.WHITE);
		
		
		PN_Weather.setLayout(null);
		
		
		setSize(400,550);//�궗�씠利� �젙�쓽
		setLocation(1070,90);//耳쒖죱�쓣�븣�쓽 �봽�젅�엫�쓽 �쐞移�
		//setResizable(false);//�겕湲곗“�젙 遺덇�
		
		this.add(PN_Weather);
		setVisible(true);
		
	}
}
