package BB89VX1112;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class JSONReadBB89VX {

    public static void main(String[] args) {
        // Path to the JSON file
        String jsonFilePath = "BB89VX_1112/JSONParseBB89VX/src/BB89VX1112/BB89VX_orarend.json";

        try {
            // Parse the JSON file
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(jsonFilePath));

            // Display JSON content in structured format
            parseAndDisplayJson(jsonObject, "");

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    // Recursive method to display JSON content in "field: value" format
    private static void parseAndDisplayJson(JSONObject jsonObject, String indent) {
        jsonObject.forEach((key, value) -> {
            System.out.print(indent + key + ": ");
            if (value instanceof JSONObject) {
                System.out.println();
                parseAndDisplayJson((JSONObject) value, indent + "    ");
            } else if (value instanceof JSONArray) {
                System.out.println();
                parseAndDisplayArray((JSONArray) value, indent + "    ");
            } else {
                System.out.println(value);
            }
        });
    }

    // Method to handle JSONArray elements
    private static void parseAndDisplayArray(JSONArray array, String indent) {
        array.forEach(item -> {
            if (item instanceof JSONObject) {
                parseAndDisplayJson((JSONObject) item, indent);
            } else {
                System.out.println(indent + item);
            }
        });
    }
}
