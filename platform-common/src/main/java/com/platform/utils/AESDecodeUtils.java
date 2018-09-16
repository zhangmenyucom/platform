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
        byte[] encrypData = Base64.decode("mgxuts7uEbdnBclp0Qim0TS3MGoaSyE09MqTvbeG9Z1PjxsDwjVH0FxG1Q==").getBytes();
        byte[] ivData = Base64.decode("SG386etdA3sOXHxqfnw==").getBytes();
        byte[] sessionKey = Base64.decode("Fn6r4IOiZJBXn4hQ0w==").getBytes();
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