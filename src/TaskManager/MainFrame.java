package TaskManager;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Timer;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.xml.parsers.ParserConfigurationException;

import org.json.simple.parser.ParseException;

import SubInfo.Submain;
import Util.FineDustParser;
import Util.ForecastParser;

public class MainFrame extends JFrame implements ActionListener{
	//field
	private static HashMap<String,String> mapGrib;
	private static HashMap<String, String> mapFnDst;
	private static ForecastParser forecastParser;
	private static FineDustParser fineDustParser;
	static boolean subison = false;
	private BufferedImage BI=null;
	private MenuBar menu;
	private ContentPanel content;
	static Submain smain;
	private PopupMenu popup;
	private MenuItem MIRestore;
	private MenuItem MIExit;
	private TrayIcon trayIcon;
	private SystemTray tray;
	private Vector<Timer> timerVector;
	private boolean isOpenwWeather = false;
	protected static int x, y;
	//method
	public MainFrame(){
		super("Task Manager");
		Image image = Toolkit.getDefaultToolkit().getImage("sunny.gif");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(850,550);	//사이즈 정의
		Dimension dimen = Toolkit.getDefaultToolkit().getScreenSize();
		x = (int)dimen.getWidth()/2 - this.getWidth()/2 - 100 ;
		y = (int)dimen.getHeight()/2 - this.getHeight()/2;
		setLocation(x, y);//켜졌을때의 프레임의 위치
		setResizable(false);//크기조정 불가
		try {
			BI=ImageIO.read(new File("icon.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		timerVector=new Vector<Timer>();
		popup=new PopupMenu();
		MIExit=new MenuItem("Exit");
		MIExit.addActionListener(this);
		MIRestore = new MenuItem("Restore");
		MIRestore.addActionListener(this);
		popup.add(MIRestore);
		popup.add(MIExit);
		if(BI!=null) trayIcon = new TrayIcon(BI,"Icon",popup);
		if(SystemTray.isSupported()&&trayIcon!=null){
			tray=SystemTray.getSystemTray();
				try {
					tray.add(trayIcon);
				} catch (AWTException e) {
					e.printStackTrace();
				}
		}
		try {
			forecastParser = new ForecastParser();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		fineDustParser = new FineDustParser(); 
		mapGrib = forecastParser.getGribHashMap();
		mapFnDst = fineDustParser.getFnDstHashMap();
		subison = true;
		
		menu = new MenuBar();
		content = new ContentPanel();
		super.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setJMenuBar(menu);	//위쪽에 메뉴 추가
		add(content);		//중앙에 content 추가
		
		smain = new Submain((int)dimen.getWidth()/2 - this.getWidth()/2 + 745, (int)dimen.getHeight()/2 - this.getHeight()/2);
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
				we.getWindow().setVisible(false);
				smain.setVisible(false);
				if(smain.getWeatherPanel().isVisible())
					isOpenwWeather=true;
				else
					isOpenwWeather=false;
					smain.getWeatherPanel().setVisible(false);
			   }
		});
		alarm();
	}
	
	static public HashMap<String,String> getWeather(){
		return mapGrib;
	}
	
	static public HashMap<String, String> getFineDust(){
		return mapFnDst;
	}
	
	static public ForecastParser getParser(){
		return forecastParser;
	}
	
	public void alarm(){
		Task temp;
		Calendar cal=Calendar.getInstance();
		Iterator<Task> iter= 	ToDoList.myTasks.iterator();
		while(iter.hasNext()){
			temp= iter.next();
			Calendar tempCal=Calendar.getInstance();
			if(temp.getStartDate().getYear()==cal.get(Calendar.YEAR)&&temp.getStartDate().getMonth()==(cal.get(Calendar.MONTH)+1)&&temp.getStartDate().getDay()==cal.get(Calendar.DATE)){
				//Date date= new Date(temp.getStartDate().getYear(),temp.getStartDate().getMonth(),temp.getStartDate().getDay(),temp.getStartDate().getHour(),temp.getStartDate().getMin());
				if(temp.getStartDate().getHour()>=cal.get(Calendar.HOUR)&&temp.getStartDate().getMin()>cal.get(Calendar.MINUTE)){
					tempCal.set(temp.getStartDate().getYear(), temp.getStartDate().getMonth()-1, temp.getStartDate().getDay(),temp.getStartDate().getHour(), temp.getStartDate().getMin(),0);
					System.out.println(ToDoList.myTasks.indexOf(temp));
					System.out.println(temp.getStartDate().getYear());
					Timer timer = new Timer();
					System.out.println(tempCal.getTime().toString());
					timer.schedule(new TaskAlarm(ToDoList.myTasks.indexOf(temp)), tempCal.getTime());
					timerVector.addElement(timer);
				}
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==MIRestore){
			this.setVisible(true);
			smain.setVisible(true);
			if(isOpenwWeather)
				smain.getWeatherPanel().setVisible(true);
			smain.getWeatherPanel().setVisible(true);
		}
		else if(e.getSource()==MIExit){
			content.pn_task.toDoList.exportTasks();
			System.exit(0);
		}
		
	}
}
