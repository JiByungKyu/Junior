package JDP;
import java.io.IOException;
import java.util.HashMap;

import org.json.simple.parser.ParseException;

public class Weather {
	HashMap<String, String> Space[];// 예보
	HashMap<String, String> Grib = new HashMap(); // 정보
	ForecastParser fpGrib;
	ForecastParser fpSpace;
	Weather() throws IOException, ParseException {
		fpGrib = new ForecastParser(Grib);
		fpSpace = new ForecastParser(Space);
	}
}