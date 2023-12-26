package forarchive.dearchivers;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Dearchivator {
    public String getStandardName() {
        return standardName;
    }

    public void setStandardName(String standardised) {
        standardName = standardised;
    }

    String standardName;

    String archFile;
    public String getOutputFile() {
        return archFile;
    }

    public void setOutputFile(String outputFile) {
        this.archFile = outputFile;
    }
    String dellitingSlashes(String fileName) {
        String expression = "\\+$";
        fileName = fileName.replaceAll(expression, "");
        return fileName;
    }
    String getAddressOfFile(String fileName) {
        String expression = "[^\\\\]*$";
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
    String getShortName(String fileName) {
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
    abstract ArrayList<String> dearchive();
    abstract ArrayList<String> dearchive(String fileDestination);
}
