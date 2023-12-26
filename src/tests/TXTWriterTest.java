package tests;

import filemanagement.writers.TXTWriter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class TXTWriterTest {

    @Test
    void write() {
        ArrayList<String> res = new ArrayList<>();
        res.add("123");
        res.add("234");
        res.add("345");
        TXTWriter writer = new TXTWriter("D:\\testing\\output.txt");
        writer.write(res);
    }
}