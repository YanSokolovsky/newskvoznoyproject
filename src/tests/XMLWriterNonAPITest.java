package tests;

import filemanagement.readers.XMLReaderNonAPI;
import filemanagement.writers.Writer;
import filemanagement.writers.XMLWriter;
import filemanagement.writers.XMLWriterNonAPI;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class XMLWriterNonAPITest {

    @Test
    void write() {
        ArrayList<String> res = new ArrayList<>();
        res.add("123");
        res.add("234");
        res.add("345");
        XMLWriterNonAPI writer = new XMLWriterNonAPI("D:\\testing\\output.xml");
        writer.write(res);
        XMLReaderNonAPI reader = new XMLReaderNonAPI("D:\\testing\\output.xml");
        ArrayList<String> arr = reader.read();
        assertEquals(arr.get(0), "123");
        assertEquals(arr.get(1), "234");
        assertEquals(arr.get(2), "345");
    }
}