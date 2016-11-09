import java.io.IOException;
import java.util.Calendar;

public abstract class ApiExplorer {
	protected String Baseurl = "http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/";
	protected String serviceKey = "bmEl832vF6jbBp3i%2Fux7VvldeJN6gX0YbM67TfhYPwkB%2B5Y0yaYwZ9n6sdz0mHiIIy0UrdvlKF0fulRi0y%2FuhA%3D%3D";
	protected String Space = "ForecastSpaceData"; /* 동네예보URL */

	void currentTime() {
		Calendar cal = Calendar.getInstance();
	}

	public void send() throws IOException {
	}
}