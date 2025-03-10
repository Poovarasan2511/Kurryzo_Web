package utils;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadJson {

    public static JSONObject setJson(String filePath, String fileName) throws IOException 
    {
        String path = filePath.concat("\\").concat(fileName);
        String jsonString = readFileAsString(path);
        return new JSONObject(jsonString);
    }

    private static String readFileAsString(String file) throws IOException 
    {
        return new String(Files.readAllBytes(Paths.get(file)));
    }
}
