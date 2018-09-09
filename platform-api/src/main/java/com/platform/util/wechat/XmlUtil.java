package com.platform.util.wechat;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 创建时间：2016年11月3日 上午11:40:17
 *
 * @author andy
 * @version 2.2
 */

public class XmlUtil {

	private static final String PREFIX_XML = "<xml>";

	private static final String SUFFIX_XML = "</xml>";

	private static final String PREFIX_CDATA = "<![CDATA[";

	private static final String SUFFIX_CDATA = "]]>";

	/**
	 * 转化成xml, 单层无嵌套
	 *
	 * @param parm
	 * @param isAddCDATA
	 * @return
	 */
	public static String xmlFormat(Map<String, String> parm, boolean isAddCDATA) {

		StringBuffer strbuff = new StringBuffer(PREFIX_XML);
		if (CollectionUtil.isNotEmpty(parm)) {
			for (Entry<String, String> entry : parm.entrySet()) {
				strbuff.append("<").append(entry.getKey()).append(">");
				if (isAddCDATA) {
					strbuff.append(PREFIX_CDATA);
					if (StringUtil.isNotEmpty(entry.getValue())) {
						strbuff.append(entry.getValue());
					}
					strbuff.append(SUFFIX_CDATA);
				} else {
					if (StringUtil.isNotEmpty(entry.getValue())) {
						strbuff.append(entry.getValue());
					}
				}
				strbuff.append("</").append(entry.getKey()).append(">");
			}
		}
		return strbuff.append(SUFFIX_XML).toString();
	}

    /**
     * 解析xml
     *
     * @param xml
     * @return
     * @throws XmlPullParserException
     * @throws IOException
     */
    public static Map<String, String> xmlParse(String xml) throws XmlPullParserException, IOException {
        Map<String, String> map = null;
        if (StringUtil.isNotEmpty(xml)) {
            InputStream inputStream = new ByteArrayInputStream(xml.getBytes());
            XmlPullParser pullParser = XmlPullParserFactory.newInstance().newPullParser();
            // 为xml设置要解析的xml数据
            pullParser.setInput(inputStream, "UTF-8");
            int eventType = pullParser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        map = new HashMap<>();
                        break;
                    case XmlPullParser.START_TAG:
                        String key = pullParser.getName();
                        if (key.equals("xml"))
                            break;
                        String value = pullParser.nextText().trim();
                        map.put(key, value);
                        break;
                    case XmlPullParser.END_TAG:
                        break;
                }
                eventType = pullParser.next();
            }
        }
        return map;
    }
    /**
     * @param xml
     * @return Map
     * @description 将xml字符串转换成map
     */
    public static Map<String, String> xmlParse2(String xml) {
        Map<String, String> map = new HashMap<>(8);
        Document doc = null;
        try {
            doc = DocumentHelper.parseText(xml);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element rootElt = doc.getRootElement();
        Iterator iter = rootElt.elementIterator();
        while (iter.hasNext()) {
            Element recordEle = (Element) iter.next();
            map.put(recordEle.getName(), recordEle.getStringValue());
        }
        return map;
    }

}
