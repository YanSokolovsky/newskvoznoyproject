package UI;

import calculators.CalcWithLib;
import filemanagement.readers.JSONReader;
import filemanagement.readers.TXTReader;
import filemanagement.readers.XMLReader;
import filemanagement.writers.JSONWriter;
import filemanagement.writers.TXTWriter;
import filemanagement.writers.XMLWriter;
import filemanagement.writers.XMLWriterNonAPI;
import forarchive.archivers.RarArchivator;
import forarchive.dearchivers.RarDearchivator;
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
    void archive(String name) {
        String rar = "\\.rar$";
        String zip = "\\.zip$";
        Pattern pat = Pattern.compile(rar);
        Matcher match = pat.matcher(name);
        if (match.find()) {
            RarArchivator arch = new RarArchivator(name, filestoarch);
            arch.archive();
        }
        pat = Pattern.compile(zip);
        match = pat.matcher(name);
        if (match.find()) {
            RarArchivator arch = new RarArchivator(name, filestoarch);
            arch.archive();
        }
    }
    void dearchive(String name) {
        String rar = "\\.rar$";
        String zip = "\\.zip$";
        Pattern pat = Pattern.compile(rar);
        Matcher match = pat.matcher(name);
        if (match.find()) {
            RarDearchivator arch = new RarDearchivator(currPath + "\\" + name);
            arch.dearchive();
        }
        pat = Pattern.compile(zip);
        match = pat.matcher(name);
        if (match.find()) {
            RarDearchivator arch = new RarDearchivator(currPath + "\\" + name);
            arch.dearchive();
        }
    }
    void dearchive(String name, String destination) {
        String rar = "\\.rar$";
        String zip = "\\.zip$";
        Pattern pat = Pattern.compile(rar);
        Matcher match = pat.matcher(name);
        if (match.find()) {
            RarDearchivator arch = new RarDearchivator(currPath + "\\" + name);
            arch.dearchive(destination);
        }
        pat = Pattern.compile(zip);
        match = pat.matcher(name);
        if (match.find()) {
            RarDearchivator arch = new RarDearchivator(currPath + "\\" + name);
            arch.dearchive(destination);
        }
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
    ArrayList<String> filestoarch = new ArrayList<>();
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
        int i = 0;
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
            if (data.length == 0)
            {
                continue;
            }
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
                    Encoder enc = new Encoder(name, "kjshfkjhskdjfh kjsdhfk jhsdkjfh ikerh");
                    enc.encryptFile();
                }
            }
            if (Objects.equals(data[0], "decode")) {
                if (data.length > 1 && isExist(data[1])) {
                    String name = currPath + "\\" + data[1];
                    Decryptor enc = new Decryptor(name, "kjshfkjhskdjfh kjsdhfk jhsdkjfh ikerh");
                    enc.decryptFile();
                }
            }
            if (Objects.equals(data[0], "read")) {
                if (data.length > 1 && isExist(data[1])) {
                    String name = currPath + "\\" + data[1];
                    expressions = read(data[1]);
                }
            }
            if (Objects.equals(data[0], "calculate")) {
                if (expressions.size() > 1) {
                    CalcWithLib calc = new CalcWithLib();
                    for (int i = 0; i < expressions.size(); i++) {
                        expressions.set(i, Double.toString(calc.Calculate(expressions.get(i))));
                    }
                }
            }
            if (Objects.equals(data[0], "write")) {
                if (data.length > 1 && isExist(data[1])) {
                    String name = currPath + "\\" + data[1];
                    write(data[1]);
                }
            }
            if (Objects.equals(data[0], "clearbuff")) {
                expressions = new ArrayList<>();
            }
            if (Objects.equals(data[0], "cleararch")) {
                filestoarch = new ArrayList<>();
            }
            if (Objects.equals(data[0], "addfile")) {
                for (int i = 1; i < data.length; i++) {
                    filestoarch.add(currPath + "\\" + data[i]);
                }
            }
            if (Objects.equals(data[0], "archive") && data.length == 2) {
                archive(currPath + "\\" + data[1]);
            }
            if (Objects.equals(data[0], "dearchive") && data.length == 2) {
                dearchive(data[1]);
            }
            if (Objects.equals(data[0], "dearchive") && data.length == 3) {
                dearchive(data[1], data[2]);
            }
            if (Objects.equals(data[0], "clear")) {
                for (int i = 0; i < 100; i++)
                {
                    System.out.println('\n');
                }
            }
        }
    }
}
