package com.platform.utils.wechat;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import net.sf.json.JSONObject;

public class CommonUtil {
	
	static SimpleDateFormat timeSdf = new SimpleDateFormat("yyyyMMddHHmmss");
	
	/**
     * 发送https请求
     * @param requestUrl 请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr 提交的数据
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
     */
    public static JSONObject httpsRequest(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = { new MyX509TrustManager() };
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setSSLSocketFactory(ssf);

            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            conn.setRequestMethod(requestMethod);

            // 当outputStr不为null时向输出流写数据
            if (null != outputStr) {
                OutputStream outputStream = conn.getOutputStream();
                // 注意编码格式
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // 从输入流读取返回内容
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }

            // 释放资源
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            conn.disconnect();
            jsonObject = JSONObject.fromObject(buffer.toString());
        } catch (ConnectException ce) {
            System.out.println("连接超时");
        } catch (Exception e) {
            System.out.println("请求异常");
        }
        return jsonObject;
    }
    
    /**
     * 生成订单号工具类
     * 当前时间+8位随机数 
     */
     public static String getTimeStr(){
 		return timeSdf.format(new Date())+randomOrderNum();
 	}
     
    /**
     * 生成8位随机数，附加在订单号后面，避免同一时间下单，订单号重复
     * @return
     */
    public static String randomOrderNum(){
    	Random random = new Random();
		String result = random.nextInt(100000000) + "";
		if (result.length() != 8) {
			return randomOrderNum();
		}
    	return result;
    }
    
    //从0开始截取到指定位置的字符串
	public static String subStrings(String str,String strEnd) {
		String result="";
		
		/* 找出指定的2个字符在 该字符串里面的 位置 */  
        int strEndIndex = str.indexOf(strEnd);  
  
        /* index 为负数 即表示该字符串中 没有该字符 */  
        if (strEndIndex < 0) {
        	
            return "error";
        }
        /* 开始截取 */
        result = str.substring(0, strEndIndex);
		
		return result;

	}
    
    
}
