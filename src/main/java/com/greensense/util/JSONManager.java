package com.greensense.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class JSONManager {

    public final static String GREENHOUSES_FILE = "json/greenhouses.json";
    public final static String USERS_FILE = "json/users.json";

    private JSONManager() {}

    public <T> void writeToJSON(String filename, T value) throws IOException {

        ObjectMapper mapper = new CustomObjectMapper();

        try (FileWriter fileWriter = new FileWriter(filename)) {
            mapper.writeValue(fileWriter, value);
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    public static <T> T loadJSON(String filepath, TypeReference<T> type) throws IOException {

        ObjectMapper mapper = new CustomObjectMapper();

        InputStream inputStream = JSONManager.class.getClassLoader().getResourceAsStream(filepath);

        if (inputStream == null) {
            throw new FileNotFoundException("File not found: " + filepath);
        }
        
        return mapper.readValue(inputStream, type);

    }

}