package JDP;
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
	ForecastExplorer FE = new ForecastExplorer();
	String str_Grib =FE.getGrib();
	String str_Space=FE.getSpace();
	JSONParser jsonParser=new JSONParser();
	int spaceTime=FE.make_SpaceTime();
	int noTime=get_NoTime(spaceTime);
	int SIZE[] = { 11, 9, 12, 9, 11, 10, 11, 9 };
	HashMap<String, String> mapSpace[]; 
 	HashMap<String, String> mapGrib=new HashMap<String,String>();
 	
 	
	ForecastParser() throws IOException, ParseException {
	 	mapSpace=new HashMap[noTime];
		for(int i=0;i<noTime;i++)
			mapSpace[i]=new HashMap<String,String>();
		spaceParsing();
		gribParsing();
	}
	
	private void gribParsing() throws IOException {
		JSONObject json;
		System.out.println(str_Grib);
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
		Iterator<String> iter = mapGrib.keySet().iterator();
		while (iter.hasNext()) {
			String key = (String) iter.next();
			System.out.print("key=" + key);
			System.out.println(" value=" + mapGrib.get(key));
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
	private void spaceParsing() throws IOException {
		JSONObject json;
		int item_Index = 0;
		int objectSize;
		String fcstTime,fcstDate;
		//System.out.println(mapSpace.length);
		try {
			json = (JSONObject) jsonParser.parse(str_Space);
			JSONObject resp = (JSONObject) json.get("response");
			JSONObject body = (JSONObject) resp.get("body");
			JSONObject items = (JSONObject) body.get("items");
			JSONArray item = (JSONArray) items.get("item");
			for (int mapIndex = 0; mapIndex <noTime; mapIndex++) { // 날짜 별로 순서대로
				//System.out.println(mapIndex);
				JSONObject weatherObject = (JSONObject) item.get(item_Index);//처음 날짜 받아오기 
				fcstDate = weatherObject.get("fcstDate").toString(); // 저장
				fcstTime = weatherObject.get("fcstTime").toString();
				mapSpace[mapIndex].put("fcstDate", fcstDate);
				mapSpace[mapIndex].put("fcstTime", fcstTime);
				switch (fcstTime) {
				case "0000":// 11
				case "1200":
				case "1800":
					objectSize=11;
					putData(mapSpace[mapIndex], item, objectSize,item_Index);
					item_Index+=objectSize;
					
					break;
				case "0300":// 9
				case "0900":
				case "2100":
					objectSize=9;
					putData(mapSpace[mapIndex], item, objectSize,item_Index);
					item_Index+=objectSize;
					break;
				case "0600":// 12
					objectSize=12;
					putData(mapSpace[mapIndex], item, objectSize,item_Index);
					item_Index+=objectSize;
					break;
				case "1500":// 10
						objectSize=10;
						putData(mapSpace[mapIndex], item, objectSize,item_Index);
						item_Index+=objectSize;
					break;
				}
				Iterator<String> iter = mapSpace[mapIndex].keySet().iterator();
				while (iter.hasNext()) {
					String key = (String) iter.next();
					System.out.print("key=" + key);
					System.out.println(" value=" + mapSpace[mapIndex].get(key));
				}
				//System.out.println(item_Index);
			}
		} catch (ParseException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public HashMap<String, String> getGribHashMap(){ 
		 		return mapGrib; 
		}
	public HashMap<String, String>[] getSpaceHashMap(){ 
 		return mapSpace; 
} 

}