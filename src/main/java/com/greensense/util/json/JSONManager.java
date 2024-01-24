package com.greensense.util.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.*;

import java.net.URL;
import java.net.URLDecoder;

public class JSONManager {

    public final static String ROOT_JSON_FOLDER = "src/main/json/";
    public final static String GREENHOUSES_FILE = ROOT_JSON_FOLDER + "greenhouses.json";
    public final static String USERS_FILE = ROOT_JSON_FOLDER + "users.json";
    public final static String ALERTS_FILE = ROOT_JSON_FOLDER + "alerts.json";

    private JSONManager() {}

    public static <T> void writeToJSON(String filename, T value) throws IOException {

        ObjectMapper mapper = new CustomObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        File outputFile = new File(filename);

        if (!outputFile.getParentFile().exists()){
            throw new IOException("Parent directory '" + outputFile.getParentFile() + "' does not exist");
        }

        try (FileWriter fileWriter = new FileWriter(outputFile)) {
            mapper.writeValue(fileWriter, value);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static <T> T loadJSON(String filepath, TypeReference<T> type) throws IOException {

        ObjectMapper mapper = new CustomObjectMapper();

        InputStream inputStream = new FileInputStream(filepath);
        
        return mapper.readValue(inputStream, type);

    }

}