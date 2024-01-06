package forarchive.archivers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipArchivator extends Archivator{
    public ZipArchivator(String rarName) {
        nameOfRar = rarName;
        nameOfFiles = new ArrayList<>();
    }
    ZipArchivator(String rarName, ArrayList<String> names) {
        nameOfRar = rarName;
        nameOfFiles = names;
    }
    public String dellitingSlashes(String fileName) {
        while (fileName.endsWith("\\")) {
            fileName = fileName.substring(0, fileName.length() - 1);
        }
        return fileName;
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
    @Override
    public void archive() {
        File f = new File(nameOfRar);
        try
        {
            ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(nameOfRar));

            for (String nameOfFile : nameOfFiles) {
                FileInputStream fileInputStream = new FileInputStream(nameOfFile);
                ZipEntry entry = new ZipEntry(getShortName(nameOfFile));
                zipOutputStream.putNextEntry(entry);
                byte[] buffer = new byte[8192];
                int len;
                while ((len = fileInputStream.read(buffer)) > 0) {
                    zipOutputStream.write(buffer, 0, len);
                }
                fileInputStream.close();
            }
            zipOutputStream.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
