package filemanagement.writers;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

public abstract class Writer {
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    String fileName;
    @XmlRootElement
    public static class text {
        public String expression;
    }
    @XmlRootElement
    public static class data {
        public ArrayList<text> expressions;
    }
    abstract void write(ArrayList<String> expressions);
}
