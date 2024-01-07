package tests;

import forarchive.dearchivers.RarDearchivator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RarDearchivatorTest {

    @Test
    void getNames() {
    }

    @Test
    void dearchive() {
        RarDearchivator rar = new RarDearchivator("D:\\testing\\output.rar");
        rar.dearchive();
    }

    @Test
    void testDearchive() {
        RarDearchivator rar = new RarDearchivator("D:\\testing\\output.rar");
        rar.dearchive("D:\\testing\\Rartest");
    }
}