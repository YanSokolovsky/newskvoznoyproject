package filemanagement.readers;

import java.util.ArrayList;

public abstract class Reader {
    String fileName;
    static class text {
        public String expression;
    }
    static class data {
        public ArrayList<text> expressions;
    }
    String FileName;
    abstract ArrayList<String> read();
}
