package forcrypto.decryptors;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class Decryptor extends Decoder{
    String File;

    public Decryptor(String inputName, String key) {
        File = inputName;
        this.Key = key;
    }
    @Override
    public void decryptFile() {
        try {
            if (!isDecoded(File)) {
                File oldfile = new File(File);
                FileInputStream ins = new FileInputStream(File);
                byte[] encryptedBytes;
                byte[] decryptedBytes;
                encryptedBytes = ins.readAllBytes();
                SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(Key.getBytes(), 16), "AES");
                Cipher cipher = Cipher.getInstance("AES");
                cipher.init(Cipher.DECRYPT_MODE, keySpec);
                decryptedBytes = cipher.doFinal(encryptedBytes);
                File newfile = new File(generateNewName(File));
                ins.close();
                oldfile.renameTo(newfile);
                FileOutputStream outs = new FileOutputStream(generateNewName(File));
                outs.write(decryptedBytes);
                outs.close();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
}
