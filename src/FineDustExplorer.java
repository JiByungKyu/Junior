import java.io.IOException;

public class FineDustExplorer extends ApiExplorer{
		String resultFineDust;
		String sido="서울";
		String fineDust="getCtprvnRltmMesureDnsty";
		String ver="1.3";
		FineDustExplorer(){
			try {
				resultFineDust=send(FineDust_Request());
							} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public String FineDust_Request() {
		StringBuilder forecastURL = new StringBuilder(airUrl + fineDust);
		forecastURL.append("?ServiceKey=" + AirDustKey);
		forecastURL.append("&_returnType=json");
		forecastURL.append("&sidoName=" + sido);
		forecastURL.append("&ver=" + ver);// 충무로
		forecastURL.append("&numOfRows=999");/* 검색할 줄 수 */
		forecastURL.append("&pageNo=1"); /* 나타낼 페이지 */
		return forecastURL.toString();
	}
		public String getFnDst(){
			return resultFineDust;
		}
}
