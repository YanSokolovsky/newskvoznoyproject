package tests;

import forarchive.archivers.RarArchivator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RarArchivatorTest {

    @Test
    void archive() {
        RarArchivator rar = new RarArchivator("D:\\testing\\output.rar");
        rar.add("D:\\testing\\output.json");
        rar.add("D:\\testing\\output.xml");
        rar.archive();
    }
}