import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ForecastParser {
	protected String param;
	private boolean isSpace = true;// 실황 정보
	ForecastExplorer FE = new ForecastExplorer();
	String str_grib;
	String str_space;
	JSONParser jsonParser;

	ForecastParser(HashMap<String, String> mapSpace[], HashMap<String, String> mapGrib)
			throws IOException, ParseException {
		str_grib = FE.send(!isSpace);
		str_space = FE.send(isSpace);
		jsonParser = new JSONParser();
		System.out.println(str_grib);
		// System.out.println(str_space);
		GribParsing(mapGrib);
	}

	private void GribParsing(HashMap<String, String> mapGrib) throws IOException, ParseException {
		JSONObject json = (JSONObject) jsonParser.parse(str_grib);
		JSONObject resp = (JSONObject) json.get("response");
		JSONObject body = (JSONObject) resp.get("body");
		JSONObject items = (JSONObject) body.get("items");
		JSONArray item = (JSONArray) items.get("item");
		for (int i = 0; i < item.size(); i++) {
			JSONObject weatherObject = (JSONObject) item.get(i);
			mapGrib.put(weatherObject.get("category").toString(), weatherObject.get("obsrValue").toString());
		}
		Iterator<String> iter = mapGrib.keySet().iterator();
		while (iter.hasNext()) {
			String key = (String) iter.next();
			System.out.print("key=" + key);
			System.out.println(" value=" + mapGrib.get(key));
		}
	}
}