import java.io.IOException;

public class Weather {
	WeatherData Space[];// ���׿���
	WeatherData Grib;// ��Ȳ

	Weather() throws IOException {
		ForecastParser FP = new ForecastParser(Space, Grib);
	}
}
