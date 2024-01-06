package forcrypto.encryptors;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Shivrator {
    String Key;
    public void deleteLastKBytes(String filePath, int k) {
        RandomAccessFile file = null;
        try {
            file = new RandomAccessFile(filePath, "rw");
            long length = file.length();
            length = length - k;
            file.setLength(length);
            file.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public byte[] extendArray(byte[] originalArray, int newSize) {
        return Arrays.copyOf(originalArray, newSize);
    }
    public String generateNewName(String filepath) {
        filepath = dellitingSlashes(filepath);
        String path = getAddressOfFile(filepath);
        String name = getShortName(filepath);
        name = name.replace(".", "");
        return path + "\\" + name + ".enc";
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
    public boolean isDecoded(String fileName) {
        fileName = dellitingSlashes(fileName);
        Pattern pat = Pattern.compile("\\.enc");
        Matcher matcher = pat.matcher(fileName);
        return !matcher.find();
    }
    abstract void encryptFile();
}
