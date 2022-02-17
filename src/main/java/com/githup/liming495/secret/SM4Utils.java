package com.githup.liming495.secret;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * description
 *
 * @author Guppy
 * @version version
 * @since 2022/2/15 11:06
 */
public class SM4Utils {
    public String secretKey = "";
    private String iv = "";
    public boolean hexString = false;

    public SM4Utils() {
    }

    public String encryptDataECB(String plainText) {
        try {
            SM4Context ctx = new SM4Context();
            ctx.isPadding = true;
            ctx.mode = SM4.SM4_ENCRYPT;

            byte[] keyBytes;
            if (hexString) {
                keyBytes = Util.hexStringToBytes(secretKey);
            } else {
                keyBytes = secretKey.getBytes();
            }

            SM4 sm4 = new SM4();
            sm4.sm4SetKeyEnc(ctx, keyBytes);
            byte[] encrypted = sm4.sm4CryptEcb(ctx, plainText.getBytes("GBK"));
            String cipherText = new BASE64Encoder().encode(encrypted);
            if (cipherText != null && cipherText.trim().length() > 0) {
                Pattern p = Pattern.compile("\\s*|\t|\r|\n");
                Matcher m = p.matcher(cipherText);
                cipherText = m.replaceAll("");
            }
            return cipherText;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String decryptDataECB(String cipherText) {
        try {
            SM4Context ctx = new SM4Context();
            ctx.isPadding = true;
            ctx.mode = SM4.SM4_DECRYPT;

            byte[] keyBytes;
            if (hexString) {
                keyBytes = Util.hexStringToBytes(secretKey);
            } else {
                keyBytes = secretKey.getBytes();
            }

            SM4 sm4 = new SM4();
            sm4.sm4SetKeyDec(ctx, keyBytes);
            byte[] decrypted = sm4.sm4CryptEcb(ctx, new BASE64Decoder().decodeBuffer(cipherText));
            return new String(decrypted, "GBK");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String encryptDataCBC(String plainText) {
        try {
            SM4Context ctx = new SM4Context();
            ctx.isPadding = true;
            ctx.mode = SM4.SM4_ENCRYPT;

            byte[] keyBytes;
            byte[] ivBytes;
            if (hexString) {
                keyBytes = Util.hexStringToBytes(secretKey);
                ivBytes = Util.hexStringToBytes(iv);
            } else {
                keyBytes = secretKey.getBytes();
                ivBytes = iv.getBytes();
            }

            SM4 sm4 = new SM4();
            sm4.sm4SetKeyEnc(ctx, keyBytes);
            byte[] encrypted = sm4.sm4CryptCbc(ctx, ivBytes, plainText.getBytes("GBK"));
            String cipherText = new BASE64Encoder().encode(encrypted);
            if (cipherText != null && cipherText.trim().length() > 0) {
                Pattern p = Pattern.compile("\\s*|\t|\r|\n");
                Matcher m = p.matcher(cipherText);
                cipherText = m.replaceAll("");
            }
            return cipherText;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String decryptDataCBC(String cipherText) {
        try {
            SM4Context ctx = new SM4Context();
            ctx.isPadding = true;
            ctx.mode = SM4.SM4_DECRYPT;

            byte[] keyBytes;
            byte[] ivBytes;
            if (hexString) {
                keyBytes = Util.hexStringToBytes(secretKey);
                ivBytes = Util.hexStringToBytes(iv);
            } else {
                keyBytes = secretKey.getBytes();
                ivBytes = iv.getBytes();
            }

            SM4 sm4 = new SM4();
            sm4.sm4SetKeyDec(ctx, keyBytes);
            byte[] decrypted = sm4.sm4CryptCbc(ctx, ivBytes, new BASE64Decoder().decodeBuffer(cipherText));
            return new String(decrypted, "GBK");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) throws IOException {
        String plainText = "ererfeiisgod";

        SM4Utils sm4 = new SM4Utils();
        sm4.secretKey = "JeF8U9wHFOMfs2Y8";
        sm4.hexString = false;

        System.out.println("ECB模式加密");
        String cipherText = sm4.encryptDataECB(plainText);
        System.out.println("密文: " + cipherText);
        System.out.println("");

        plainText = sm4.decryptDataECB(cipherText);
        System.out.println("明文: " + plainText);
        System.out.println("");

        System.out.println("CBC模式加密");
        sm4.iv = "UISwD9fW6cFh9SNS";
        cipherText = sm4.encryptDataCBC(plainText);
        System.out.println("密文: " + cipherText);
        System.out.println("");

        plainText = sm4.decryptDataCBC(cipherText);
        System.out.println("明文: " + plainText);

        System.out.println("CBC模式解密");
        System.out.println("密文：4esGgDn/snKraRDe6uM0jQ==");
        String cipherText2 = "4esGgDn/snKraRDe6uM0jQ==";
        plainText = sm4.decryptDataCBC(cipherText2);
        System.out.println("明文: " + plainText);
    }
}
