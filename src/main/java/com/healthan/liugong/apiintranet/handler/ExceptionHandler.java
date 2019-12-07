package com.healthan.liugong.apiintranet.handler;

import com.healthan.liugong.apiintranet.exception.BusinessException;
import com.healthan.liugong.apiintranet.common.BizEnum;
import com.healthan.liugong.apiintranet.common.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * @autor yb
 * @date 2019/9/19 17:40
 **/

@RestControllerAdvice
public class ExceptionHandler {
    public static Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public Results handleException(Exception e) {
        logger.error(BizEnum.UNSPECIFIED.getDesc(), e);
        return new Results(BizEnum.UNSPECIFIED.getCode(), BizEnum.UNSPECIFIED.getDesc(), null);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public Results handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        //打印校验住的所有的错误信息
        List<ObjectError> list = e.getBindingResult().getAllErrors();
        Integer code = Integer.valueOf(BizEnum.BIZ_ERR.getCode());
        String msg = list.get(0).getDefaultMessage();
        logger.error("业务异常", e);
        return new Results(code, msg, null);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(BusinessException.class)
    public Results handleBusinessException(BusinessException e) {
        String[] responses = e.getMessage().split(",");
        Integer code = Integer.valueOf(responses[0]);
        String msg = responses[1];
        logger.error("业务异常", e);
        return new Results(code, msg, null);
    }
}