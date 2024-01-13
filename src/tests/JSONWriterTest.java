package tests;

import filemanagement.readers.JSONReader;
import filemanagement.writers.JSONWriter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JSONWriterTest {

    @Test
    void write() {
        ArrayList<String> res = new ArrayList<>();
        res.add("123");
        res.add("234");
        res.add("345");
        JSONWriter writer = new JSONWriter("D:\\testing\\output.json");
        writer.write(res);
        JSONReader reader = new JSONReader("D:\\testing\\output.json");
        ArrayList<String> arr = reader.read();
        assertEquals(arr.get(0), "123");
        assertEquals(arr.get(1), "234");
        assertEquals(arr.get(2), "345");
    }
}