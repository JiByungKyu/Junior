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
		Weather weather = new Weather();											//사용법을 모르겟다
		
		for(int i=0;i<weather.fpSpace.noTime;i++){
			Iterator<String> iter = weather.Space[i].keySet().iterator();
			
			while (iter.hasNext()) {
			
				String key = (String) iter.next();
				System.out.print("key=" + key);
				System.out.println(" value=" + weather.Space[i].get(key));
			}
		}
		//weather.Space
		
		//PN_News.setBackground(Color.WHITE);
		PN_Weather.setBackground(Color.WHITE);
		
		
		PN_Weather.setLayout(null);
		
		
		setSize(400,550);//사이즈 정의
		setLocation(1070,90);//켜졌을때의 프레임의 위치
		//setResizable(false);//크기조정 불가
		
		this.add(PN_Weather);
		setVisible(true);
		
	}
}
