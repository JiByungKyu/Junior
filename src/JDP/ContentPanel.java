package JDP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ContentPanel extends JPanel {
	//field
	private JPanel pn_RightPanel;
	private JTextArea empty;
	protected static CalendarPanel pn_calendar;
	protected static WeatherPanel pn_weather;
	protected static TaskPanel pn_task;
	
	//method
	public ContentPanel(){
		pn_RightPanel = new JPanel();
		pn_calendar = new CalendarPanel();
		pn_weather = new WeatherPanel();
		pn_task = new TaskPanel();
		empty = new JTextArea();
		
		empty.setEditable(false);
		empty.setText("\n");
		setBackground(Color.WHITE);
		setLayout(new FlowLayout());
		
		pn_RightPanel.setLayout(new BorderLayout());
		pn_RightPanel.add(BorderLayout.NORTH, pn_weather);
		pn_RightPanel.add(BorderLayout.CENTER, empty);
		pn_RightPanel.add(BorderLayout.SOUTH, pn_task);
		
		add(pn_calendar,"West");
		add(pn_RightPanel,"East");
	}
}
