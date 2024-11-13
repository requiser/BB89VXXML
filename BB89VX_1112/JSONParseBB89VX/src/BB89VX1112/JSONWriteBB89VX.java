package BB89VX1112;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JSONWriteBB89VX {

    public static void main(String[] args) {
        // Paths for the input JSON file and output JSON file
        String inputFilePath = "BB89VX_1112/JSONParseBB89VX/src/BB89VX1112/BB89VX_orarend.json";
        String outputFilePath = "BB89VX_1112/JSONParseBB89VX/src/BB89VX1112/BB89VX_orarend_1.json";

        try {
            // Parse the JSON file
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(inputFilePath));

            // Write JSON content in structured format to a new file
            writeJsonToFile(jsonObject, outputFilePath);

            // Print JSON content in block format to the console
            printJsonInBlockFormat(jsonObject, "");

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    // Method to write JSON content in a structured format to a file
    private static void writeJsonToFile(JSONObject jsonObject, String filePath) {
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(formatJson(jsonObject, ""));
            file.flush();
            System.out.println("Structured JSON data written to file: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to print JSON content in a structured, indented block format to the console
    private static void printJsonInBlockFormat(JSONObject jsonObject, String indent) {
        jsonObject.forEach((key, value) -> {
            System.out.print(indent + key + ": ");
            if (value instanceof JSONObject) {
                System.out.println();
                printJsonInBlockFormat((JSONObject) value, indent + "  ");
            } else if (value instanceof JSONArray) {
                System.out.println();
                printJsonArray((JSONArray) value, indent + "  ");
            } else {
                System.out.println(value);
            }
        });
    }

    // Method to format JSON as an indented string for writing to file
    private static String formatJson(JSONObject jsonObject, String indent) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        jsonObject.forEach((key, value) -> {
            sb.append(indent).append("  ").append("\"").append(key).append("\": ");
            if (value instanceof JSONObject) {
                sb.append(formatJson((JSONObject) value, indent + "  "));
            } else if (value instanceof JSONArray) {
                sb.append(formatJsonArray((JSONArray) value, indent + "  "));
            } else {
                sb.append("\"").append(value).append("\"");
            }
            sb.append(",\n");
        });
        if (sb.length() > 2) sb.setLength(sb.length() - 2); // Remove the last comma
        sb.append("\n").append(indent).append("}");
        return sb.toString();
    }

    // Method to print JSONArray with indentation for console
    private static void printJsonArray(JSONArray array, String indent) {
        array.forEach(item -> {
            if (item instanceof JSONObject) {
                printJsonInBlockFormat((JSONObject) item, indent);
            } else {
                System.out.println(indent + item);
            }
        });
    }

    // Method to format JSON array as an indented string
    private static String formatJsonArray(JSONArray array, String indent) {
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        array.forEach(item -> {
            if (item instanceof JSONObject) {
                sb.append(indent).append("  ").append(formatJson((JSONObject) item, indent + "  "));
            } else {
                sb.append(indent).append("  ").append("\"").append(item).append("\"");
            }
            sb.append(",\n");
        });
        if (sb.length() > 2) sb.setLength(sb.length() - 2); // Remove the last comma
        sb.append("\n").append(indent).append("]");
        return sb.toString();
    }
}
