package nhnnext.novelizer_android.util;

import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonHandler<T> {
    private final Class<T> ClassName;

    public JsonHandler(Class<T> MyClass) {
        this.ClassName = MyClass;
    }

    public String converToJSON(T type) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String resultJson = objectMapper.writeValueAsString(type);
            return resultJson;
        } catch (JsonProcessingException e) {
            Log.e("CONVERT JSON ERROR", "" + e);
            throw new RuntimeException();
        }

    }

    public T convertToObject(String jsonData) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            T type = objectMapper.readValue(jsonData, ClassName);
            return type;
        } catch (IOException e) {
            Log.e("CONVERT OBJECT ERROR", "" + e);
            throw new RuntimeException();
        }
    }
}
