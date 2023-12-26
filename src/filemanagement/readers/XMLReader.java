package filemanagement.readers;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class XMLReader extends Reader{
    public XMLReader(String name)
    {
        fileName = name;
    }
    @Override
    public ArrayList<String> read() {
        XmlMapper obMap = new XmlMapper();
        data Data;
        try {
            Data = obMap.readValue(new File(fileName), data.class);
            ArrayList<String> expressions = new ArrayList<>();
            for (int i = 0 ; i < Data.expressions.size(); i++) {
                expressions.add(Data.expressions.get(i).expression);
            }
            return expressions;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
