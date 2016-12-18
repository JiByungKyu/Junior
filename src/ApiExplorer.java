import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;

public class ApiExplorer {
	protected String FCBaseUrl = "http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/";
	protected String FCserviceKey = "bmEl832vF6jbBp3i%2Fux7VvldeJN6gX0YbM67TfhYPwkB%2B5Y0yaYwZ9n6sdz0mHiIIy0UrdvlKF0fulRi0y%2FuhA%3D%3D";
	protected String FCMidKey="bmEl832vF6jbBp3i%2Fux7VvldeJN6gX0YbM67TfhYPwkB%2B5Y0yaYwZ9n6sdz0mHiIIy0UrdvlKF0fulRi0y%2FuhA%3D%3D";
	protected String AirDustKey="bmEl832vF6jbBp3i%2Fux7VvldeJN6gX0YbM67TfhYPwkB%2B5Y0yaYwZ9n6sdz0mHiIIy0UrdvlKF0fulRi0y%2FuhA%3D%3D";
	protected String FCMidUrl = "http://newsky2.kma.go.kr/service/MiddleFrcstInfoService/";
	protected String airUrl="http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/";
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
	public String send(String url) throws IOException {
		URL forecasturl= new URL(url);
		HttpURLConnection conn = (HttpURLConnection) forecasturl.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();
		System.out.println(forecasturl.toString());
		System.out.println(sb.toString());
		return sb.toString();
	}
}