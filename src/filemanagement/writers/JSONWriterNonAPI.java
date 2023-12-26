package filemanagement.writers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class JSONWriterNonAPI extends Writer{
    public JSONWriterNonAPI(String name) {
        fileName = name;
    }
    public void write(ArrayList<String> results) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));
            bufferedWriter.write("{\n");
            bufferedWriter.write(  "  \"expressions\": [\n");
            for (int i = 0 ; i < results.size() - 1; i++) {
                bufferedWriter.write("    {");
                bufferedWriter.write('\n');
                bufferedWriter.write("      \"expression\": " + "\"" + results.get(i) + "\"");
                bufferedWriter.write('\n');
                bufferedWriter.write("    },\n");
            }
            bufferedWriter.write("    {");
            bufferedWriter.write('\n');
            bufferedWriter.write("      \"expression\": " + "\"" + results.getLast() + "\"");
            bufferedWriter.write('\n');
            bufferedWriter.write("    }\n");
            bufferedWriter.write("  ]\n");
            bufferedWriter.write('}');
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
