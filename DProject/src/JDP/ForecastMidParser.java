package JDP;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ForecastMidParser {
	JSONParser jsonParser = new JSONParser();
	ForecastMidExplorer FME= new ForecastMidExplorer();
	HashMap<String, String> mapLand=new HashMap<String,String>();	//날씨정보 
	HashMap<String, String> mapTemp=new HashMap<String,String>();	//
	String str_Frcst = FME.getFrcst();
	String str_Land = FME.getLand();
	String str_Temp=FME.getTemp();
 	String realFrcst;
<<<<<<< HEAD
	public ForecastMidParser() throws IOException {
=======
	ForecastMidParser() throws IOException {
>>>>>>> 408dd76e91fce1f531c64a0ff2b2bd5390501be4
		frcstParsing();
		landParsing();
		tempParsing();
		System.out.println(realFrcst);
	}
	private void frcstParsing()throws IOException {

		JSONObject json;
		try {
			json = (JSONObject) jsonParser.parse(str_Frcst);

			JSONObject resp = (JSONObject) json.get("response");
			JSONObject body = (JSONObject) resp.get("body");
			JSONObject items = (JSONObject) body.get("items");
			JSONObject item = (JSONObject) items.get("item");
				realFrcst=item.get("wfSv").toString();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	private void landParsing()throws IOException {
		JSONObject json;
		try {
			json = (JSONObject) jsonParser.parse(str_Land);
			String wf;
			String am;
			String pm;
			JSONObject resp = (JSONObject) json.get("response");
			JSONObject body = (JSONObject) resp.get("body");
			JSONObject items = (JSONObject) body.get("items");
			JSONObject item = (JSONObject) items.get("item");
			for (int i = 3; i <= 10; i++) {
				if(i<=7){	
					am ="wf"+i+"Am";
					pm ="wf"+i+"Pm";		
					mapLand.put(am, item.get(am).toString());
					mapLand.put(pm, item.get(pm).toString());
				}
				else{
					wf="wf"+i;
					mapLand.put(wf, item.get(wf).toString());
				}
					
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Iterator<String> iter = mapLand.keySet().iterator();
		while (iter.hasNext()) {
			String key = (String) iter.next();
			System.out.print("key=" + key);
			System.out.println(" value=" + mapLand.get(key));
		}
	}
	private void tempParsing()throws IOException {
		JSONObject json;
		try {
			json = (JSONObject) jsonParser.parse(str_Temp);
			String min;
			String max;
			JSONObject resp = (JSONObject) json.get("response");
			JSONObject body = (JSONObject) resp.get("body");
			JSONObject items = (JSONObject) body.get("items");
			JSONObject item = (JSONObject) items.get("item");
			for (int i = 3; i <= 10; i++) {
				min="taMin"+i;
				max="taMax"+i;
				mapTemp.put(min, item.get(min).toString());
				mapTemp.put(max,item.get(max).toString());
				
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Iterator<String> iter = mapTemp.keySet().iterator();
		while (iter.hasNext()) {
			String key = (String) iter.next();
			System.out.print("key=" + key);
			System.out.println(" value=" + mapTemp.get(key));
		}
	}
	public String getFrcst(){
		return realFrcst;
	}
	public HashMap<String, String> getLandHashMap(){ 
 		return mapLand; 
	}
	public HashMap<String, String> getTempHashMap(){ 
 		return mapTemp; 
	}
}
