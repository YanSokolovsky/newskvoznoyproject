package tests;

import filemanagement.readers.TXTReader;
import filemanagement.writers.TXTWriter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TXTWriterTest {

    @Test
    void write() {
        ArrayList<String> res = new ArrayList<>();
        res.add("123");
        res.add("234");
        res.add("345");
        TXTWriter writer = new TXTWriter("D:\\testing\\output.txt");
        writer.write(res);
        TXTReader reader = new TXTReader("D:\\testing\\output.txt");
        ArrayList<String> arr = reader.read();
        assertEquals(arr.get(0), "123");
        assertEquals(arr.get(1), "234");
        assertEquals(arr.get(2), "345");
    }
}