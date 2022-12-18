package Models.SubModels;

import java.io.FileInputStream;
import java.io.IOException;

public class EncryptionTest {
    public static void main(String[] args) throws IOException {
        String path = "src/car.png";
        FileInputStream fileInputStream = new FileInputStream(path);
        int key = 5;
//        EncryptAndDecrypt.encrypt(fileInputStream, key);
        EncryptAndDecrypt.decryptImage(fileInputStream, key);
    }
}
