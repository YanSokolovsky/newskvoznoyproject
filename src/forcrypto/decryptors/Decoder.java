package forcrypto.decryptors;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Decoder {
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
        if (!isDecoded(filepath)) {
            String path = getAddressOfFile(filepath);
            String name = getShortName(filepath);
            name = name.replace(".enc", "");
            String datatupe1 = "json$";
            String datatupe2 = "txt$";
            String datatupe3 = "xml$";
            String datatupe4 = "rar$";
            String datatupe5 = "zip$";
            String datatupe6 = "enc$";
            Pattern pat = Pattern.compile(datatupe1);
            Matcher matcher = pat.matcher(name);
            if (matcher.find()) {
                name = name.substring(0, name.length() - 4);
                return path + "\\" + name + ".json";
            }
            pat = Pattern.compile(datatupe2);
            matcher = pat.matcher(name);
            if (matcher.find()) {
                name = name.substring(0, name.length() - 3);
                return path + "\\" + name + ".txt";
            }
            pat = Pattern.compile(datatupe3);
            matcher = pat.matcher(name);
            if (matcher.find()) {
                name = name.substring(0, name.length() - 3);
                return path + "\\" + name + ".xml";
            }
            pat = Pattern.compile(datatupe4);
            matcher = pat.matcher(name);
            if (matcher.find()) {
                name = name.substring(0, name.length() - 3);
                return path + "\\" + name + ".rar";
            }
            pat = Pattern.compile(datatupe5);
            matcher = pat.matcher(name);
            if (matcher.find()) {
                name = name.substring(0, name.length() - 3);
                return path + "\\" + name + ".zip";
            }
            pat = Pattern.compile(datatupe6);
            matcher = pat.matcher(name);
            if (matcher.find()) {
                name = name.substring(0, name.length() - 3);
                return path + "\\" + name + ".enc";
            }
        } else {
            return filepath;
        }
        return filepath;
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
    abstract void decryptFile();
}
