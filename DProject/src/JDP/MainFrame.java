package JDP;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JFrame;

import org.json.simple.parser.ParseException;

public class MainFrame extends JFrame {
	public static HashMap<String,String> mapGrib;
	public static ForecastParser forecastParser1;
	public MainFrame(){
		super("Task Manager");
		
		try {
			forecastParser1 = new ForecastParser();
			mapGrib = forecastParser1.getGribHashMap();
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		MenuBar menu = new MenuBar();
		ContentPanel content = new ContentPanel(); 
		
		setSize(830,550);//�궗�씠利� �젙�쓽
		setLocation(270,90);//耳쒖죱�쓣�븣�쓽 �봽�젅�엫�쓽 �쐞移�
		//setResizable(false);//�겕湲곗“�젙 遺덇�
		
		setJMenuBar(menu);//�쐞履쎌뿉 硫붾돱 異붽�
		add(content);//以묒븰�뿉 content 異붽�
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
	static ForecastParser getFP(){
		return forecastParser1;
	}
}
