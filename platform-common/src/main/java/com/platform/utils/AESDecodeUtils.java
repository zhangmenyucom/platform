package com.platform.utils;

import com.platform.common.vo.EncryptedDataBean;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.AlgorithmParameterSpec;

/**
 * @author hgg
 * @date 2018/2/24
 * 小程序AES解密
 */
public class AESDecodeUtils {
    public static void main(String[] args) throws Exception {
        byte[] encrypData = java.util.Base64.getDecoder().decode("PwBjjfHsL7J6DysSqi8idAJXW+iHPjWHufbtOu0eWwRBAHi7wQFBbLB7qmPnk+M2H7GCpCEYGwekcZK8+otQX9NUg4fmpu5AhnmbQWW9zokLXgcH/01LCUFrJhsJ6E+ZKla6p2cZ8YTOtZEFaIapWYMQMIz3C+NE0dZoO+koXrhfL4U1IYvmC+nNEWbd9uIP0ESqdNk19y9O5A0Hg2xDfg==");
        byte[] ivData = java.util.Base64.getDecoder().decode("+Puyus9Aua7znYWSjT1eEQ==");
        byte[] sessionKey = java.util.Base64.getDecoder().decode("4ea1ohbscffn0xf8i67zkj2mfd66lo6q");
        System.out.println(decrypt(sessionKey, ivData, encrypData));
    }

    public static EncryptedDataBean decrypt(byte[] key, byte[] iv, byte[] encData) throws Exception {
        AlgorithmParameterSpec ivSpec = new IvParameterSpec(iv);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        //解析解密后的字符串
        return JsonUtil.getObjet(new String(cipher.doFinal(encData), "UTF-8"), EncryptedDataBean.class);
    }
}