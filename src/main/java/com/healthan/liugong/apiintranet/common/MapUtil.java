package com.healthan.liugong.apiintranet.common;

import com.healthan.liugong.apiintranet.exception.BusinessException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: java bean 与 map 互相转换工具类
 * @Author: junqiang.lu
 * @Date: 2018/5/14
 */
@Component
public final class MapUtil {

    private MapUtil() {
    }

    /**
     * Map集合包含Map集合，Map<String,Map>
     * xml  To  map
     *
     * @throws Exception
     */
    public static Map<String, Object> toMapContainMap(String responseXmlTemp) {
        Document doc = parseText(responseXmlTemp);
        Element rootElement = doc.getRootElement();
        Map<String, Object> mapXml = new HashMap<>();
        elementToMapContainMap(mapXml, rootElement);
        return mapXml;
    }

    private static Document parseText(String responseXmlTemp) {
        Document doc = null;
        try {
            doc = DocumentHelper.parseText(responseXmlTemp);
        } catch (DocumentException e) {
            throw new BusinessException(BizEnum.PARSE_XML_ERR);
        }
        return doc;
    }

    /**
     * 使用递归调用将多层级xml转为map
     *
     * @param map
     * @param rootElement
     */
    private static void elementToMapContainMap(Map<String, Object> map, Element rootElement) {
        // 获得当前节点的子节点
        List<Element> childElements = rootElement.elements();
        if (childElements.size() > 0) {
            Map<String, Object> tempMap = new HashMap<>();
            for (Element e : childElements) {
                elementToMapContainMap(tempMap, e);
                map.put(rootElement.getName(), tempMap);
            }
        } else {
            map.put(rootElement.getName(), rootElement.getData());
        }
    }

    /**
     * 大多数his接口都能满足  带有list的
     *
     * @param resultXml
     * @return
     */
    public static Map<String, Object> toMapContainListByHis(String resultXml) {
        Document doc = parseText(resultXml);
        Element rootElement = doc.getRootElement();
//        Map<String, Object> rootMap = new HashMap();
        Map<String, Object> mapXml = new HashMap();
//        rootMap.put(rootElement.getName(), mapXml);
        elementToMapContainList(mapXml, rootElement);
        return mapXml;
    }


    /**
     * 遍历子节点
     *
     * @param map
     * @param rootElement
     */
    public static void elementToMapContainList(Map<String, Object> map, Element rootElement) {
        //获得当前节点的子节点
        List<Element> elements = rootElement.elements();

        for (Element element : elements) {
            List<Element> es = element.elements();
            if (!CollectionUtils.isEmpty(es)) {
                //如果还存在子节点，且是List结尾，就考虑list情况，继续递归
                //如果是s结尾并且子节点与孙节点 + s 的名字一致，也是list处理
                String nextElement = es.get(0).getName() + "List";
                if (element.getName().endsWith("List") && element.getName().equals(nextElement)) {
                    //获取当前节点下的子节点
                    ArrayList<Map> list = new ArrayList();
                    for (Element e : es) {
                        elementChildToList(list, e);
                    }
                    map.put(element.getName(), list);
                } else {
                    //否则当map处理
                    Map<String, Object> tempMap = new HashMap();
                    for (Element e : es) {
                        elementToMapContainMap(tempMap, e);
                    }
                    map.put(element.getName(), tempMap);
                }
            } else {
                if(element.getName().endsWith("List")){
                    map.put(element.getName(), new ArrayList());
                }else{
                    map.put(element.getName(), element.getData());
                }
            }

        }
    }

    /**
     * 递归子节点
     *
     * @param arrayList
     * @param rootElement
     */
    public static void elementChildToList(ArrayList<Map> arrayList, Element rootElement) {
        //获得当前节点的子节点
        List<Element> elements = rootElement.elements();
        if (elements.size() > 0) {
            ArrayList<Map> list = new ArrayList();
            Map<String, Object> sameTempMap = new HashMap();
            for (Element element : elements) {
                elementChildToList(list, element);
                sameTempMap.put(element.getName(), element.getData());
            }
            arrayList.add(sameTempMap);
        }
    }

    /**
     * map 转 xml (封装过的his接口格式)
     *
     * @param objectMap
     * @return
     */
    public static String mapToXmlByPackageHis(Map<String, Object> objectMap) {
        StringBuilder sb = new StringBuilder();
        sb.append("<request>");
        sb.append("<userId>").append(WebServiceConstant.USER_ID).append("</userId>");
        sb.append("<transactionType>").append(WebServiceConstant.TRANSACTION_TYPE).append("</transactionType>");
        if (!CollectionUtils.isEmpty(objectMap)) {
            for (Map.Entry<String, Object> entry : objectMap.entrySet()) {
                String param = entry.getKey();
                Object value = entry.getValue();
                sb.append("<").append(param).append(">").append(value).append("</").append(param).append(">");
            }
        }
        sb.append("</request>");
        return sb.toString();
    }

    /**
     * map 转 xml (仅微信支付xml格式)
     *
     * @param dataMap map 数据
     * @return
     */
    public static String mapToXmlByWechat(Map<String, String> dataMap) {
        StringBuilder xml = new StringBuilder();
        StringBuilder value = new StringBuilder();
        xml.append("<xml>");
        for (String key : dataMap.keySet()) {
            value.append("<![CDATA[").append(dataMap.get(key)).append("]]>");
            xml.append("<").append(key).append(">").append(value).append("</").append(key).append(">");
        }
        xml.append("</xml>");

        return xml.toString();
    }

    /**
     * map 转 xml (原生his接口的格式)
     *
     * @param dataMap map 数据
     * @return
     */
    public static String mapToXmlByBaseHis(Map<String, Object> dataMap) {
        StringBuilder xml = new StringBuilder();
        StringBuilder value = new StringBuilder();
        xml.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        xml.append("<root>");
        for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
            xml.append("<").append(entry.getKey()).append(">").append(entry.getValue()).append("</").append(entry.getKey()).append(">");
        }
        xml.append("</root>");
        return xml.toString();
    }
}
