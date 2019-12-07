package com.healthan.liugong.apiintranet.exception;


import com.healthan.liugong.apiintranet.common.BizEnum;

/**
 * @autor yb
 * @date 2019/9/24 9:03
 **/
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = -7864604160297181941L;

    public BusinessException(BizEnum bizEnum) {
        super(bizEnum.getCode() + "," + bizEnum.getDesc());
    }

    public BusinessException(BizEnum bizEnum, String message) {
        super(bizEnum.getCode() + "," + message);
    }
}
