package tests;

import forarchive.dearchivers.RarDearchivator;
import forarchive.dearchivers.ZipDearchivator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ZipDearchivatorTest {

    @Test
    void dearchive() {
        ZipDearchivator zip = new ZipDearchivator("D:\\testing\\output.zip");
        zip.dearchive();
    }

    @Test
    void testDearchive() {
    }
}