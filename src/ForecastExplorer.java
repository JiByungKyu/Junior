import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ForecastExplorer extends ApiExplorer {
	public void send() throws IOException {
		StringBuilder forecastURL = new StringBuilder(Baseurl + Space);
		forecastURL.append("?ServiceKey=" + serviceKey);// URLEncoder.encode(serviceKey,"UTF-8"));
		forecastURL.append("&_type=json");
		forecastURL.append("&base_date=20161023" + "&base_time=2000");
		forecastURL.append("&nx=60" + "&ny=127");
		forecastURL.append("&numOfRows=999");/* 검색건수 */
		forecastURL.append("&pageNo=1"); /* 페이지 번호 */
		URL forecasturl = new URL(forecastURL.toString());
		HttpURLConnection conn = (HttpURLConnection) forecasturl.openConnection();
		conn.setRequestMethod("GET");
		// conn.setRequestProperty("Content-type", "application/json");
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
		// System.out.println(forecasturl);
		conn.disconnect();
		System.out.println(sb.toString());
		//
	}

}
