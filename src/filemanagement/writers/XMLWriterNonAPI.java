package filemanagement.writers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class XMLWriterNonAPI extends Writer{
    XMLWriterNonAPI(String name) {
        fileName = name;
    }
    void write(ArrayList<String> results) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));
            bufferedWriter.write("<data>");
            bufferedWriter.newLine();
            bufferedWriter.write(  "  <expressions>");
            bufferedWriter.newLine();
            for (String result : results) {
                bufferedWriter.write("\t<text>");
                bufferedWriter.newLine();
                bufferedWriter.write("\t  <expression>" + result + "</expression>");
                bufferedWriter.newLine();
                bufferedWriter.write("    </text>");
            }
            bufferedWriter.newLine();
            bufferedWriter.write("  </expressions>");
            bufferedWriter.newLine();
            bufferedWriter.write("</data>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
