package com.platform.util.wechat;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

/**
 * 加载配置文件
 *
 */
public class WeixinConfigUtils {
    private static final Logger log = LoggerFactory.getLogger(WeixinConfigUtils.class);
    
    
    /**
     * 解析申请退款之后微信返回的值并进行存库操作
     * @throws IOException 
     * @throws JDOMException 
     */
    @SuppressWarnings("unchecked")
	public static Map<String, String> parseRefundXml(String refundXml) throws JDOMException, IOException{
       // ParseXMLUtils.jdomParseXml(refundXml);
        StringReader read = new StringReader(refundXml);
        // 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
        InputSource source = new InputSource(read);
        // 创建一个新的SAXBuilder
        SAXBuilder sb = new SAXBuilder();
        // 通过输入源构造一个Document
        org.jdom.Document doc;
        doc = (org.jdom.Document) sb.build(source);
        org.jdom.Element root = doc.getRootElement();// 指向根节点
        List<org.jdom.Element> list = root.getChildren();
        Map<String, String> refundOrderMap = new HashMap<String, String>();
        if(list!=null&&list.size()>0){
            for (org.jdom.Element element : list) {
                refundOrderMap.put(element.getName(), element.getText());
            }
            return refundOrderMap;
            }
        return null;
    }
    
}