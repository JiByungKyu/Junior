package Util;
import java.io.IOException;

public class ForecastExplorer extends ApiExplorer {
	/*
	 * 신규 동네 예보 조회 서비스의 데이터를 수신할 class 
	 */
	String Grib = "ForecastGrib";// 실황
	String Space = "ForecastSpaceData";// 예보
	String resultSpace;
	String resultGrib;
	ForecastExplorer() throws IOException {
		/*
		 * 데이터 수신
		 */
		resultSpace=send(Space_Request());
		resultGrib=send(Grib_Request());
	}
	/*
	 * 양식에 맞게 시간을 계산한다.
	 */
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
	/*
	 * 구한 시간을 기준으로 데이터 요청
	 */
	public String Grib_Request() {
		int gribDate = make_GribDate();
		int gribTime = make_GribTime();

		StringBuilder forecastURL = new StringBuilder(FCBaseUrl + Grib);
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

		StringBuilder forecastURL = new StringBuilder(FCBaseUrl + Space);
		forecastURL.append("?ServiceKey=" + FCserviceKey);// URLEncoder.encode(serviceKey,"UTF-8"));
		forecastURL.append("&_type=json");
		forecastURL.append("&base_date=" + spaceDate + "&base_time=" + String.format("%04d", spaceTime));
		forecastURL.append("&nx=60" + "&ny=127");// 충무로
		forecastURL.append("&numOfRows=999");/* 검색할 줄 수 */
		forecastURL.append("&pageNo=1"); /* 나타낼 페이지 */
		return forecastURL.toString();
	}
	public String getSpace(){
		return resultSpace;
	}
	public String getGrib(){
		return resultGrib;
	}
}
