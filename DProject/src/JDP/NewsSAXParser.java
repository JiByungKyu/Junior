package JDP;
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
	public ArrayList<String> title = new ArrayList<String>();
	public ArrayList<String> link = new ArrayList<String>();
	public ArrayList<String> dscrpt = new ArrayList<String>();
	public String pubDate;
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
				    System.out.println("Start Document");
				  }
				  // 엘리먼트 시작

				  public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
				    System.out.println("Start Element: " + qName);
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
				      System.out.println("Attribute: " + attributes.getQName(i) + "=" + attributes.getValue(i));

				    }

				  }
				  // 엘리먼트 끝
				  public void endElement(String uri, String localName, String qName) throws SAXException {
				    System.out.println("End Element: " + qName);
				  }
				  // 텍스트 데이터
				  public void characters(char ch[], int start, int length) throws SAXException {
						  if(bTitle){
							  title.add(new String(ch,start,length));
							  bTitle=false;
							  }
						  else if(bLink){
							  link.add(new String(ch,start,length));
							  bLink=false;
							  }
						  
						  else if(bDscrpt){
							  dscrpt.add(new String(ch,start,length));
							  bDscrpt=false;
						  }
						  if(bPubDate){
							  pubDate=new String(ch,start,length);
							  bPubDate=false;
						  }
				    System.out.println("Character: " + new String(ch, start, length));
				  }
				  public void endDocument() throws SAXException {
				    super.endDocument();
				    System.out.println("End Document");
				  }
				  public void parse() throws SAXException, IOException{
					  parser.parse(news,this);
					  System.out.println(link.size());
					  System.out.println(title.size());
					  System.out.println(dscrpt.size());
					 for(int i=0;i<link.size();i++){
						 System.out.println("link "+link.get(i));
						 System.out.println("title "+title.get(i));
						 System.out.println("decription "+dscrpt.get(i));
						 
					 }
					 System.out.println(pubDate);
							
				  }
	}
