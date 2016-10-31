import java.io.IOException;
import java.io.StringReader;

import javax.json.Json;
import javax.json.stream.JsonParser;

public class ForecastParser {
	protected String param;
	private boolean isSpace = true;//
	ForecastExplorer FE = new ForecastExplorer();

	ForecastParser(WeatherData Space[], WeatherData Grib) throws IOException {

		System.out.println(FE.send(isSpace));
		System.out.println(FE.send(!isSpace));
		GribParsing();

	}

	private void GribParsing() throws IOException {
		JsonParser parser = Json.createParser(new StringReader(FE.send(!isSpace)));
		while (parser.hasNext()) {
			JsonParser.Event event = parser.next();
			switch (event) {
			case START_ARRAY:
			case END_ARRAY:
			case START_OBJECT:
			case END_OBJECT:
			case VALUE_FALSE:
			case VALUE_NULL:
			case VALUE_TRUE:
				System.out.println(event.toString());
				break;
			case KEY_NAME:
				System.out.print(event.toString() + " " + parser.getString() + " - ");
				break;
			case VALUE_STRING:
			case VALUE_NUMBER:
				System.out.println(event.toString() + " " + parser.getString());
				break;
			default:
				break;
			}

		}
	}

}
