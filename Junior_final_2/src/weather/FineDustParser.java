package weather;
import java.util.HashMap;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FineDustParser {
	JSONParser jsonParser = new JSONParser();
	FineDustExplorer FDE= new FineDustExplorer();
	HashMap<String, String> mapFnDst=new HashMap<String,String>();
	String str_FineDust = FDE.getFnDst();
 	String realFrcst;
 	public FineDustParser(){
 		fnDstParsing();
 	}
	private void fnDstParsing() {

		JSONObject json;
		try {
			json = (JSONObject) jsonParser.parse(str_FineDust);

			JSONArray list = (JSONArray) json.get("list");
			for(int i=0;i<list.size();i++){
				JSONObject fDObject = (JSONObject) list.get(i);
				if(fDObject.get("stationName").equals("중구")){
					mapFnDst.put("stationName", fDObject.get("stationName").toString());
					mapFnDst.put("pm25Value", fDObject.get("pm25Value").toString());
					mapFnDst.put("pm10Value", fDObject.get("pm10Value").toString());
					
					mapFnDst.put("dataTime", fDObject.get("dataTime").toString());
					mapFnDst.put("o3Value", fDObject.get("o3Value").toString());
					mapFnDst.put("no2Value", fDObject.get("no2Value").toString());
					mapFnDst.put("so2Value", fDObject.get("so2Value").toString());
					mapFnDst.put("coValue", fDObject.get("coValue").toString());
					Iterator<String> iter = mapFnDst.keySet().iterator();
					break;
				}
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public HashMap<String,String> getFnDstHashMap(){
		return mapFnDst;
	}
}
