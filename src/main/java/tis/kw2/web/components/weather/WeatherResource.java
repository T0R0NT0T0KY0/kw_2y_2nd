package tis.kw2.web.components.weather;

import tis.kw2.web.helpers.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WeatherResource {

	private final static String WEATHER_TOKEN = Envs.getENV("WEATHER_TOKEN");

	public static String getWeatherByCityName(String city) throws IOException {
		Map<String, String> q = new HashMap<>();
		q.put("q", city);
		q.put("appid", WEATHER_TOKEN);
		q.put("units", "metric");
		return HttpClient.get("http://api.openweathermap.org/data/2.5/weather", q);
	}
}
