package weather;
import java.io.IOException;

/*
 * author : ji
 * 2016.11.17
 */
public class ForecastMidExplorer extends ApiExplorer {
	String midFrcst="getMiddleForecast";
	String midLand="getMiddleLandWeather";
	String midTemp="getMiddleTemperature";
	int stnId=109;	//서울, 인천, 경기도 기상 전망조회  구역 코드
	String regLandId="11B00000";//서울, 인천, 경기도 육상 예보 구역 코드
	String regTempId="11B10101";//서울 기온 예보 구역 코드
	long tmFrcst;
	String resultFrcst;
	String resultLand;
	String resultTemp;
	ForecastMidExplorer() throws IOException {
		tmFrcst=makeTmFrcst();
		resultFrcst=send(Frcst_Request());
		resultLand=send(Land_Request());
		resultTemp=send(Temp_Request());
	}
	public long makeTmFrcst(){
		int iTime;
		long lDate=cDate;
		if(cTime>1800)
			iTime=1800;
		else if(cTime<=600){
			iTime=1800;
			lDate--;
		}
		else
			iTime=600;
		return lDate*10000l+iTime;
	}
	private String Frcst_Request() {
		
		StringBuilder forecastURL = new StringBuilder(FCMidUrl + midFrcst);
		forecastURL.append("?ServiceKey=" + FCMidKey);// URLEncoder.encode(serviceKey,"UTF-8"));
		forecastURL.append("&_type=json");
		forecastURL.append("&stnId=" + stnId);
		forecastURL.append("&tmFc="+tmFrcst);// 충무로
		forecastURL.append("&numOfRows=999");/* 검색할 줄 수 */
		forecastURL.append("&pageNo=1"); /* 나타낼 페이지 */
		return forecastURL.toString();
	}
	private String Land_Request(){
		StringBuilder forecastURL = new StringBuilder(FCMidUrl + midLand);
		forecastURL.append("?ServiceKey=" + FCMidKey);
		forecastURL.append("&_type=json");
		forecastURL.append("&regId=" + regLandId);
		forecastURL.append("&tmFc="+tmFrcst);// 충무로
		forecastURL.append("&numOfRows=999");/* 검색할 줄 수 */
		forecastURL.append("&pageNo=1"); /* 나타낼 페이지 */
		return forecastURL.toString();
	}
	private String Temp_Request(){
		StringBuilder forecastURL = new StringBuilder(FCMidUrl + midTemp);
		forecastURL.append("?ServiceKey=" + FCMidKey);
		forecastURL.append("&_type=json");
		forecastURL.append("&regId=" + regTempId);
		forecastURL.append("&tmFc="+tmFrcst);// 충무로
		forecastURL.append("&numOfRows=999");/* 검색할 줄 수 */
		forecastURL.append("&pageNo=1"); /* 나타낼 페이지 */
		return forecastURL.toString();
	}
	public String getFrcst(){
		return resultFrcst;
	}
	public String getLand(){
		return resultLand;
	}
	public String getTemp(){
		return resultTemp;
	}

}
