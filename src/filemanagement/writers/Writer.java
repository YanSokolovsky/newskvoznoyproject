package filemanagement.writers;

import java.util.ArrayList;

public abstract class Writer {
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    String fileName;
    public static class text {
        public String expression;
    }
    public static class data {
        public ArrayList<text> expressions;
    }
    abstract void write(ArrayList<String> expressions);
}
