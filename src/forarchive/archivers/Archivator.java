package forarchive.archivers;

import java.util.ArrayList;

public abstract class Archivator {

    public ArrayList<String> nameOfFiles;
    public String nameOfRar;
    public void add(String name) {
        nameOfFiles.add(name);
    }
    public void setFileNames(ArrayList<String> names) {
        nameOfFiles = names;
    }
    public void remove(String name) {
        nameOfFiles.remove(name);
    }
    abstract void archive();
}
