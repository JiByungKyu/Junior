import java.io.IOException;

public class Weather {
	WeatherData Space[];// 동네예보
	WeatherData Grib;// 실황

	Weather() throws IOException {
		ForecastParser FP = new ForecastParser(Space, Grib);
	}
}
