package JDP;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.xml.parsers.ParserConfigurationException;

import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

/**
 * Class used to run the ToDoList application
 * 
 * @author Charles Henry
 *
 */
public class MainApp {

	public static void main(String[] args) throws IOException, ParseException, ParserConfigurationException, SAXException, URISyntaxException {
		// ToDoList ToDo1 = new ToDoList();
		// ToDo1.setVisible(true); // Makes the frame visible to the user
		// (default
		// = false)
		//NewsSAXParser news = new NewsSAXParser();
		//news.parse();
		//HashMap<String,String> G[] = new HashMap[30];
		//for(int a=0;a<30;a++)
		//		G[a]=new HashMap<String,String>();
		//ForecastParser b=new ForecastParser();
		new ForecastMidParser();
	}

}