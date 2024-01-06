package tests;

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
    }
}