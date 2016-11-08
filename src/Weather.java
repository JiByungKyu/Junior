import java.io.IOException;
import java.util.HashMap;

import org.json.simple.parser.ParseException;

public class Weather {
	HashMap<String, String> Space[];// 예보
	HashMap<String, String> Grib = new HashMap(); // 정보

	Weather() throws IOException, ParseException {
		ForecastParser fpGrib = new ForecastParser(Grib);
		ForecastParser fpSpace = new ForecastParser(Space);
	}
}