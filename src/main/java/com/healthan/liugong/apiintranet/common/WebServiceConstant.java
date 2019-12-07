package com.healthan.liugong.apiintranet.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 静态变量对象
 *
 * @autor yb
 * @date 2019/10/24 15:06
 **/
@Component
public class WebServiceConstant {

    /**
     * 操作人员工号或机具编号
     */
    public static String USER_ID;
    /**
     * 交易类型
     */
    public static String TRANSACTION_TYPE;

    /**
     * 封装过的his地址
     */
    public static String PAGECK_WSDL_URL;

    /**
     * 预约挂号的预约详情用的 原生his地址
     */
    public static String HDC_WSDL_URL;

    /**
     * 评价医院用的 原生his地址
     */
    public static String WSDL_URL;


    /**
     * 启动初始化静态参数
     *
     * @param webService
     */
    @Value("#{${webService}}")
    public void setWebService(Map<String, Object> webService) {
        //启动先初始化默认的客户端
        for (Map.Entry<String, Object> entry : webService.entrySet()) {
            Map<String, String> map = (Map<String, String>) entry.getValue();
            String wsdlUrl = map.get("wsdlUrl");
            if ("packageHis".equals(entry.getKey())) {
                this.USER_ID = map.get("userId");
                this.TRANSACTION_TYPE = map.get("transactionType");
                this.PAGECK_WSDL_URL = wsdlUrl;
            } else if ("hdcHis".equals(entry.getKey())) {
                this.HDC_WSDL_URL = wsdlUrl;
            } else {
                this.WSDL_URL = wsdlUrl;
            }
            WebServiceClent.initDefaultClient(wsdlUrl);
        }
    }

}