package tests;

import forarchive.archivers.RarArchivator;
import forarchive.archivers.ZipArchivator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ZipArchivatorTest {

    @Test
    void archive() {
        ZipArchivator zip = new ZipArchivator("D:\\testing\\output.zip");
        zip.add("D:\\testing\\output.json");
        zip.add("D:\\testing\\output.xml");
        zip.archive();
    }
}