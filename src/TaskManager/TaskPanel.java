package TaskManager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

public class TaskPanel extends JPanel implements ActionListener {
	//field
	protected JPanel pn_taskList;
	protected JPanel pn_button;
	protected JPanel pn_showbtn;
	protected static JList<String> taskList;
	protected static DefaultListModel<String> listModel;
	protected JButton btn_add;
	protected JButton btn_delete;
	protected JButton btn_modify;
	protected JButton btn_showAll;
	protected JButton btn_showToday;
	static ToDoList toDoList;
	static TodayScheduleFrame todaySchedule;
	ContentPanel pn_content;
	//method
	TaskPanel(ContentPanel content){
		pn_content = content;
		pn_taskList = new JPanel();
		pn_button = new JPanel();
		pn_showbtn = new JPanel();
		listModel = new DefaultListModel<String>();
		taskList = new JList<String>(listModel);
		btn_add = new JButton("일정 추가");
		btn_modify = new JButton("수정");
		btn_delete = new JButton("삭제");
		btn_showAll = new JButton("일정 모두 보기");
		btn_showToday = new JButton("오늘의 일정 보기");
		toDoList = new ToDoList();
		
		setLayout(new BorderLayout());
		pn_button.setBackground(Color.WHITE);
		pn_showbtn.setBackground(Color.WHITE);
		
		taskList.setFixedCellWidth(250);
		JScrollPane scroller = new JScrollPane(taskList);//스크롤 생성
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		taskList.setVisibleRowCount(10);//10줄까지만 보이도록 설정
		taskList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		pn_taskList.setBackground(Color.WHITE);
		pn_taskList.add(scroller);
		//actionlistener 추가
		btn_add.addActionListener(this);
		btn_modify.addActionListener(this);
		btn_delete.addActionListener(this);
		btn_showAll.addActionListener(this);
		btn_showToday.addActionListener(this);
		//panel에 추가
		pn_button.add(btn_add);
		pn_button.add(btn_modify);
		pn_button.add(btn_delete);
		pn_showbtn.add(btn_showAll);
		pn_showbtn.add(btn_showToday);
		
		add(pn_taskList,"North");
		add(new JPanel());
		add(pn_button,"Center");
		add(pn_showbtn,"South");
		showTask(CalendarPanel.getCalendar().get(Calendar.DATE),
				CalendarPanel.getCalendar().get(Calendar.MONTH) + 1,
				CalendarPanel.getCalendar().get(Calendar.YEAR));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String event = e.getActionCommand();
		if(event.equals("일정 추가")){
			toDoList.add1.setVisible(true);
			pn_content.getCalendarPanel().calendarView();
		}
		else if(event.equals("수정")){
			toDoList.edit(taskList.getSelectedIndex());
			pn_content.getCalendarPanel().calendarView();
		}
		else if(event.equals("삭제")){
			toDoList.delete(taskList.getSelectedIndex());
			pn_content.getCalendarPanel().calendarView();
		}
		else if(event.equals("일정 모두 보기")){
			toDoList.setVisible(true);
		}
		else if(event.equals("오늘의 일정 보기")){
			if(todaySchedule == null){
				todaySchedule = new TodayScheduleFrame();
			}
			else if(!todaySchedule.isVisible()){
				todaySchedule.setVisible(true);
			}
			else{
				todaySchedule.setVisible(false);
			}
		}
		showTask(CalendarPanel.getCalendar().get(Calendar.DATE),
				CalendarPanel.getCalendar().get(Calendar.MONTH) + 1,
				CalendarPanel.getCalendar().get(Calendar.YEAR));
	}
	//일정 출력하는 메소드
	public static void showTask(int day, int month, int year){
		if(!listModel.isEmpty()){
			listModel.removeAllElements();
		}
		for(int i = 0; i < ToDoList.myTasks.size(); i++){
			if(ToDoList.myTasks.get(i).getStartDate().getDay() == day && 
					ToDoList.myTasks.get(i).getStartDate().getMonth() == month &&
					ToDoList.myTasks.get(i).getStartDate().getYear() == year)
			{
				listModel.addElement(toDoList.getTask().get(i).getName());
			}
		}
	}
	
}
