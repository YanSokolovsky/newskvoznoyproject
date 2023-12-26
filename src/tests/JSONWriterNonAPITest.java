package tests;

import filemanagement.writers.JSONWriterNonAPI;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class JSONWriterNonAPITest {

    @Test
    void write() {
        ArrayList<String> res = new ArrayList<>();
        res.add("123");
        res.add("234");
        res.add("345");
        JSONWriterNonAPI writer = new JSONWriterNonAPI("D:\\testing\\output.json");
        writer.write(res);
    }
}