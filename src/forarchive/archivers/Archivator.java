package forarchive.archivers;

import java.util.ArrayList;

public abstract class Archivator {

    ArrayList<String> nameOfFiles;
    String nameOfRar;
    void add(String name) {
        nameOfFiles.add(name);
    }
    void setFileNames(ArrayList<String> names) {
        nameOfFiles = names;
    }
    void remove(String name) {
        nameOfFiles.remove(name);
    }
    abstract void archive();
}
