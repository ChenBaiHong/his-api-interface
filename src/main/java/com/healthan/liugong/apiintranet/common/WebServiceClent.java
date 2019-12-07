package com.healthan.liugong.apiintranet.common;

import com.healthan.liugong.apiintranet.aop.WebLogAspect;
import com.healthan.liugong.apiintranet.exception.BusinessException;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @autor yb
 * @date 2019/10/14 17:38
 **/
public class WebServiceClent {
    public static final Logger log = LoggerFactory.getLogger(WebLogAspect.class);
    private static Map<String, Client> wsdlClients = new HashMap(8);

    /**
     * 指定的wsdlUrl
     *
     * @param wsdlUrl webService 的url
     * @param method  调用的方法名
     * @param params  参数
     * @return
     */
    public static String getByWsdlUrl(String wsdlUrl, String method, Object... params) {
        Client client = initDefaultClient(wsdlUrl);
        try {
            // invoke("方法名",参数1,参数2,参数3....);
            Object[] objects = client.invoke(method, params);
            return (String) objects[0];
        } catch (Exception e) {
            log.error("请求异常：", e);
            throw new BusinessException(BizEnum.HIS_NOT_RETURN, e.getMessage());
        }
    }

    public static Client initDefaultClient(String wsdlUrl) {
        String serviceName = wsdlUrl.substring(wsdlUrl.lastIndexOf("//") + 2, wsdlUrl.lastIndexOf("?"));
        // 创建动态客户端
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client;
        if (wsdlClients.containsKey(serviceName)) {
            client = wsdlClients.get(serviceName);
        } else {
            client = dcf.createClient(wsdlUrl);
            wsdlClients.put(serviceName, client);
        }
        return client;
    }
}
