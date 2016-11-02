package JDP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JPanel;

public class ContentPanel extends JPanel {
	//field
	JPanel pn_RightSide;
	CalendarPanel pn_calendar;
	WeatherPanel pn_weather;
	TaskPanel pn_task;
	
	//method
	public ContentPanel(){
		pn_RightSide = new JPanel();
		pn_calendar = new CalendarPanel();
		pn_weather = new WeatherPanel();
		pn_task = new TaskPanel();
		
		setBackground(Color.WHITE);
		setLayout(new FlowLayout());
		
		pn_RightSide.setLayout(new BorderLayout());
		pn_RightSide.add(BorderLayout.NORTH, pn_weather);
		pn_RightSide.add(BorderLayout.SOUTH, pn_task);
		
		add(pn_calendar,"West");
		add(pn_RightSide,"East");
	}
}
