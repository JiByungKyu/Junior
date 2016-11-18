package JDP;

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
import javax.swing.text.DefaultCaret;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class newsPanel extends JPanel{
	private NewsSAXParser news ;
	
	private int dataLength ;
	private JScrollPane SP_news ;
	private JPanel PN_inner ;
	public newsPanel(){
		createNewsParser();
		PN_inner = new JPanel();
		PN_inner.setBounds(10, 100, 300, 380);
		PN_inner.setLayout(new BorderLayout());
		SP_news = new JScrollPane();
		//SP_news.setBounds(10, 160, 300, 300);
		SP_news.getVerticalScrollBar().setUnitIncrement(16);
		SP_news.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		//SP_news.setPreferredSize(new Dimension(200,300));
		PN_inner.add(SP_news,BorderLayout.CENTER);
		this.add(PN_inner);
		
		JPanel PN_borderlaout = new JPanel();
        SP_news.setViewportView(PN_borderlaout);
        PN_borderlaout.setLayout(new BorderLayout(0 , 0));//깔끔하게댐
        
        JPanel PN_column = new JPanel();
        PN_borderlaout.add(PN_column, BorderLayout.NORTH);
        PN_column.setLayout(new GridLayout(0, 1, 0, 1));
        PN_column.setBackground(Color.gray);
		
		
		this.setLayout(new GridLayout(0,1,1,5));
		
		for(int i=1;i<dataLength;i++){
			String STR_allTitle = new String(news.title.get(i));
			JTextArea TA_Title =setlinkTA(news.link.get(i), STR_allTitle);
			JTextArea TA_dscrpt = new JTextArea(news.dscrpt.get(i));
			TA_Title.setBounds(10,10,260,40);
			TA_Title.setLineWrap(true);
			TA_Title.setWrapStyleWord(true);
			TA_Title.setFont(new Font("맑은 고딕", Font.BOLD,15));
			TA_Title.setBackground(Color.WHITE);
			TA_dscrpt.setBounds(10,50,260,65);
			TA_dscrpt.setLineWrap(true);
			TA_dscrpt.setWrapStyleWord(true);
			TA_dscrpt.setBackground(new JPanel().getBackground());
			
			JPanel rowPanel = new JPanel();
            rowPanel.setPreferredSize(new Dimension(280,120));
            rowPanel.setLayout(null);
            PN_column.add(rowPanel);
            rowPanel.setLayout(null);
            rowPanel.add(TA_Title);
            rowPanel.add(TA_dscrpt);
            
            
            if(i%2==0){
                rowPanel.setBackground(SystemColor.inactiveCaptionBorder);
                TA_dscrpt.setBackground(SystemColor.inactiveCaptionBorder);
            }
			
            
			//LB_title[i] = new JLabel(news.title.get(i));
			//LB_dscrpt[i] = new JLabel(news.dscrpt.get(i));
			//add(LB_dscrpt[i]);
			//rowPanel.add(LB_link[i]);
			
		
		
		
		}
		
		
	}
	public void createNewsParser(){
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
		dataLength= news.link.size();
	}
	
	public JTextArea setlinkTA(String link, String title){
		JTextArea TA_Link = new JTextArea(title);
		TA_Link.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		 TA_Link.addMouseListener(new MouseAdapter() {
		   public void mouseClicked(MouseEvent e) {
		      if (e.getClickCount() > 0) {
		          if (Desktop.isDesktopSupported()) {
		                Desktop desktop = Desktop.getDesktop();
		                try {
		                    URI uri = new URI(link);
		                    desktop.browse(uri);
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
