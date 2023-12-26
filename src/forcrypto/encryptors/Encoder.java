package forcrypto.encryptors;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Encoder extends Shivrator{
    String inputName;
    public Encoder(String coderName, String coderKey) {
        inputName = coderName;
        Key = coderKey;
    }

    @Override
    public void encryptFile() {
        try {
            if (isDecoded(inputName)) {
                byte[] decodedData;
                byte[] encryptedData;
                File decodedfile = new File(inputName);
                FileInputStream fileInputStream = new FileInputStream(decodedfile);
                decodedData = fileInputStream.readAllBytes();
                SecretKeySpec secretKeySpec = new SecretKeySpec(Key.getBytes(), "AES");
                Cipher cipher = Cipher.getInstance("AES");
                cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
                encryptedData = cipher.doFinal(decodedData);
                String encryptedFile = generateNewName(inputName);
                File encodedfile = new File(encryptedFile);
                fileInputStream.close();
                boolean result = decodedfile.renameTo(encodedfile);
                FileOutputStream fileOutputStream = new FileOutputStream(encryptedFile);
                fileOutputStream.write(encryptedData);
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
