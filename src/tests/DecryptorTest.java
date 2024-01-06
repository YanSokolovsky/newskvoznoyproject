package tests;

import forcrypto.decryptors.Decryptor;
import forcrypto.encryptors.Encoder;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DecryptorTest {

    @Test
    void generateNewName() {
        Decryptor dec = new Decryptor("filename.txt", "wordforencriptin");
        String path1 = "C:\\someData1\\someData2\\someData3\\someData\\filetxt.enc\\\\\\\\";
        String path2 = "C:\\someData1\\someData2\\someData3\\someData\\filexml.enc\\\\\\\\";
        String path3 = "C:\\someData1\\someData2\\someData3\\someData\\filezip.enc\\\\\\\\";
        String path4 = "C:\\someData1\\someData2\\someData3\\someData\\filerar.enc\\\\\\\\";
        String path5 = "C:\\someData1\\someData2\\someData3\\someData\\filejson.enc\\\\\\\\";
        String path6 = "C:\\someData1\\someData2\\someData3\\someData\\filexmlencenc.enc\\\\\\\\";
        String name1 = dec.generateNewName(path1);
        String name2 = dec.generateNewName(path2);
        String name3 = dec.generateNewName(path3);
        String name4 = dec.generateNewName(path4);
        String name5 = dec.generateNewName(path5);
        String name6 = dec.generateNewName(path6);
        assertEquals("C:\\someData1\\someData2\\someData3\\someData\\file.txt", name1);
        assertEquals("C:\\someData1\\someData2\\someData3\\someData\\file.xml", name2);
        assertEquals("C:\\someData1\\someData2\\someData3\\someData\\file.zip", name3);
        assertEquals("C:\\someData1\\someData2\\someData3\\someData\\file.rar", name4);
        assertEquals("C:\\someData1\\someData2\\someData3\\someData\\file.json", name5);
        assertEquals("C:\\someData1\\someData2\\someData3\\someData\\filexmlenc.enc", name6);
    }

    @Test
    void dellitingSlashes() {
        Encoder tr = new Encoder("filename.txt", "wordforencripting");
        String address1 = "C:\\someData1\\someData2\\someData3\\";
        String address2 = "C:\\Data1\\Data2\\someData3\\";
        String address3 = "C:\\someData1\\someData2\\someBetween\\someData3";
        String address4 = "C:\\someData1\\someData2\\\\";
        String address5 = "C:\\someData1\\someData2\\someData3\\\\\\";
        String res1 = tr.dellitingSlashes(address1);
        String res2 = tr.dellitingSlashes(address2);
        String res3 = tr.dellitingSlashes(address3);
        String res4 = tr.dellitingSlashes(address4);
        String res5 = tr.dellitingSlashes(address5);
        assertEquals("C:\\someData1\\someData2\\someData3", res1);
        assertEquals("C:\\Data1\\Data2\\someData3", res2);
        assertEquals("C:\\someData1\\someData2\\someBetween\\someData3", res3);
        assertEquals("C:\\someData1\\someData2", res4);
        assertEquals("C:\\someData1\\someData2\\someData3", res5);
    }

    @Test
    void getAddressOfFile() {
        Encoder tr = new Encoder("filename.txt", "wordforencripting");
        String address1 = "C:\\someData1\\someData2\\someData3\\data.file";
        String address2 = "C:\\Data1\\Data2\\someData3\\data.file";
        String address3 = "C:\\someData1\\someData2\\someBetween\\someData3\\data.file\\";
        String address4 = "C:\\someData1\\someData2\\data.file\\\\\\";
        String address5 = "C:\\someData1\\someData2\\someData3\\someData\\file.data\\\\\\\\";
        String res1 = tr.getAddressOfFile(address1);
        String res2 = tr.getAddressOfFile(address2);
        String res3 = tr.getAddressOfFile(address3);
        String res4 = tr.getAddressOfFile(address4);
        String res5 = tr.getAddressOfFile(address5);
        assertEquals("C:\\someData1\\someData2\\someData3", res1);
        assertEquals("C:\\Data1\\Data2\\someData3", res2);
        assertEquals("C:\\someData1\\someData2\\someBetween\\someData3", res3);
        assertEquals("C:\\someData1\\someData2", res4);
        assertEquals("C:\\someData1\\someData2\\someData3\\someData", res5);
    }

    @Test
    void getShortName() {
        Encoder tr = new Encoder("filename.txt", "wordforencripting");
        String address1 = "C:\\someData1\\someData2\\someData3\\data.file";
        String address2 = "C:\\Data1\\Data2\\someData3\\data.file";
        String address3 = "C:\\someData1\\someData2\\someBetween\\someData3\\data.file\\";
        String address4 = "C:\\someData1\\someData2\\data.file\\\\\\";
        String address5 = "C:\\someData1\\someData2\\someData3\\someData\\file.data\\\\\\\\";
        String res1 = tr.getShortName(address1);
        String res2 = tr.getShortName(address2);
        String res3 = tr.getShortName(address3);
        String res4 = tr.getShortName(address4);
        String res5 = tr.getShortName(address5);
        assertEquals("data.file", res1);
        assertEquals("data.file", res2);
        assertEquals("data.file", res3);
        assertEquals("data.file", res4);
        assertEquals("file.data", res5);
    }
    @Test
    void isDecodedTest() {
        Encoder tr = new Encoder("filename.txt", "wordforencripting");
        String path1 = "C:\\somFile\\SomeFile2\\data.enc";
        String path2 = "C:\\somFile\\SomeFile2\\data.enc\\";
        String path3 = "C:\\somFile\\SomeFile2\\data.txt";
        String path4 = "C:\\somFile\\SomeFile2\\data.json\\";
        assertFalse(tr.isDecoded(path1));
        assertFalse(tr.isDecoded(path2));
        assertTrue(tr.isDecoded(path3));
        assertTrue(tr.isDecoded(path4));
    }

    @Test
    void decryptFile() {
        File file = new File("D:\\testing","encodedtxt.enc");
        assertFalse(file.exists());
        try {
            BufferedWriter wr = new BufferedWriter(new FileWriter("D:\\testing\\encodedtxt.enc"));
            wr.write("§ъs`$ИBа\u0019e\ts!афк");
            wr.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Decryptor tr = new Decryptor("D:\\testing\\encodedtxt.enc", "wordforencriptin");
        tr.decryptFile();
        File nf = new File("D:\\testing\\encoded.txt");
        assertTrue(nf.exists());
        String res;
        try {
            BufferedReader wr = new BufferedReader(new FileReader("D:\\testing\\encoded.txt"));
            res = wr.readLine();
            wr.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertEquals("data to encrypt", res); //§ъs`$ИBаe	s!афк
        nf.delete();
    }
}