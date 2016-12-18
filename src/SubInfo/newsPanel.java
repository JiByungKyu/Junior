package SubInfo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import Util.NewsSAXParser;
/** 홍대표
 * 뉴스 탭에 들어가는 패널이다.
 * 뉴스 파서를 사용해 해쉬맵으로 파싱된 정보를 받아와 사용한다.
 * 제목은 링크를 달아서 클릭하면 해당되는 기사의 링크가 열린다.
 */
public class newsPanel extends JPanel{	
	//뉴스 파서
	public NewsSAXParser news ;		
	
	private int dataLength ;	
	//들어오는 데이타 갯수 
	private JScrollPane SP_news ;
	private JPanel PN_inner ;
	
	public newsPanel(){
		createNewsParser();			
		//뉴스파서를 생성하고 dataLength를 설정해줌
		PN_inner = new JPanel();
		PN_inner.setBounds(10, 70, 300, 410);
		PN_inner.setLayout(new BorderLayout());
		SP_news = new JScrollPane();
		SP_news.getVerticalScrollBar().setUnitIncrement(16);	
		//스크롤바 생성하고 설정해줌
		SP_news.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
		//수직스크롤 항상 띄움
	
		PN_inner.add(SP_news,BorderLayout.CENTER);
		this.add(PN_inner);
		
		JPanel PN_borderlaout = new JPanel();
        SP_news.setViewportView(PN_borderlaout);
        PN_borderlaout.setLayout(new BorderLayout(0 , 0)); 
        //이 패널을 추가함으로써 깔끔하게 보여짐
        
        JPanel PN_column = new JPanel();		
        //날짜별로 한줄씩 패널을 추가하는 수직 패널선언
        PN_borderlaout.add(PN_column, BorderLayout.NORTH);
        PN_column.setLayout(new GridLayout(0, 1, 0, 1));
        PN_column.setBackground(Color.gray);
		
		
		this.setLayout(new GridLayout(0,1,1,5));
		
		for(int i=1;i<dataLength;i++){	
			//데이터 길이만큼 기사를 스크롤바 안에 추가해줌
			String STR_allTitle = new String(news.getTitle().get(i));
			JTextArea TA_Title =setlinkTA(news.getLink().get(i), STR_allTitle);	
			// 파서에서 뉴스제목 받아서 링크 달린 JTextArea로 바꿔줌
			JTextArea TA_dscrpt = new JTextArea(news.getDscrpt().get(i));
    		TA_dscrpt.setEditable(false);	
    		//사용자가 수정 불가능하게 함
            TA_Title.setEditable(false);
			TA_Title.setLineWrap(true);
			TA_Title.setWrapStyleWord(true);
			TA_Title.setFont(new Font("맑은 고딕", Font.BOLD,15));
			//
			if(STR_allTitle.length()<=25){		
				//제목 길이에 따라서 제목과 내용 미리보기 JTextArea의 크기 조절
				TA_Title.setBounds(10,8,260,20);
				TA_dscrpt.setBounds(10,35,260,85);
			}
			else{
				TA_Title.setBounds(10,8,260,40);
				TA_dscrpt.setBounds(10,55,260,65);
			}

			TA_dscrpt.setLineWrap(true);
			TA_dscrpt.setWrapStyleWord(true);
			TA_dscrpt.setBackground(new JPanel().getBackground());
			TA_Title.setBackground(new JPanel().getBackground());
			JPanel rowPanel = new JPanel();		
			// 기사 하나를 의미하는 rowPanel 설정
            rowPanel.setPreferredSize(new Dimension(280,120));
            rowPanel.setLayout(null);
            PN_column.add(rowPanel);
            rowPanel.setLayout(null);
            rowPanel.add(TA_Title);
            rowPanel.add(TA_dscrpt);

            if(i%2==0){	
            	//번갈아가면서 색깔 조정
    			TA_Title.setBackground(SystemColor.inactiveCaptionBorder);
                rowPanel.setBackground(SystemColor.inactiveCaptionBorder);
                TA_dscrpt.setBackground(SystemColor.inactiveCaptionBorder);
            }
			
           
		
		}
		
		
	}
	/*
	 * 뉴스 파서 객체를 생성하는 메소드이다.
	 */
	public void createNewsParser(){
		//뉴스파서를 생성하고 dataLength를 설정해줌
		try {
			news = new NewsSAXParser();
		} catch (ParserConfigurationException | SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			news.parse();
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		dataLength= news.getLink().size();
	}
	
	/*
	 * 링크와 제목을 받고 링크 달린 JTextArea를 반환하는 메소드
	 */
	public JTextArea setlinkTA(String link, String title){
		JTextArea TA_Link = new JTextArea(title);
		TA_Link.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		//마우스 올렸을때 바뀜
		 TA_Link.addMouseListener(new MouseAdapter() {
		   public void mouseClicked(MouseEvent e) {	
			   //마우스 클릭되면
		      if (e.getClickCount() > 0) {
		          if (Desktop.isDesktopSupported()) {
		                Desktop desktop = Desktop.getDesktop();
		                try {
		                    URI uri = new URI(link);
		                    desktop.browse(uri);		
		                    //링크 열어줌
		                } catch (IOException ex) {
		                    ex.printStackTrace();
		                } catch (URISyntaxException ex) {
		                    ex.printStackTrace();
		                }
		        }
		      }
		   }
		});
		return TA_Link;
	}
	
}
