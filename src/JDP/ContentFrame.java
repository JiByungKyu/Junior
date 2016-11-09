package JDP;


import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class ContentFrame extends JFrame {
	//field
	JPanel content;
	JPanel pn_info;
	JPanel pn_task;
	
	private JTextArea b = new JTextArea("날씨자리 알아보기 위한 텍스트 창");
	private JTextArea c = new JTextArea("일정자리");
	
	//method
	public ContentFrame(){
		content = new JPanel();
		content.setBackground(Color.WHITE);
		setLayout(null);
		CalendarFrame calendar = new CalendarFrame(); 
		pn_info = new JPanel();
		pn_task = new JPanel();
		
		pn_info.add(b);
		pn_task.add(c);
		
		content.add(calendar.pn_calendar);
		content.add(pn_info);
		content.add(pn_task,BorderLayout.EAST);
	}
}
