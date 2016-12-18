package JDP;

<<<<<<< HEAD
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JFrame;

import org.json.simple.parser.ParseException;

import weather.FineDustParser;
import weather.ForecastParser;

public class MainFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static HashMap<String,String> mapGrib;
	private static HashMap<String, String> mapFnDst;
	private MenuBar menu;
	private ContentPanel content;
	
	public MainFrame(){
		super("Task Manager");
		
		try {
			ForecastParser forecastParser;
			FineDustParser fineDustParser;
			try {
				forecastParser = new ForecastParser();
				fineDustParser = new FineDustParser(); 
				mapGrib = forecastParser.getGribHashMap();
				mapFnDst = fineDustParser.getFnDstHashMap(); 
				//Submain smain = new Submain(forecastParser);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		menu = new MenuBar();
		content = new ContentPanel(); 
		
		setSize(850,550);	//사이즈 정의
		setLocation(270,90);//켜졌을때의 프레임의 위치
		setResizable(false);//크기조정 불가
		
		setJMenuBar(menu);	//위쪽에 메뉴 추가
		add(content);		//중앙에 content 추가
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		//export tasks on close
		addWindowListener(new WindowAdapter(){
			/**
			 * Method used to export tasks when the program is closed
			 * 
			 * @ param exported boolean used to show whether the tasks have been saved of not
			 */
			public void windowClosing(WindowEvent we)
			    {
					content.pn_task.toDoList.exportTasks();
			    }
		});
	}
	
	static public HashMap<String,String> getWeather(){
		return mapGrib;
	}
	
	static public HashMap<String, String> getFineDust(){
		return mapFnDst;
=======
import javax.swing.JFrame;

public class MainFrame extends JFrame {
	public MainFrame(){
		super("Task Manager");
		
		MenuFrame menu = new MenuFrame();
		ContentFrame content = new ContentFrame(); 
		
		setSize(800,550);//사이즈 정의
		setLocation(270,90);//켜졌을때의 프레임의 위치
		setResizable(false);//크기조정 불가
		
		add("North",menu.menubar);//위쪽에 메뉴 추가
		add(content.content);//중앙에 content 추가
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
>>>>>>> a8ecebe03b04f20fe1760fbb616dc4e67105ab96
	}
}
