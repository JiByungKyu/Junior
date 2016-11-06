package weather;
import java.io.IOException;
import java.util.HashMap;

import org.json.simple.parser.ParseException;

public class Weather {
	HashMap<String, String> Space[] = new HashMap[15];// 예보
	HashMap<String, String> Grib = new HashMap(); // 정보

	public Weather() throws IOException, ParseException {
		ForecastParser FP = new ForecastParser(Space, Grib);
	}
}