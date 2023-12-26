package tests;

import filemanagement.writers.XMLWriter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class XMLWriterTest {

    @Test
    void write() {
        ArrayList<String> res = new ArrayList<>();
        res.add("123");
        res.add("234");
        res.add("345");
        XMLWriter writer = new XMLWriter("D:\\testing\\output.xml");
        writer.write(res);
    }
}