package com.healthan.liugong.apiintranet.service;

import java.util.Map;

/**
 * @autor yb
 * @date 2019/10/15 10:31
 **/
public interface HospitalService {

    Map<String, Object> getPackageHis(String methodName, Map<String, Object> requestMap);

    Map<String, Object> getByHdcHis(String methodName, Map<String, Object> requestMap);

    Map<String, Object> getByOHis(String methodName, Map<String, Object> requestMap);
}
