package TaskManager;

import java.util.TimerTask;

public class TaskAlarm extends TimerTask{
	/*
	 * 알람 실행 class
	 */
	private int indexOf;
	TaskAlarm(int index){
		indexOf=index;
	}
	public void run() {

		System.out.println("TimeTask!");
		new AlarmFrame(indexOf);
		
	}
}
