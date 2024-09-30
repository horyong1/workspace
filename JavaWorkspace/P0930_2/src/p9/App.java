package p9;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class App {
    // 암호화
    // 복호 가능한 암호화 : RSA, DES , AES
    // 복호 불가능한 암호화 : SHA-256 해시 
    public static void main(String[] args)throws Exception {
        //AES
    //    // 키 생성
    //     KeyGenerator keyGen = KeyGenerator.getInstance("AES");
    //     keyGen.init(128); // AES-128
    //     SecretKey secretKey = keyGen.generateKey();
        
    //     // 평문
    //     String plainText = "Hello, AES!";
        
    //     // 암호화   
    //     Cipher cipher = Cipher.getInstance("AES");
    //     cipher.init(Cipher.ENCRYPT_MODE, secretKey);
    //     byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
    //     String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);
    //     System.out.println("Encrypted Text: " + encryptedText);
        
    //     // 복호화
    //     cipher.init(Cipher.DECRYPT_MODE, secretKey);
    //     byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
    //     String decryptedText = new String(decryptedBytes);
    //     System.out.println("Decrypted Text: " + decryptedText);

        // RSA
        // 암호화 복호화 용 키를 2개를 만들어서 사용함
        // // 키 페어 생성
        // KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        // keyGen.initialize(2048); // RSA-2048
        // KeyPair keyPair = keyGen.generateKeyPair();
        // PublicKey publicKey = keyPair.getPublic();
        // PrivateKey privateKey = keyPair.getPrivate();
        
        // // 평문
        // String plainText = "Hello, RSA!";
        // // 암호화 하는 키 
        // // 암호화
        // Cipher encryptCipher = Cipher.getInstance("RSA");
        // encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        // byte[] encryptedBytes = encryptCipher.doFinal(plainText.getBytes());
        // String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);
        // System.out.println("Encrypted Text: " + encryptedText);
        // // 복호화 하는 키
        // // 복호화
        // Cipher decryptCipher = Cipher.getInstance("RSA");
        // decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
        // byte[] decryptedBytes = decryptCipher.doFinal(Base64.getDecoder().decode(encryptedText));
        // String decryptedText = new String(decryptedBytes);
        // System.out.println("Decrypted Text: " + decryptedText);

        // SHA-256
        // 평문
        String plainText = "1111";
        
        // SHA-256 해시 생성
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = md.digest(plainText.getBytes());
        String hash = Base64.getEncoder().encodeToString(hashBytes);
        System.out.println("Hashed Text (SHA-256): " + hash);

        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            String hex = Integer.toHexString(0xff & b); // 각 바이트를 16진수로 변환
            if (hex.length() == 1) hexString.append('0'); // 한 자리일 경우 앞에 0을 추가
            hexString.append(hex);
        }
        System.out.println("hexString : " + hexString.toString());
    }

}
