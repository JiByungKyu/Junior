import java.io.IOException;

public class Weather {
	WeatherData Space[];
	WeatherData Grib;

	Weather() throws IOException {
		ForecastParser FP = new ForecastParser(Space, Grib);
	}
}
