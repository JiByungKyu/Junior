package weather;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
/*
 * author=Ji, 11/14
 */
import org.xml.sax.helpers.DefaultHandler;

public class NewsSAXParser extends DefaultHandler {
	private ArrayList<String> title = new ArrayList<String>();
	private ArrayList<String> link = new ArrayList<String>();
	private ArrayList<String> dscrpt = new ArrayList<String>();
	private String pubDate;
	String news = "http://fs.jtbc.joins.com//RSS/newsflash.xml";
	SAXParser parser;
	boolean bPubDate=false;
	boolean bTitle=false;
	boolean bLink=false;
	boolean bDscrpt=false;
	boolean pubTime=true;
	public NewsSAXParser() throws ParserConfigurationException, SAXException{
				parser= SAXParserFactory.newInstance().newSAXParser();

	}
				  // 문서의 시작
				  public void startDocument() throws SAXException {
				    super.startDocument();
				  }
				  // 엘리먼트 시작

				  public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
				    if(qName.equalsIgnoreCase("link")){//대소문자 구분없이 비교
				    	bLink=true;
				    }
				    else if(qName.equalsIgnoreCase("title")){//대소문자 구분없이 비교
				    	bTitle=true;
				    }
				    else if(qName.equalsIgnoreCase("description")){//대소문자 구분없이 비교
				    	bDscrpt=true;
				    }
				    else if(pubTime&&qName.equalsIgnoreCase("pubDate")){
				    	pubTime=false;
				    	bPubDate=true;
				    }
				    for (int i = 0; i < attributes.getLength(); i++) {

				    }

				  }
				  // 텍스트 데이터
				  public void characters(char ch[], int start, int length) throws SAXException {
						  if(bTitle){
							  getTitle().add(new String(ch,start,length));
							  bTitle=false;
							  }
						  else if(bLink){
							  getLink().add(new String(ch,start,length));
							  bLink=false;
							  }
						  
						  else if(bDscrpt){
							  getDscrpt().add(new String(ch,start,length));
							  bDscrpt=false;
						  }
						  if(bPubDate){
							  setPubDate(new String(ch,start,length));
							  bPubDate=false;
						  }
				  }
				  public void parse() throws SAXException, IOException{
					  parser.parse(news,this);							
				  }
				public ArrayList<String> getTitle() {
					return title;
				}
				public void setTitle(ArrayList<String> title) {
					this.title = title;
				}
				public ArrayList<String> getLink() {
					return link;
				}
				public void setLink(ArrayList<String> link) {
					this.link = link;
				}
				public ArrayList<String> getDscrpt() {
					return dscrpt;
				}
				public void setDscrpt(ArrayList<String> dscrpt) {
					this.dscrpt = dscrpt;
				}
				public String getPubDate() {
					return pubDate;
				}
				public void setPubDate(String pubDate) {
					this.pubDate = pubDate;
				}
	}
