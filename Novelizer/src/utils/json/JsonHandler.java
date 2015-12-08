package utils.json;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.option.Option;
import model.scene.Scene;
import project.Project;

public class JsonHandler<T> {
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
	
	public Project convertToProject(String jsonData) {
		ObjectMapper objMapper = new ObjectMapper();

		Project project = null;
		try {
			project = objMapper.readValue(jsonData, Project.class);
		} catch (IOException e) {
			log.error("JsonString to Project fail \n" + e);
			throw new RuntimeException();
		}

		return project;
	}
	
	// Object를 T로 바꿨는데, 잘바꾼지 모르겠음
	public String convertToJson(T type) {
		ObjectMapper objMapper = new ObjectMapper();

		String resultJson = null;
		try {
			resultJson = objMapper.writeValueAsString(type);
		} catch (IOException e) {
			log.error("Object to Json Fail \n" + e);
			throw new RuntimeException();
		}
		return resultJson;
	}
	
	// convertMethod 추상화
	@SuppressWarnings({ "unchecked", "null" })
	public T convertToType(String jsonData) {
		ObjectMapper objMapper = new ObjectMapper();
		
		T t = null;
		try {
			t = objMapper.readValue(jsonData, (Class<T>)t.getClass());
		} catch (Exception e) {
			log.error("JsonString to {} fail \n" + e, t.toString());
			throw new RuntimeException();
		}
		return t;
	}
}
