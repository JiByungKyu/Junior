package Util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;

public class ApiExplorer {
	// 각종 API 관련 서비스키, url 등등 기본적인 API에서 제공하는 정보를 편의에 맞게 수정
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
		// 현재 날짜를 정수형으로 반환
		int Date;
		Date = cal.get(Calendar.YEAR) * 10000;
		Date += (cal.get(Calendar.MONTH) + 1) * 100;
		Date += cal.get(Calendar.DATE);
		return Date;
	}

	protected int currentTime() {
		// 현재 시간을 정수형으로 반환
		int Time = cal.get(Calendar.HOUR_OF_DAY) * 100;
		Time += cal.get(Calendar.MINUTE);
		return Time;
	}
	
	public String send(String url) throws IOException {
		/*
		 * 매개변수로 url을 받아 http 방식으로 요청, 응답받은 문자열을 반환한다. 
		 */
		URL forecasturl= new URL(url);
		HttpURLConnection conn = (HttpURLConnection) forecasturl.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
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
		return sb.toString();
	}
}