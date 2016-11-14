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

	private int make_GribTime() {// 실황 기준 시간
		int gribtime = cTime;
		if (gribtime % 100 <= 40) {// API 기준 시간
			if (gribtime < 100) {// 00시의 경우
				gribtime += 2300;
			} else {
				gribtime -= 100;
			}
		}
		return (gribtime / 100) * 100;
	}

	private int make_GribDate() {
		int gribTime = cTime;
		int gribDate = cDate;
		if (gribTime <= 40) {
			return --gribDate;
		}
		return gribDate;
	}

	public int make_SpaceTime() {
		int spaceTime = cTime;
		int tTime = (spaceTime / 100) % 3;
		int minute = spaceTime % 100;
		if (spaceTime <= 230)
			return 2300;
		else {

			if (tTime == 2) {
				if (minute <= 30)
					spaceTime -= 300;
			} else if (tTime == 1) {
				spaceTime -= 200;
			} else {
				spaceTime -= 100;
			}
			return (spaceTime / 100) * 100;
		}
	}

	public int make_SpaceDate() {
		int spaceTime = cTime;
		int spaceDate = cDate;
		if (spaceTime <= 230)
			spaceDate--;
		return spaceDate;

	}

	public String Grib_Request() {
		int gribDate = make_GribDate();
		int gribTime = make_GribTime();

		StringBuilder forecastURL = new StringBuilder(FCBaseurl + Grib);
		forecastURL.append("?ServiceKey=" + FCserviceKey);// URLEncoder.encode(serviceKey,"UTF-8"));
		forecastURL.append("&_type=json");
		forecastURL.append("&base_date=" + gribDate + "&base_time=" + String.format("%04d", gribTime));
		forecastURL.append("&nx=60" + "&ny=127");// 충무로
		forecastURL.append("&numOfRows=999");/* 검색할 줄 수 */
		forecastURL.append("&pageNo=1"); /* 나타낼 페이지 */
		return forecastURL.toString();
	}

	public String Space_Request() {
		int spaceDate = make_SpaceDate();
		int spaceTime = make_SpaceTime();

		StringBuilder forecastURL = new StringBuilder(FCBaseurl + Space);
		forecastURL.append("?ServiceKey=" + FCserviceKey);// URLEncoder.encode(serviceKey,"UTF-8"));
		forecastURL.append("&_type=json");
		forecastURL.append("&base_date=" + spaceDate + "&base_time=" + String.format("%04d", spaceTime));
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
