package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import filemanagement.readers.XMLReader;
import filemanagement.writers.Writer;
import filemanagement.writers.XMLSerializer;
import filemanagement.writers.XMLWriter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class XMLWriterTest {

    @Test
    void write() {
        ArrayList<String> res = new ArrayList<>();
        res.add("123");
        res.add("234");
        res.add("345");
        XMLWriter writer = new XMLWriter("D:\\testing\\output.xml");
        writer.write(res);
        XMLReader reader = new XMLReader("D:\\testing\\output.xml");
        ArrayList<String> arr = reader.read();
        assertEquals(arr.get(0), "123");
        assertEquals(arr.get(1), "234");
        assertEquals(arr.get(2), "345");
    }
}