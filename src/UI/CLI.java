package UI;

import calculators.CalcWithLib;
import filemanagement.readers.JSONReader;
import filemanagement.readers.TXTReader;
import filemanagement.readers.XMLReader;
import filemanagement.writers.JSONWriter;
import filemanagement.writers.TXTWriter;
import filemanagement.writers.XMLWriter;
import filemanagement.writers.XMLWriterNonAPI;
import forcrypto.decryptors.Decryptor;
import forcrypto.encryptors.Encoder;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CLI {
    // command "cd filename"
    // command "dir"
    // command "encode filename"
    // command "decode filename"
    // command "addtoarch filename"
    // command "cleartoarch"
    // command "rar rarfile"
    // command "zip zipfile"
    // command "dezip zipfile"
    // command "derar rarfile"
    // command "read filename\s(normal file)"
    // command "calculate"
    // command "write"
    void encode(String filename) {
        dir();
    }
    ArrayList<String> read(String filename) {
        String format1 = "\\.xml$";
        String format2 = "\\.json$";
        String format3 = "\\.txt$";
        Pattern pat = Pattern.compile(format1);
        Matcher match = pat.matcher(filename);
        if (match.find()) {
            XMLReader reader = new XMLReader(currPath + "\\" + filename);
            return reader.read();
        }
        pat = Pattern.compile(format2);
        match = pat.matcher(filename);
        if (match.find()) {
            JSONReader reader = new JSONReader(currPath + "\\" + filename);
            return reader.read();
        }
        pat = Pattern.compile(format3);
        match = pat.matcher(filename);
        if (match.find()) {
            TXTReader reader = new TXTReader(currPath + "\\" + filename);
            return reader.read();
        }
        return new ArrayList<>();
    }
    void write(String filename) {
        String format1 = "\\.xml$";
        String format2 = "\\.json$";
        String format3 = "\\.txt$";
        Pattern pat = Pattern.compile(format1);
        Matcher match = pat.matcher(filename);
        if (match.find()) {
            XMLWriterNonAPI writer = new XMLWriterNonAPI(currPath + "\\" + filename);
            writer.write(expressions);
        }
        pat = Pattern.compile(format2);
        match = pat.matcher(filename);
        if (match.find()) {
            JSONWriter writer = new JSONWriter(currPath + "\\" + filename);
            writer.write(expressions);
        }
        pat = Pattern.compile(format3);
        match = pat.matcher(filename);
        if (match.find()) {
            TXTWriter writer = new TXTWriter(currPath + "\\" + filename);
            writer.write(expressions);
        }
    }
    public String dellitingSlashes(String fileName) {
        while (fileName.endsWith("\\")) {
            fileName = fileName.substring(0, fileName.length() - 1);
        }
        return fileName;
    }
    public String getAddressOfFile(String fileName) {
        String expression = "[^\\\\]*$";
        fileName = dellitingSlashes(fileName);
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(fileName);
        String address;
        if (matcher.find()) {
            address = fileName.substring(0, matcher.start());
        } else {
            address = fileName;
        }
        address = dellitingSlashes(address);
        return address;
    }
    public String getShortName(String fileName) {
        fileName = dellitingSlashes(fileName);
        String expression = "[^\\\\]*$";
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(fileName);
        if (matcher.find()) {
            return fileName.substring(matcher.start(), matcher.end());
        } else {
            return fileName;
        }
    }
    boolean isExist(String fileName) {
        ArrayList<String> files = getListOfFiles();
        return files.contains(fileName);
    }
    String currPath = "C:";
    ArrayList<String> expressions = new ArrayList<>();
    ArrayList<String> getListOfFiles() {
        File folder = new File(currPath);
        File[] files = folder.listFiles();
        ArrayList<String> ListOfNames = new ArrayList<>();
        if (files == null) {
            return new ArrayList<>();
        } else {
            for (File file : files) {
                ListOfNames.add(file.getName());
            }
        }
        return ListOfNames;
    }
    void cd(String name) {
        if (isExist(name)) {
            currPath = currPath + "\\" + name;
        } else {
            if (Objects.equals(name, "..")) {
                currPath = getAddressOfFile(currPath);
            }
        }
    }
    void dir() {
        ArrayList<String> arr = getListOfFiles();
        for (String s : arr) {
            System.out.println(s);
        }
    }
    public void doing() {
        Scanner in = new Scanner(System.in);
        while(true) {
            System.out.println(currPath);
            String command = in.nextLine();
            String[] data = command.split(" ");
            if (Objects.equals(data[0], "cd")) {
                if (data.length > 1)
                    cd(data[1]);
            }
            if (Objects.equals(data[0], "dir")) {
                dir();
            }
            if (Objects.equals(data[0], "encode")) {
                if (data.length > 1 && isExist(data[1])) {
                    String name = currPath + "\\" + data[1];
                    Encoder enc = new Encoder(name, "ssssssssssssssss");
                    enc.encryptFile();
                }
            }
            if (Objects.equals(data[0], "decode")) {
                if (data.length > 1 && isExist(data[1])) {
                    String name = currPath + "\\" + data[1];
                    Decryptor enc = new Decryptor(name, "ssssssssssssssss");
                    enc.decryptFile();
                }
            }
            if (Objects.equals(data[0], "read")) {
                if (data.length > 1 && isExist(data[1])) {
                    String name = currPath + "\\" + data[1];
                    expressions = read(data[1]);
                    System.out.println(expressions.getFirst());
                }
            }
            if (Objects.equals(data[0], "calculate")) {
                if (expressions.size() > 1) {
                    CalcWithLib calc = new CalcWithLib();
                    for (int i = 0; i < expressions.size(); i++) {
                        expressions.set(i, Double.toString(calc.Calculate(expressions.get(i))));
                    }
                    System.out.println(expressions.getFirst());
                }
            }
            if (Objects.equals(data[0], "write")) {
                if (data.length > 1 && isExist(data[1])) {
                    String name = currPath + "\\" + data[1];
                    write(data[1]);
                }
            }
        }
    }
}
