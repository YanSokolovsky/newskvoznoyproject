package forarchive.archivers;

import java.io.IOException;
import java.util.ArrayList;

public class RarArchivator extends Archivator {
    RarArchivator(String name) {
        nameOfRar = name;
    }
    RarArchivator(String name, ArrayList<String> names) {
        nameOfRar = name;
        nameOfFiles = names;
    }
    @Override
    void archive() {
        try {
            for (String sourceFile : nameOfFiles) {
                String rarFile = nameOfRar;
                String winRarPath = "D:\\WinRAR.exe";
                String command = String.format("\"%s\" a -ep1 \"%s\" \"%s\"", winRarPath, rarFile, sourceFile);
                Process process = Runtime.getRuntime().exec(command);
                process.waitFor();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
