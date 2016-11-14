package JDP;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JFrame;

import weather.ForecastParser;

public class MainFrame extends JFrame {
	public static HashMap<String,String> mapGrib;
	
	public MainFrame(){
		super("Task Manager");
		
		try {
			ForecastParser forecastParser = new ForecastParser();
			mapGrib = forecastParser.getHashMap();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		MenuBar menu = new MenuBar();
		ContentPanel content = new ContentPanel(); 
		
		setSize(830,530);//사이즈 정의
		setLocation(270,90);//켜졌을때의 프레임의 위치
		//setResizable(false);//크기조정 불가
		
		setJMenuBar(menu);//위쪽에 메뉴 추가
		add(content);//중앙에 content 추가
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
	
	static public HashMap<String,String> getMapGrib(){
		return mapGrib;
	}
}
