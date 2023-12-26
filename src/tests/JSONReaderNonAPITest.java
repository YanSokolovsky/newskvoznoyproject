package tests;

import filemanagement.readers.JSONReaderNonAPI;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JSONReaderNonAPITest {
    @Test
    void read() {
        JSONReaderNonAPI reader = new JSONReaderNonAPI("D:\\testing\\input.json");
        ArrayList<String> arr = reader.read();
        assertEquals(arr.get(0), "123.56 + 34.45 * 4 + (34 -43) * 3 - 2 ^ 4");
        assertEquals(arr.get(1), "(1 - 2)+(  1 + 3)+(4  *    4)+(3 - 2)+( 0 + 0)+(123 / 123)");
        assertEquals(arr.get(2), "(((((((((((1 + 1) + 1) + 1) + 1) + 1)))) + 1)))");
    }

}