package JDP;

import java.util.Calendar;

public abstract class ApiExplorer {
	protected String FCBaseurl = "http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/";
	protected String FCserviceKey = "bmEl832vF6jbBp3i%2Fux7VvldeJN6gX0YbM67TfhYPwkB%2B5Y0yaYwZ9n6sdz0mHiIIy0UrdvlKF0fulRi0y%2FuhA%3D%3D";
	protected Calendar cal = Calendar.getInstance();

	protected String currentDate() {

		StringBuilder Ctime = new StringBuilder();
		Ctime.append(cal.get(Calendar.YEAR));
		Ctime.append((cal.get(Calendar.MONTH) + 1));
		Ctime.append(cal.get(Calendar.DATE));
		return Ctime.toString();
	}

	protected int currentTime() {
		int Time = cal.get(Calendar.HOUR_OF_DAY) * 100;
		Time += cal.get(Calendar.MINUTE);
		return Time;
	}
}