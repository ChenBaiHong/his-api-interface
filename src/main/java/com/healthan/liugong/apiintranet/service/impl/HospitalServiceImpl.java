package com.healthan.liugong.apiintranet.service.impl;


import com.healthan.liugong.apiintranet.aop.WebLogAspect;
import com.healthan.liugong.apiintranet.common.WebServiceConstant;
import com.healthan.liugong.apiintranet.exception.BusinessException;
import com.healthan.liugong.apiintranet.common.BizEnum;
import com.healthan.liugong.apiintranet.common.MapUtil;
import com.healthan.liugong.apiintranet.common.WebServiceClent;
import com.healthan.liugong.apiintranet.service.HospitalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @autor yb
 * @date 2019/10/15 10:34
 **/

@Service
public class HospitalServiceImpl implements HospitalService {
    public static final Logger log = LoggerFactory.getLogger(WebLogAspect.class);

    @Override
    public Map<String, Object> getPackageHis(String methodName, Map<String, Object> requestMap) {
        String request = MapUtil.mapToXmlByPackageHis(requestMap);
        log.info("PACKAGE_HIS请求参数：" + request);
        String response = WebServiceClent.getByWsdlUrl(WebServiceConstant.PAGECK_WSDL_URL, methodName, request);
        log.info("PACKAGE_HIS返回参数：" + response);
        Map<String, Object> resultMap = MapUtil.toMapContainListByHis(response);
        if (CollectionUtils.isEmpty(resultMap)) {
            throw new BusinessException(BizEnum.HIS_NOT_RETURN);
        }
        Map<String, Object> responseMsg = (Map<String, Object>) resultMap.get("responseHead");
        String resultCode = (String) responseMsg.get("code");
        if (!"0".equals(resultCode)) {
            String errorMsg = (String) responseMsg.get("message");
            throw new BusinessException(BizEnum.BIZ_ERR, errorMsg);
        }
        resultMap.remove("responseHead");
        return resultMap;
    }

    @Override
    public Map<String, Object> getByHdcHis(String methodName, Map<String, Object> requestMap) {
        String header = toHeader(methodName, "", "dQRbFkbW155zJ90M2tONfXVAVfze9mfn");
        String body = MapUtil.mapToXmlByBaseHis(requestMap);
        log.info("HDC_HIS请求参数：header[" + header + "],body[" + body + "]");
        String response = WebServiceClent.getByWsdlUrl(WebServiceConstant.WSDL_URL, "CallInterface", header, body);
        log.info("HDC_HIS返回参数：" + response);
        Map<String, Object> resultMap = changeHisResponse(response);
        return resultMap;
    }

    @Override
    public Map<String, Object> getByOHis(String methodName, Map<String, Object> requestMap) {
        String header = toHeader(methodName, "", "dQRbFkbW155zJ90M2tONfXVAVfze9mfn");
        String body = MapUtil.mapToXmlByBaseHis(requestMap);
        log.info("O_HIS请求参数：header[" + header + "],body[" + body + "]");
        String response = WebServiceClent.getByWsdlUrl(WebServiceConstant.HDC_WSDL_URL, "CallInterface", header, body);
        log.info("O_HIS返回参数：" + response);
        Map<String, Object> resultMap = changeHisResponse(response);
        return resultMap;
    }

    /**
     * 调原生his接口，是没有code之类的，如果返回不是xml格式，就是出错了
     *
     * @param response
     * @return
     */
    private Map<String, Object> changeHisResponse(String response) {
        if (!response.startsWith("<?xml")) {
            if (response.indexOf("未查到相应信息") >= 0) {
                return new HashMap();
            }
            throw new BusinessException(BizEnum.BIZ_ERR, response);
        }
        Map<String, Object> resultMap = MapUtil.toMapContainListByHis(response);
        if (CollectionUtils.isEmpty(resultMap)) {
            throw new BusinessException(BizEnum.HIS_NOT_RETURN);
        }
        return resultMap;
    }

    /**
     * 原生his接口，组装请求头 header
     *
     * @param methodName
     * @param callOperator
     * @param certificate
     * @return
     */
    private String toHeader(String methodName, String callOperator, String certificate) {
        StringBuilder headerXmlStr = new StringBuilder();
        headerXmlStr.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        headerXmlStr.append("<root>");
        headerXmlStr.append("<serverName>").append(methodName).append("</serverName>");
        headerXmlStr.append("<format>xml</format>");
        headerXmlStr.append("<callOperator>").append(callOperator).append("</callOperator>");
        headerXmlStr.append("<certificate>").append(certificate).append("</certificate>");
        headerXmlStr.append("<msgNo></msgNo>");
        headerXmlStr.append("<sendTime></sendTime>");
        headerXmlStr.append("<sendCount></sendCount>");
        headerXmlStr.append("</root>");
        return headerXmlStr.toString();
    }
}
