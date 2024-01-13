package tests;

import filemanagement.readers.JSONReaderNonAPI;
import filemanagement.writers.JSONWriterNonAPI;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JSONWriterNonAPITest {

    @Test
    void write() {
        ArrayList<String> res = new ArrayList<>();
        res.add("123");
        res.add("234");
        res.add("345");
        JSONWriterNonAPI writer = new JSONWriterNonAPI("D:\\testing\\output.json");
        writer.write(res);
        JSONReaderNonAPI reader = new JSONReaderNonAPI("D:\\testing\\output.json");
        ArrayList<String> arr = reader.read();
        assertEquals(arr.get(0), "123");
        assertEquals(arr.get(1), "234");
        assertEquals(arr.get(2), "345");
    }
}