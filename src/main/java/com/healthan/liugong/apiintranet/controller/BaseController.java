package com.healthan.liugong.apiintranet.controller;

import com.healthan.liugong.apiintranet.common.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class BaseController {

    public static Logger logger = LoggerFactory.getLogger(BaseController.class);

    /**
     *
     * @param code 0 表示操作成功
     * @param result 返回的结果集
     * @param message 返回错误信息 如果 result 是 1 message 提示错误信息
     * @return Map<String,Object>
     */
    protected Map<String,Object> renderResult(Results result) {
        Map<String,Object> map  = new HashMap<String,Object>();
        map.put("code", result.getCode());
        map.put("result", result.getResult());
        map.put("message", result.getMessage());
        return map;
    }

}