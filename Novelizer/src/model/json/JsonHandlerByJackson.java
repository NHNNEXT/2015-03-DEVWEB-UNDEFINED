package model.json;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.ObjectMapper;

import vo.scene.Scene;

public class JsonHandlerByJackson {
	private static final Logger log = LoggerFactory.getLogger(JsonHandlerByJackson.class);

	public Scene convertToObject(String jsonData) {
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
}
