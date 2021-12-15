package tis.kw2.web.components.weather;

import tis.kw2.web.helpers.*;

import java.util.*;

import static tis.kw2.web.components.weather.WeatherResource.getWeatherByCityName;

public class WeatherService {

	public static Object[] getWeatherByCityNameController(String name) {
		Object[] goList = new Object[2];
		try {
			String data = getWeatherByCityName(name);
			Map parse = JSONParser.parse(data);
//			parse.forEach((k, v) -> System.out.println(k + " = " + v));
			goList[1] = parse;
		} catch (Exception e) {
			goList[0] = e.getMessage();
		}
		return goList;
	}

	public static Map<String, String> getWeatherData(String name) {
		try {
			Object[] data = getWeatherByCityNameController(name);
			if (Objects.nonNull(data[0]) || Objects.isNull(data[1])) return null;
			Map info = (Map) data[1];
			return parseWeatherInformation(info);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	private static Map<String, String> parseWeatherInformation(Map<String, Object> info) {
		Map toReturn = new HashMap();
		String tab = "  ";
		StringBuilder sb = new StringBuilder("Weather information:\n");
		Map coord = (Map) info.get("coord");
		sb.append("Coordinates: \n" + tab + "longitude: ").append(coord.get("lon")).append("\n")
				.append(tab + "latitude: ").append(coord.get("lat")).append("\n");

		Map<String, String> weather = (Map) ((List) info.get("weather")).get(0);

		String weatherMain = weather.get("main");
		sb.append("Weather: \n" + tab +  "main: ").append(weatherMain).append("\n")
				.append(tab + "description: ").append(weather.get("description")).append("\n");

		Map main = (Map) info.get("main");
		sb.append("Temperature: \n" + tab + "main: ").append(main.get("temp")).append("\n")
				.append(tab + "feels like: ").append(main.get("feels_like")).append("\n")
				.append(tab + "min temperature: ").append(main.get("temp_min")).append("\n")
				.append(tab + "max temperature: ").append(main.get("temp_max")).append("\n");

		sb.append("Humidity: ").append(main.get("humidity")).append("\n");
		System.out.println(sb);

		Map wind = (Map) info.get("wind");
		sb.append("Wind: \n" + tab + "speed: ").append(wind.get("speed")).append("\n")
				.append(tab + "gust: ").append(wind.get("gust")).append("\n");

		toReturn.put("information", String.valueOf(sb));

		toReturn.put("image", getImage(weatherMain));

		return toReturn;
	}

	private static String getImage(String weatherMain) {
		switch (weatherMain) {
			case "Clouds":
				return "/img/cloudWeather.png";
			// todo
			default:
				return "";
		}
	}
}
