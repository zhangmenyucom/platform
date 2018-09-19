package com.platform.util.wechat;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;



public class SignUtil {

	public static String key = "46546546";

	public void getSig(){
		String sign = null;
		Map<String,String> pmapParms = new TreeMap<String, String>();
		
		//加入数据 模拟
		pmapParms.put("mch_appid", "13213");
		pmapParms.put("mchid", "13213");
		pmapParms.put("device_info", "13213");
		pmapParms.put("nonce_str", "13213");
		pmapParms.put("partner_trade_no", "13213");
		pmapParms.put("openid", "13213");
		pmapParms.put("check_name", "13213");
		pmapParms.put("re_user_name", "13213");
		pmapParms.put("amount", "13213");
		pmapParms.put("desc", "13213");
		pmapParms.put("spbill_create_ip", "13213");
		
		
/*		Payer payer = new Payer();
		pmapParms.put("mch_appid",payer.getMch_appid());
		pmapParms.put("mchid", payer.getMchid());
		pmapParms.put("device_info", payer.getDevice_info());
		pmapParms.put("nonce_str", payer.getNonce_str());
		pmapParms.put("partner_trade_no", payer.getPartner_trade_no());
		pmapParms.put("openid", payer.getOpenid());
		pmapParms.put("check_name", payer.getCheck_name());
		pmapParms.put("re_user_name", payer.getRe_user_name());
		pmapParms.put("amount", Integer.toString(payer.getAmount()));
		pmapParms.put("desc", payer.getDesc());
		pmapParms.put("spbill_create_ip", payer.getSpbill_create_ip());*/
		
	/*	String stringSignTemp=toOldQueryString(pmapParms)+"&key="+key;
		MD5Util md5Util = new MD5Util();
		sign =md5Util.EncodeUtf8(stringSignTemp);
		//return sign;*/
	}
	
    public static String toOldQueryString(Map<?, ?> data) {
    	
    	StringBuffer queryString = new StringBuffer();
    	
		try {
			for (Entry<?, ?> pair : data.entrySet()) {
				queryString.append(pair.getKey() + "=");
				queryString.append(pair.getValue()+"&");
			}
			if (queryString.length() > 0) {
				queryString.deleteCharAt(queryString.length() - 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return queryString.toString();
    }

}
