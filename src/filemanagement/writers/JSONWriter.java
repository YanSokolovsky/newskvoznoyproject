package filemanagement.writers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class JSONWriter extends Writer{
    public JSONWriter(String name) {
        fileName = name;
    }
    public void write(ArrayList<String> results) {
        data rt = new data();
        rt.expressions = new ArrayList<>();
        for (String expression : results) {
            text j = new text();
            j.expression = expression;
            rt.expressions.add(j);
        }
        ObjectMapper obMap = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(data.class, new JSONSerializer());
        obMap.registerModule(module);
        File file = new File(fileName);
        try {
            obMap.writeValue(file, rt);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
