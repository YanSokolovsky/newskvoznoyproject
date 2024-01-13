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
import java.io.IOException;
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
    // This method encodes a file using the Encoder class
    void encode(String inputFileName) {
        dir();
    }
    // This method reads a file and returns an ArrayList of Strings
    // The file format can be xml, json, or txt
    ArrayList<String> read(String inputFileName) {
        // Define the regex patterns for each file format
        String xmlFormat = "\\.xml$";
        String jsonFormat = "\\.json$";
        String txtFormat = "\\.txt$";
        // Compile the pattern for xml format
        Pattern pattern = Pattern.compile(xmlFormat);
        // Match the pattern with the input file name
        Matcher matcher = pattern.matcher(inputFileName);
        // If the file name ends with .xml, use the XMLReader class to read the file
        if (matcher.find()) {
            XMLReader xmlReader = new XMLReader(currentPath + "\\" + inputFileName);
            return xmlReader.read();
        }
        // Compile the pattern for json format
        pattern = Pattern.compile(jsonFormat);
        // Match the pattern with the input file name
        matcher = pattern.matcher(inputFileName);
        // If the file name ends with .json, use the JSONReader class to read the file
        if (matcher.find()) {
            JSONReader jsonReader = new JSONReader(currentPath + "\\" + inputFileName);
            return jsonReader.read();
        }
        // Compile the pattern for txt format
        pattern = Pattern.compile(txtFormat);
        // Match the pattern with the input file name
        matcher = pattern.matcher(inputFileName);
        // If the file name ends with .txt, use the TXTReader class to read the file
        if (matcher.find()) {
            TXTReader txtReader = new TXTReader(currentPath + "\\" + inputFileName);
            return txtReader.read();
        }
        // If the file format is not supported, return an empty ArrayList
        return new ArrayList<>();
    }
    // This method archives a file using the RarArchivator class
    // The file format can be rar or zip
    void archive(String outputFileName) {
        // Define the regex patterns for each file format
        String rarFormat = "\\.rar$";
        String zipFormat = "\\.zip$";
        // Compile the pattern for rar format
        Pattern pattern = Pattern.compile(rarFormat);
        // Match the pattern with the output file name
        Matcher matcher = pattern.matcher(outputFileName);
        // If the file name ends with .rar, use the RarArchivator class to archive the file
        if (matcher.find()) {
            RarArchivator rarArchivator = new RarArchivator(outputFileName, filesToArchive);
            rarArchivator.archive();
        }
        // Compile the pattern for zip format
        pattern = Pattern.compile(zipFormat);
        // Match the pattern with the output file name
        matcher = pattern.matcher(outputFileName);
        // If the file name ends with .zip, use the RarArchivator class to archive the file
        if (matcher.find()) {
            RarArchivator rarArchivator = new RarArchivator(outputFileName, filesToArchive);
            rarArchivator.archive();
        }
    }
    // This method dearchives a file using the RarDearchivator class
    // The file format can be rar or zip
    void dearchive(String inputFileName) {
        // Define the regex patterns for each file format
        String rarFormat = "\\.rar$";
        String zipFormat = "\\.zip$";
        // Compile the pattern for rar format
        Pattern pattern = Pattern.compile(rarFormat);
        // Match the pattern with the input file name
        Matcher matcher = pattern.matcher(inputFileName);
        // If the file name ends with .rar, use the RarDearchivator class to dearchive the file
        if (matcher.find()) {
            RarDearchivator rarDearchivator = new RarDearchivator(currentPath + "\\" + inputFileName);
            rarDearchivator.dearchive();
        }
        // Compile the pattern for zip format
        pattern = Pattern.compile(zipFormat);
        // Match the pattern with the input file name
        matcher = pattern.matcher(inputFileName);
        // If the file name ends with .zip, use the RarDearchivator class to dearchive the file
        if (matcher.find()) {
            RarDearchivator rarDearchivator = new RarDearchivator(currentPath + "\\" + inputFileName);
            rarDearchivator.dearchive();
        }
    }
    // This method dearchives a file using the RarDearchivator class
    // The file format can be rar or zip
    // The destination path is specified as a parameter
    void dearchive(String inputFileName, String destinationPath) {
        // Define the regex patterns for each file format
        String rarFormat = "\\.rar$";
        String zipFormat = "\\.zip$";
        // Compile the pattern for rar format
        Pattern pattern = Pattern.compile(rarFormat);
        // Match the pattern with the input file name
        Matcher matcher = pattern.matcher(inputFileName);
        // If the file name ends with .rar, use the RarDearchivator class to dearchive the file to the destination path
        if (matcher.find()) {
            RarDearchivator rarDearchivator = new RarDearchivator(currentPath + "\\" + inputFileName);
            rarDearchivator.dearchive(destinationPath);
        }
        // Compile the pattern for zip format
        pattern = Pattern.compile(zipFormat);
        // Match the pattern with the input file name
        matcher = pattern.matcher(inputFileName);
        // If the file name ends with .zip, use the RarDearchivator class to dearchive the file to the destination path
        if (matcher.find()) {
            RarDearchivator rarDearchivator = new RarDearchivator(currentPath + "\\" + inputFileName);
            rarDearchivator.dearchive(destinationPath);
        }
    }
    // This method writes the expressions to the output file based on its format
    void write(String outputFileName) {
        // Define the regex patterns for different file formats
        String xmlFormat = "\\.xml$";
        String jsonFormat = "\\.json$";
        String txtFormat = "\\.txt$";
        // Create a pattern object for xml format
        Pattern pattern = Pattern.compile(xmlFormat);
        // Create a matcher object for the output file name
        Matcher matcher = pattern.matcher(outputFileName);
        // Check if the output file name matches the xml format
        if (matcher.find()) {
            // Create an XMLWriterNonAPI object with the output file path
            XMLWriterNonAPI xmlWriter = new XMLWriterNonAPI(currentPath + "\\" + outputFileName);
            // Write the expressions to the output file in xml format
            xmlWriter.write(expressions);
        }
        // Repeat the same process for json format
        pattern = Pattern.compile(jsonFormat);
        matcher = pattern.matcher(outputFileName);
        if (matcher.find()) {
            // Create a JSONWriter object with the output file path
            JSONWriter jsonWriter = new JSONWriter(currentPath + "\\" + outputFileName);
            // Write the expressions to the output file in json format
            jsonWriter.write(expressions);
        }
        // Repeat the same process for txt format
        pattern = Pattern.compile(txtFormat);
        matcher = pattern.matcher(outputFileName);
        if (matcher.find()) {
            // Create a TXTWriter object with the output file path
            TXTWriter txtWriter = new TXTWriter(currentPath + "\\" + outputFileName);
            // Write the expressions to the output file in txt format
            txtWriter.write(expressions);
        }
    }
    // This method removes any trailing slashes from the input file name
    public String removeTrailingSlashes(String inputFileName) {
        // Loop until the input file name does not end with a slash
        while (inputFileName.endsWith("\\")) {
            // Remove the last character from the input file name
            inputFileName = inputFileName.substring(0, inputFileName.length() - 1);
        }
        // Return the input file name without trailing slashes
        return inputFileName;
    }
    // This method returns the directory of the input file
    public String getFileDirectory(String inputFileName) {
        // Define the regex pattern for the file name
        String regex = "[^\\\\]*$";
        // Remove any trailing slashes from the input file name
        inputFileName = removeTrailingSlashes(inputFileName);
        // Create a pattern object for the file name
        Pattern pattern = Pattern.compile(regex);
        // Create a matcher object for the input file name
        Matcher matcher = pattern.matcher(inputFileName);
        // Declare a string variable for the directory
        String directory;
        // Check if the matcher finds the file name in the input file name
        if (matcher.find()) {
            // Get the substring before the file name as the directory
            directory = inputFileName.substring(0, matcher.start());
        } else {
            // If not, the input file name is the directory
            directory = inputFileName;
        }
        // Remove any trailing slashes from the directory
        directory = removeTrailingSlashes(directory);
        // Return the directory
        return directory;
    }
    // This method checks if the input file exists in the current directory
    boolean isExist(String inputFileName) {
        // Get the list of files in the current directory
        ArrayList<String> files = listFiles();
        // Return true if the input file name is in the list, false otherwise
        return files.contains(inputFileName);
    }

    String currentPath = "C:";
    ArrayList<String> expressions = new ArrayList<>();
    ArrayList<String> filesToArchive = new ArrayList<>();
    ArrayList<String> listFiles() {
        File currentFolder = new File(currentPath);
        File[] folderFiles = currentFolder.listFiles();
        ArrayList<String> fileNames = new ArrayList<>();
        if (folderFiles == null) {
            return new ArrayList<>();
        } else {
            for (File file : folderFiles) {
                fileNames.add(file.getName());
            }
        }
        return fileNames;
    }
    void cd(String directoryName) {
        if (isExist(directoryName)) {
            currentPath = currentPath + "\\" + directoryName;
        } else {
            if (Objects.equals(directoryName, "..")) {
                // Rename getAddressOfFile to getFileDirectory
                currentPath = getFileDirectory(currentPath);
            }
        }
    }
    void dir() {
        // Rename arr to files
        ArrayList<String> files = listFiles();
        int i = 0;
        for (String s : files) {
            System.out.println(s);
        }
    }
    public void doing() {
        Scanner in = new Scanner(System.in);
        while(true) {
            System.out.println(currentPath);
            System.out.print(">");
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
                    String filePath = currentPath + "\\" + data[1];
                    Encoder encoder = new Encoder(filePath, "kjshfkjhskdjfh kjsdhfk jhsdkjfh ikerh");
                    encoder.encryptFile();
                }
            }
            if (Objects.equals(data[0], "decode")) {
                if (data.length > 1 && isExist(data[1])) {
                    String filePath = currentPath + "\\" + data[1];
                    Decryptor decryptor = new Decryptor(filePath, "kjshfkjhskdjfh kjsdhfk jhsdkjfh ikerh");
                    decryptor.decryptFile();
                }
            }
            if (Objects.equals(data[0], "read")) {
                if (data.length > 1 && isExist(data[1])) {
                    String filePath = currentPath + "\\" + data[1];
                    expressions = read(data[1]);
                }
            }
            if (Objects.equals(data[0], "calculate")) {
                if (expressions.size() > 1) {
                    CalcWithLib calculator = new CalcWithLib();
                    for (int i = 0; i < expressions.size(); i++) {
                        expressions.set(i, Double.toString(calculator.Calculate(expressions.get(i))));
                    }
                }
            }
            if (Objects.equals(data[0], "write")) {
                if (data.length > 1 && isExist(data[1])) {
                    String filePath = currentPath + "\\" + data[1];
                    write(data[1]);
                }
            }
            if (Objects.equals(data[0], "clearbuff")) {
                expressions = new ArrayList<>();
            }
            if (Objects.equals(data[0], "cleararch")) {
                filesToArchive = new ArrayList<>();
            }
            if (Objects.equals(data[0], "addfile")) {
                for (int i = 1; i < data.length; i++) {
                    filesToArchive.add(currentPath + "\\" + data[i]);
                }
            }
            if (Objects.equals(data[0], "archive") && data.length == 2) {
                try {
                    archive(currentPath + "\\" + data[1]);
                } catch(Exception e) {
                    System.out.println("Oooops!!! Something goes wrong. Try to check version of rar file. It should be 4.");
                }
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
            if (Objects.equals(data[0], "help")) {
                System.out.println("clear чистит консоль " +
                        "\ndir выводит | имена файлов, которые содержатся в папке, в который вы находитесь " +
                        "\ncd filename | переносит вас в папку с именет filename, если такой нет, то ничего не делает" +
                        "\nencode filename | шифрует файл filename" +
                        "\ndecode filename | дешифрует файл" +
                        "\nread filename | читает файл с именем filename. Если вы попытаетесь прочитать файли формата отличного от xml, json, txt, то команда ничего не сделает" +
                        "\ncalculate | считает все выражения прочитанные из фалов ранее пока вы не очистили буффер" +
                        "\nwrite filename | запысывает в файл все что было в буффере на момент использования команды." +
                        "\nЕсли в буффере были не посчитанные выражения, то команда запишет их в файл" +
                        "\nclearbuff | чистит буффер выражений прочитанных ранее" +
                        "\ncleararch | чистит буффер имен файлов, которые вы добавляли для архивации" +
                        "\naddfile filename | добавляет имя файла в буффер имен файлов для архивации" +
                        "\narchive nameofarchive | архивирует все файлы из буфера имен фалов для архивации в архив с именем nameofarchive" +
                        "\ndearchive nameofarchive | распоковывает архив в папку вашего местоположения " +
                        "\ndearchive nameofarchive nameofdestinationfile |  распоковывает архив в папку с именем nameofdestinationfile\n");
            }
            System.out.println(" ");
        }
    }
}
