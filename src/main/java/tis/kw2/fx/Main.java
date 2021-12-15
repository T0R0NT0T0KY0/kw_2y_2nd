package tis.kw2.fx;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

import static tis.kw2.web.components.weather.WeatherService.getWeatherByCityNameController;
import static tis.kw2.web.components.weather.WeatherService.getWeatherData;

public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Weather Application");
		stage.setWidth(500);
		stage.setHeight(300);
		stage.toFront();

		InputStream iconStream = getClass().getResourceAsStream("/img/icon.png");
		Image icon = new Image(iconStream);
		stage.getIcons().add(icon);

		Label label = new Label();
		TextField textField = new TextField();
		textField.setPrefColumnCount(11);
		Button button = new Button("search");

		button.setOnAction(event -> {
			Map<String, String> weatherData = getWeatherData(textField.getText());
			if (Objects.isNull(weatherData)) return;
			label.setText(weatherData.get("information"));
			InputStream is = getClass().getResourceAsStream(weatherData.get("image"));
			Image img = new Image(is);
		});
		FlowPane root = new FlowPane(Orientation.VERTICAL, 10, 10, textField, button, label);
		Scene scene = new Scene(root, 250, 200);

		stage.setScene(scene);
		stage.setTitle("Weather searcher");
		stage.show();
	}

}
