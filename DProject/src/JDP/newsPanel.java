package JDP;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class newsPanel extends JPanel{
	private NewsSAXParser news;
	private JLabel[] LB_title ;
	private JLabel[] LB_dscrpt ; 
	private JLabel[] LB_link ;
	private int dataLength;
	public newsPanel(){
		createNewsParser();
		//LB_title = new JLabel[dataLength];
		LB_dscrpt = new JLabel[dataLength];
		LB_link = new JLabel[dataLength];
		this.setLayout(new GridLayout(0,1,1,5));
		
		for(int i=0;i<dataLength;i++){
			//LB_title[i] = new JLabel(news.title.get(i));
			LB_dscrpt[i] = new JLabel(news.dscrpt.get(i));
			LB_link[i] = setlinkLabel(news.link.get(i), news.title.get(i));
		}
		
		for(int i=1;i<dataLength;i++){
			
			
			//add(LB_dscrpt[i]);
			this.add(LB_link[i]);
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
	public JLabel setlinkLabel(String link,String title){
		JLabel LB_Link = new JLabel(title);
		LB_Link.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		 LB_Link.addMouseListener(new MouseAdapter() {
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
		return LB_Link;
	}
	
}
