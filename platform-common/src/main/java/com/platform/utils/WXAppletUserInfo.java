package com.platform.utils;

import com.platform.common.vo.EncryptedDataBean;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.util.Arrays;

public class WXAppletUserInfo {
    public static void main(String[] args) throws Exception {
        getUserMobile("a5rp3wjfazpqakqj181rgdfnzwijncy3","/UufPunN1z/dq+6E4P2O5w==","iGzVycm5fYNKmFx6+3RaCjmIvccutIYxnT0FSt/wpyTqa0Cr2kDB/okJ3A2wBuieTR0ij5TKxxHS5zSRyzzhXzVXsJIWmF1SNoHjeAv+TD7eSYH9oQ/fc86KUZVbBVSfgXdis1A+Pi/e4cwK8eaOqOaVSvJekByrI5D9SAZ4fPP0RHkWyLidax1H8AniSzETIsdZ3dbMVqSy9YMH1Q3YVg==");
    }
    /**
     * 解密用户敏感数据获取用户信息
     *
     * @param sessionKey    数据进行加密签名的密钥
     * @param encryptedData 包括敏感数据在内的完整用户信息的加密数据
     * @param iv            加密算法的初始向量
     */
    public static EncryptedDataBean getUserMobile(String sessionKey, String iv, String encryptedData) throws Exception {
        byte[] encrypBytes = org.codehaus.xfire.util.Base64.decode(encryptedData);
        byte[] ivBytes = org.codehaus.xfire.util.Base64.decode(iv);
        byte[] keyBytes = org.codehaus.xfire.util.Base64.decode(sessionKey);

        try {
            // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
            int base = 16;
            if (keyBytes.length % base != 0) {
                int groups = keyBytes.length / base + (keyBytes.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyBytes, 0, temp, 0, keyBytes.length);
                keyBytes = temp;
            }
            // 初始化
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "BC");
            SecretKeySpec spec = new SecretKeySpec(keyBytes, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivBytes));
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);
            byte[] resultByte = cipher.doFinal(encrypBytes);
            if (null != resultByte && resultByte.length > 0) {
                return JsonUtil.getObjet(new String(resultByte, "UTF-8"), EncryptedDataBean.class);
            }
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        return null;
    }
}