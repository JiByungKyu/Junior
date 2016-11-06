package weather;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ForecastExplorer extends ApiExplorer {
	String Grib = "ForecastGrib";// 실황
	String Space = "ForecastSpaceData";// 예보

	ForecastExplorer() throws IOException {
		super();
	}

	private String Make_GBTime() {// 실황 기준 시간
		int Btime = currentTime();
		if (Btime % 100 <= 40) {// API 기준 시간
			if (Btime < 100) {// 00시의 경우
				Btime += 2300;
			} else {
				Btime -= 100;
			}
		}
		return String.format("%04d", (Btime / 100) * 100);
	}

	private String Make_SBTime() {
		int Btime = currentTime() / 100;
		if (Btime % 3 == 2)
			;
		else if (Btime % 3 == 1) {
			Btime -= 2;
		} else {
			Btime -= 1;
		}

		return String.format("%04d", Btime * 100);
	}

	public String Grib_Request() {
		StringBuilder forecastURL = new StringBuilder(FCBaseurl + Grib);
		forecastURL.append("?ServiceKey=" + FCserviceKey);// URLEncoder.encode(serviceKey,"UTF-8"));
		forecastURL.append("&_type=json");
		forecastURL.append("&base_date=" + currentDate() + "&base_time=" + Make_GBTime());
		forecastURL.append("&nx=60" + "&ny=127");// 충무로
		forecastURL.append("&numOfRows=999");/* 검색할 줄 수 */
		forecastURL.append("&pageNo=1"); /* 나타낼 페이지 */
		return forecastURL.toString();
	}

	public String Space_Request() {
		StringBuilder forecastURL = new StringBuilder(FCBaseurl + Space);
		forecastURL.append("?ServiceKey=" + FCserviceKey);// URLEncoder.encode(serviceKey,"UTF-8"));
		forecastURL.append("&_type=json");
		forecastURL.append("&base_date=" + currentDate() + "&base_time=" + Make_SBTime());
		forecastURL.append("&nx=60" + "&ny=127");// 충무로
		forecastURL.append("&numOfRows=999");/* 검색할 줄 수 */
		forecastURL.append("&pageNo=1"); /* 나타낼 페이지 */
		return forecastURL.toString();
	}

	public String send(boolean isSpace) throws IOException {
		URL forecasturl;
		if (isSpace)
			forecasturl = new URL(Space_Request());
		else
			forecasturl = new URL(Grib_Request());

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
		return sb.toString();
	}
}
