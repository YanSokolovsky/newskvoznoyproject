package forarchive.archivers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipArchivator extends Archivator{
    ZipArchivator(String rarName) {
        nameOfRar = rarName;
    }
    ZipArchivator(String rarName, ArrayList<String> names) {
        nameOfRar = rarName;
        nameOfFiles = names;
    }
    @Override
    void archive() {
        try(ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(nameOfRar)))
        {
            for (String nameOfFile : nameOfFiles) {
                FileInputStream fileInputStream = new FileInputStream(nameOfFile);
                ZipEntry entry = new ZipEntry(nameOfFile);
                zipOutputStream.putNextEntry(entry);
                byte[] buffer = new byte[fileInputStream.available()];
                int numberOfBytes = fileInputStream.read(buffer);
                zipOutputStream.write(buffer);
                zipOutputStream.closeEntry();
                fileInputStream.close();
            }
        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }
    }
}
