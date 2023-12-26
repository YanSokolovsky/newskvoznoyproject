package tests;

import filemanagement.writers.JSONWriter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class JSONWriterTest {

    @Test
    void write() {
        ArrayList<String> res = new ArrayList<>();
        res.add("123");
        res.add("234");
        res.add("345");
        JSONWriter writer = new JSONWriter("D:\\testing\\output.json");
        writer.write(res);
    }
}