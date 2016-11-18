package JDP;
import java.io.IOException;

/*
 * author : ji
 * 2016.11.17
 */
public class ForecastMidExplorer extends ApiExplorer {
	String midFrcst="getMiddleForecast";
	String midLand="getMiddleLandWeather";
	String midTemp="getMiddleTemperature";
	int stnId=109;	//�꽌�슱, �씤泥�, 寃쎄린�룄 湲곗긽 �쟾留앹“�쉶  援ъ뿭 肄붾뱶
	String regLandId="11B00000";//�꽌�슱, �씤泥�, 寃쎄린�룄 �쑁�긽 �삁蹂� 援ъ뿭 肄붾뱶
	String regTempId="11B10101";//�꽌�슱 湲곗삩 �삁蹂� 援ъ뿭 肄붾뱶
	long tmFrcst;
	String resultFrcst;
	String resultLand;
	String resultTemp;
	ForecastMidExplorer() throws IOException {
		tmFrcst=makeTmFrcst();
		resultFrcst=send(Frcst_Request());
		resultLand=send(Land_Request());
		resultTemp=send(Temp_Request());
		System.out.println(resultTemp);
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
		forecastURL.append("&tmFc="+tmFrcst);// 異⑸Т濡�
		forecastURL.append("&numOfRows=999");/* 寃��깋�븷 以� �닔 */
		forecastURL.append("&pageNo=1"); /* �굹���궪 �럹�씠吏� */
		return forecastURL.toString();
	}
	private String Land_Request(){
		StringBuilder forecastURL = new StringBuilder(FCMidUrl + midLand);
		forecastURL.append("?ServiceKey=" + FCMidKey);
		forecastURL.append("&_type=json");
		forecastURL.append("&regId=" + regLandId);
		forecastURL.append("&tmFc="+tmFrcst);// 異⑸Т濡�
		forecastURL.append("&numOfRows=999");/* 寃��깋�븷 以� �닔 */
		forecastURL.append("&pageNo=1"); /* �굹���궪 �럹�씠吏� */
		return forecastURL.toString();
	}
	private String Temp_Request(){
		StringBuilder forecastURL = new StringBuilder(FCMidUrl + midTemp);
		forecastURL.append("?ServiceKey=" + FCMidKey);
		forecastURL.append("&_type=json");
		forecastURL.append("&regId=" + regTempId);
		forecastURL.append("&tmFc="+tmFrcst);// 異⑸Т濡�
		forecastURL.append("&numOfRows=999");/* 寃��깋�븷 以� �닔 */
		forecastURL.append("&pageNo=1"); /* �굹���궪 �럹�씠吏� */
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
