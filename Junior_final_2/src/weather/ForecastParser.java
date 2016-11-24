package weather;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/*
 * Date : 2016.11.05 
 * Author : Byungkyu Ji 
 * class: 기상정보 파싱 클래스
 */
public class ForecastParser {
<<<<<<< HEAD:Junior_final_2/src/weather/ForecastParser.java
	private ForecastExplorer FE = new ForecastExplorer();
	String str_Grib =getFE().getGrib();
	String str_Space=getFE().getSpace();
	JSONParser jsonParser=new JSONParser();
	int spaceTime=getFE().make_SpaceTime();
	private int noTime=get_NoTime(spaceTime);
	int SIZE[] = { 11, 9, 12, 9, 11, 10, 11, 9 };
	private HashMap<String, String> mapSpace[]; 
=======
	public ForecastExplorer FE = new ForecastExplorer();
	String str_Grib =FE.getGrib();
	String str_Space=FE.getSpace();
	JSONParser jsonParser=new JSONParser();
	public int spaceTime=FE.make_SpaceTime();
	public int noTime=get_NoTime(spaceTime);
	int SIZE[] = { 11, 9, 12, 9, 11, 10, 11, 9 };
	public HashMap<String, String> mapSpace[]; 
>>>>>>> be45af4268122ff1f701508c09e2f2efe93d56fa:DProject/src/JDP/ForecastParser.java
 	HashMap<String, String> mapGrib=new HashMap<String,String>();
 	
 	
	public ForecastParser() throws IOException, ParseException {
	 	setMapSpace(new HashMap[getNoTime()]);
		for(int i=0;i<getNoTime();i++)
			getMapSpace()[i]=new HashMap<String,String>();
		spaceParsing();
		gribParsing();
	}
	
	private void gribParsing() throws IOException {
		JSONObject json;
		try {
			json = (JSONObject) jsonParser.parse(str_Grib);

			JSONObject resp = (JSONObject) json.get("response");
			JSONObject body = (JSONObject) resp.get("body");
			JSONObject items = (JSONObject) body.get("items");
			JSONArray item = (JSONArray) items.get("item");
			for (int i = 0; i < item.size(); i++) {
				JSONObject weatherObject = (JSONObject) item.get(i);
				mapGrib.put(weatherObject.get("category").toString(), weatherObject.get("obsrValue").toString());
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	private void putData(HashMap<String, String> innerMap, JSONArray item, int size,int item_Index) { // JSONObject를
		JSONObject weatherObject = (JSONObject) item.get(item_Index);
		for(int i=0;i<size;i++){
			weatherObject = (JSONObject) item.get(item_Index+i);
			innerMap.put(weatherObject.get("category").toString(), weatherObject.get("fcstValue").toString()); // put
		}
	}
	private int get_NoTime(int baseTime){
		int noTime;	
		if(baseTime==200)
			noTime= 15;
		else{
			noTime=23-(baseTime/300);
		}
		return noTime;
	}
	private void spaceParsing() {
		JSONObject json;
		int item_Index = 0;
		int objectSize;
		String fcstTime,fcstDate;
		try {
			json = (JSONObject) jsonParser.parse(str_Space);
			JSONObject resp = (JSONObject) json.get("response");
			JSONObject body = (JSONObject) resp.get("body");
			JSONObject items = (JSONObject) body.get("items");
			JSONArray item = (JSONArray) items.get("item");
			for (int mapIndex = 0; mapIndex <getNoTime(); mapIndex++) { // 날짜 별로 순서대로
				JSONObject weatherObject = (JSONObject) item.get(item_Index);//처음 날짜 받아오기 
				fcstDate = weatherObject.get("fcstDate").toString(); // 저장
				fcstTime = weatherObject.get("fcstTime").toString();
				getMapSpace()[mapIndex].put("fcstDate", fcstDate);
				getMapSpace()[mapIndex].put("fcstTime", fcstTime);
				switch (fcstTime) {
				case "0000":// 11
				case "1200":
				case "1800":
					objectSize=11;
					putData(getMapSpace()[mapIndex], item, objectSize,item_Index);
					item_Index+=objectSize;
					
					break;
				case "0300":// 9
				case "0900":
				case "2100":
					objectSize=9;
					putData(getMapSpace()[mapIndex], item, objectSize,item_Index);
					item_Index+=objectSize;
					break;
				case "0600":// 12
					objectSize=12;
					putData(getMapSpace()[mapIndex], item, objectSize,item_Index);
					item_Index+=objectSize;
					break;
				case "1500":// 10
						objectSize=10;
						putData(getMapSpace()[mapIndex], item, objectSize,item_Index);
						item_Index+=objectSize;
					break;
				}
			}
		} catch (ParseException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public HashMap<String, String> getGribHashMap(){ 
		 		return mapGrib; 
		}
	public HashMap<String, String>[] getSpaceHashMap(){ 
 		return getMapSpace(); 
}

	public int getNoTime() {
		return noTime;
	}

	public void setNoTime(int noTime) {
		this.noTime = noTime;
	}

	public HashMap<String, String>[] getMapSpace() {
		return mapSpace;
	}

	public void setMapSpace(HashMap<String, String> mapSpace[]) {
		this.mapSpace = mapSpace;
	}

	public ForecastExplorer getFE() {
		return FE;
	}

	public void setFE(ForecastExplorer fE) {
		FE = fE;
	} 

}