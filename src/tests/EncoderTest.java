package tests;

import forcrypto.decryptors.Decryptor;
import forcrypto.encryptors.Encoder;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class EncoderTest {

    @Test
    void generateNewName() {
        Encoder tr = new Encoder("filename.txt", "wordforencripting");
        String address1 = "C:\\someData1\\someData2\\someData3\\data.file";
        String address2 = "C:\\Data1\\Data2\\someData3\\data.file";
        String address3 = "C:\\someData1\\someData2\\someBetween\\someData3\\data.file\\";
        String address4 = "C:\\someData1\\someData2\\data.file\\\\\\";
        String address5 = "C:\\someData1\\someData2\\someData3\\someData\\file.data\\\\\\\\";
        String res1 = tr.generateNewName(address1);
        String res2 = tr.generateNewName(address2);
        String res3 = tr.generateNewName(address3);
        String res4 = tr.generateNewName(address4);
        String res5 = tr.generateNewName(address5);
        assertEquals("C:\\someData1\\someData2\\someData3\\datafile.enc", res1);
        assertEquals("C:\\Data1\\Data2\\someData3\\datafile.enc", res2);
        assertEquals("C:\\someData1\\someData2\\someBetween\\someData3\\datafile.enc", res3);
        assertEquals("C:\\someData1\\someData2\\datafile.enc", res4);
        assertEquals("C:\\someData1\\someData2\\someData3\\someData\\filedata.enc", res5);
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
    void encryptFileTest() {
        File file = new File("D:\\testing","encoded.txt");
        assertFalse(file.exists());
        try {
            BufferedWriter wr = new BufferedWriter(new FileWriter("D:\\testing\\encoded.txt"));
            wr.write("data to enc");
            wr.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Encoder tr = new Encoder("D:\\testing\\encoded.txt", "wordforencriptin");
        tr.encryptFile();
        File nf = new File("D:\\testing\\encodedtxt.enc");
        assertTrue(nf.exists());
        String res;
        Decryptor dec = new Decryptor("D:\\testing\\encodedtxt.enc","wordforencriptin");
        dec.decryptFile();
        try {
            BufferedReader wr = new BufferedReader(new FileReader("D:\\testing\\encoded.txt"));
            res = wr.readLine();
            wr.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertEquals(res, "data to enc");
        file.delete();
    }
}