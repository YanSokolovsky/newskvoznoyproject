package filemanagement.readers;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class JSONReader extends Reader{
    public JSONReader(String filename) {
        fileName = filename;
    }
    @Override
    public ArrayList<String> read(){
        ArrayList<String> expressions = new ArrayList<>();
        ObjectMapper obMap = new ObjectMapper();
        data Data;
        try {
            Data = obMap.readValue(new File(fileName), data.class);
            for (int i = 0 ; i < Data.expressions.size(); i++) {
                expressions.add(Data.expressions.get(i).expression);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return expressions;
    }
}
