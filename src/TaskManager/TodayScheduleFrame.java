package TaskManager;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class TodayScheduleFrame extends JFrame {
	//field
	private ArrayList<String> todayFor = new ArrayList<String>();
	protected JLabel date;
	protected JList<String> list;
	protected DefaultListModel<String> model;
	protected JTextArea info;
	private JTextArea wisdom;
	//method
	public TodayScheduleFrame(){
		setVisible(true);
		setSize(550,400);
		setResizable(false);
		getContentPane().setBackground(Color.WHITE);
		setLocation(MainFrame.x * 2, MainFrame.y *2);
		getContentPane().setLayout(null);
		setWisdom();
		//날짜 출력
		date = new JLabel(CalendarPanel.getToday().get(Calendar.MONTH)+1 + "월" + CalendarPanel.getToday().get(Calendar.DATE) + "일");
		date.setHorizontalAlignment(SwingConstants.CENTER);
		date.setBounds(39, 10, 470, 39);
		date.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		getContentPane().add(date);
		//오늘의 일정 출력
		model = new DefaultListModel<String>();
		list = new JList<String>(model);
		JScrollPane scroller = new JScrollPane(list);//스크롤 생성
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		list.setVisibleRowCount(5);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		scroller.setBounds(39, 125, 470, 150);
		scroller.setBackground(Color.WHITE);
		scroller.setBorder(new TitledBorder("스케줄"));
		getContentPane().add(scroller);
		//정보 출력
		info = new JTextArea();
		info.setBackground(Color.WHITE);
		info.setBounds(39, 285, 470, 73);
		info.setBorder(new TitledBorder("정보"));
		info.setEditable(false);
		getContentPane().add(info);		
		//명언 출력
		wisdom = new JTextArea();
		String today = getwisdom();
		wisdom.setText(today);

		if(isalpha(today)){
			if(today.length()>85){
				wisdom.setBounds(39, 61, 470, 55);
			}else{
				wisdom.setBounds(39, 61, 470, 42);
			}
		}else{
			if(today.length()>45){
				wisdom.setBounds(39, 61, 470, 55);
			}else{
				wisdom.setBounds(39, 61, 470, 42);
			}
		}
		
		wisdom.setEditable(false);
		wisdom.setLineWrap(true);
		wisdom.setWrapStyleWord(true);
		wisdom.setBorder(new TitledBorder(new LineBorder(new Color(51,255,102),5), "오늘의 명언"));
		getContentPane().add(wisdom);
		wisdom.setColumns(10);
		
		showTask();
		showInfo();
	}
	//영어인지 확인하는 메소드 
	public boolean isalpha(String today){
		char ch = today.charAt(0);
		if((ch>='a' && ch<='z')||(ch>='A'&&ch<='Z'))
			return true;
		else
		return false;
	}
	//명언을 랜덤하게 가져오는 메소드
	public String getwisdom(){
		Random random = new Random();
		int index = random.nextInt(todayFor.size());
		return todayFor.get(index).toString();
	}
	//명언을 출력하는 메소드
	public void setWisdom(){
        BufferedReader br = null;        //버퍼 생성
        InputStreamReader isr = null;     // Input 스트림 생성
        FileInputStream fis = null;        // File Input 스트림 생성
        File file = new File("wisdom.txt"); // File 경로
        String temp = "";// 버퍼로 읽어들일 임시 변수
 
        try {
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis, "UTF-8"); 
            br = new BufferedReader(isr);
            while( (temp = br.readLine()) != null) {
            	todayFor.add(temp);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                isr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
	//정보를 출력하는 메소드
	public void showInfo(){
		StringBuilder todayInfo = new StringBuilder();
		todayInfo.append("오늘의 날씨는 ");
		switch(WeatherPanel.sky){
		case 1:	todayInfo.append("맑겠습니다.");			break;
		case 2:	todayInfo.append("구름이 조금 있겠습니다.\n");	break;
		case 3:	todayInfo.append("구름이 많이 있겠습니다.\n");	break;
		case 4:	todayInfo.append("비가 예보되어 있습니다.\n");	break;
		case 5:	todayInfo.append("진눈깨비가 날리겠습니다.\n");break;
		case 6:	todayInfo.append("눈이 예보되어 있습니다.\n");	break;
		case 7:	todayInfo.append("흐리겠습니다.\n");		break;
		}
		switch(WeatherPanel.fineDust){		
		case 2:	todayInfo.append("미세먼지가 나쁨수준이니 마스크를 준비하세요.\n");					break;
		case 3:	todayInfo.append("미세먼지가 매우 나쁨수준이니 실외활동 자제하고 마스크를 착용하세요.\n");	break;
		}
		info.setText(todayInfo.toString());
	}
	//일정을 출력하는 메소드
	public void showTask(){
		if(!model.isEmpty()){
			model.removeAllElements();
		}
		for(int i = 0; i < ToDoList.myTasks.size(); i++){
			if(ToDoList.myTasks.get(i).getStartDate().getDay() == CalendarPanel.getToday().get(Calendar.DATE) && 
					ToDoList.myTasks.get(i).getStartDate().getMonth() == CalendarPanel.getToday().get(Calendar.MONTH)+1 &&
					ToDoList.myTasks.get(i).getStartDate().getYear() == CalendarPanel.getToday().get(Calendar.YEAR))
			{
				model.addElement(ToDoList.myTasks.get(i).getName());
			}
		}
	}
}
