package parsing;


import com.fasterxml.jackson.databind.ObjectMapper;
import fmu.Model;

import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonParser {
    public static void main(String[] args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Model model = objectMapper.readValue(Files.readAllBytes(Paths.get("src/main/resources/json/CraneController.json")), Model.class);

        System.out.println(model);
    }
}
