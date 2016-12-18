package SubInfo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
/** 홍대표
 * 메인 프레임 우측에 붙어있는 프레임이다.
 * 이틀간의 날씨, 주간날씨, 뉴스 정보를 보여주는 역할을 한다.
 */
public class Submain extends JFrame {	//MainFrame 우측 프레임
	
	private JTabbedPane jtp = new JTabbedPane();	//템 사용 (날씨, 뉴스)
	private twodayPanel PN_Weather;					//동내 예보 UI 패널
	private newsPanel PN_News = new newsPanel();	//뉴스 기사 패널
	
	public Submain(int width, int height) {	//생성자 , 프레임의 location 위치를 받아옴)
		super("Weather & News");
		PN_Weather = new twodayPanel();
		PN_Weather.setBackground(Color.WHITE);

		PN_News.setBackground(Color.WHITE);
		
		jtp.addTab("날씨", PN_Weather);
		jtp.addTab("뉴스", PN_News);
		PN_News.setLayout(null);
		JLabel Media = new JLabel(PN_News.news.getTitle().get(0));//JTBC 레이블
		Media.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		int pubDateLength = PN_News.news.getPubDate().length();
		JLabel getDate = new JLabel(PN_News.news.getPubDate().substring(0, pubDateLength-3)+" ");// 정보 받아온시간 레이블
		Media.setBounds(12, 10, 200, 30);
		getDate.setBounds(12, 38, 248, 25);
		PN_News.add(Media);
		PN_News.add(getDate);
		
		
		this.setSize(330,550);
		this.setLocation(width, height);
		setResizable(false);      
		getContentPane().add(jtp,BorderLayout.CENTER);
		setVisible(true);
		
	}
	public twodayPanel getWeatherPanel(){
		return PN_Weather;
	}
}
