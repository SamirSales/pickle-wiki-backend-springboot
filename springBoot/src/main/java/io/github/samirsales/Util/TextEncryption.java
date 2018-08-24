package io.github.samirsales.Util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TextEncryption {

//    public String encodeToBase64(String text){
//        byte[] encodedBytes = Base64.encodeBase64(text.getBytes());
//        return new String(encodedBytes);
//    }

    public String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

//    public static void main(String[] args){
//        TextEncryption te = new TextEncryption();
//
////        System.out.println("---------------------- Base 64");
////        System.out.println(te.encodeToBase64("samir"));
////        System.out.println(te.encodeToBase64("sámir"));
////        System.out.println(te.encodeToBase64("sâmir"));
////        System.out.println(te.encodeToBase64("sãmir"));
////        System.out.println(te.encodeToBase64("samir sales"));
////        System.out.println(te.encodeToBase64("samirsales"));
////
////        System.out.println("---------------------- MD5");
////        System.out.println(te.getMD5("samir"));
////        System.out.println(te.getMD5("sámir"));
////        System.out.println(te.getMD5("sâmir"));
////        System.out.println(te.getMD5("sãmir"));
////        System.out.println(te.getMD5("samir sales"));
////        System.out.println(te.getMD5("samirsales"));
//
//        System.out.println(te.getMD5("admin")); //21232f297a57a5a743894a0e4a801fc3
//    }
}
