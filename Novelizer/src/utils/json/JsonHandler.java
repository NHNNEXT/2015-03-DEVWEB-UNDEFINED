package utils.json;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.option.Option;
import model.scene.Scene;

public class JsonHandler {
	private static final Logger log = LoggerFactory.getLogger(JsonHandler.class);

	public Scene convertToScene(String jsonData) {
		ObjectMapper objMapper = new ObjectMapper();

		Scene scene = null;
		try {
			scene = objMapper.readValue(jsonData, Scene.class);
		} catch (IOException e) {
			log.error("JsonString to Scene fail \n" + e);
			throw new RuntimeException();
		}

		return scene;

	}

	public Option convertToOption(String jsonData) {
		ObjectMapper objMapper = new ObjectMapper();

		Option option = null;
		try {
			option = objMapper.readValue(jsonData, Option.class);
		} catch (IOException e) {
			log.error("JsonString to Scene fail \n" + e);
			throw new RuntimeException();
		}

		return option;
	}

	public String convertToJson(Object object) {
		ObjectMapper objMapper = new ObjectMapper();

		String resultJson = null;
		try {
			resultJson = objMapper.writeValueAsString(object);
		} catch (IOException e) {
			log.error("Object to Json Fail \n" + e);
			throw new RuntimeException();
		}
		return resultJson;
	}
}
