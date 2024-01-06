package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import filemanagement.writers.Writer;
import filemanagement.writers.XMLSerializer;
import filemanagement.writers.XMLWriter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class XMLWriterTest {

    @Test
    void write() {
        ObjectMapper mapper = new XmlMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Writer.data.class, new XMLSerializer());
        mapper.registerModule(module);
        ArrayList<String> res = new ArrayList<>();
        res.add("123");
        res.add("234");
        res.add("345");
        XMLWriter writer = new XMLWriter("D:\\testing\\output.xml");
        writer.write(res);
    }
}