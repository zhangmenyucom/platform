package com.platform.config;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取配置文件
 */
public class Config {

	    
	    /**
	     * 微信支付商户密钥
	     */
	    public static String key ;
	    
	    /**
	     * 微信支付商户号
	     */
	    public static String mch_id;
	    
	    /**
	     * 微信支付请求url
	     */
	    public static String req_url;
	    
	    /**
	     * 通知url
	     */
	    public static String notify_url;
	    
	    /**
	     * appid
	     */
	    public static String sub_appid;
	    
	    /**
	     * appsecret
	     */
	    public static String sub_appsecret;
	    /**
	     * 授权
	     */
	    public static String granttype;
	    /**
	     * 七牛图片外链
	     */
	    public static String qiniu_url;
	    /**
	     * 企业付款api
	     */
	    public static String enterprice_url;
	    /**
	     * 用户openid
	     */
	    public static String openid;
	    
	    /**
	     * 微信支付证书路径
	     */
	    public static String cert_path;
	    
	    static{
	        Properties prop = new Properties();   
	        InputStream in = Config.class.getResourceAsStream("/config.properties");   
	        try {
	            prop.load(in);
	            key = prop.getProperty("key").trim();
	            mch_id = prop.getProperty("mch_id").trim();
	            req_url = prop.getProperty("req_url").trim();
	            notify_url = prop.getProperty("notify_url").trim();
	            sub_appid = prop.getProperty("sub_appid").trim();
	            sub_appsecret = prop.getProperty("sub_appsecret").trim();
	            granttype = prop.getProperty("granttype").trim();
	            qiniu_url = prop.getProperty("qiniu_url").trim();
	            enterprice_url = prop.getProperty("enterprice_url").trim();
	            openid = prop.getProperty("openid").trim();
	            cert_path = prop.getProperty("cert_path").trim();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } 
	    }
	}
