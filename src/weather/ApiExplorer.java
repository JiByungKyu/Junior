package weather;
import java.util.Calendar;

public abstract class ApiExplorer {
	protected String FCBaseurl = "http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/";
	protected String FCserviceKey = "bmEl832vF6jbBp3i%2Fux7VvldeJN6gX0YbM67TfhYPwkB%2B5Y0yaYwZ9n6sdz0mHiIIy0UrdvlKF0fulRi0y%2FuhA%3D%3D";
	protected Calendar cal = Calendar.getInstance();
	protected int cDate, cTime;

	ApiExplorer() {
		cDate = currentDate();
		cTime = currentTime();
	}

	protected int currentDate() {
		int Date;
		Date = cal.get(Calendar.YEAR) * 10000;
		Date += (cal.get(Calendar.MONTH) + 1) * 100;
		Date += cal.get(Calendar.DATE);
		return Date;
	}

	protected int currentTime() {
		int Time = cal.get(Calendar.HOUR_OF_DAY) * 100;
		Time += cal.get(Calendar.MINUTE);
		return Time;
	}
}