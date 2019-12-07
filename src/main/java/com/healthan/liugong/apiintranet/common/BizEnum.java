package com.healthan.liugong.apiintranet.common;

/**
 * @autor yb
 * @date 2019/9/24 9:05
 **/
public enum BizEnum {
    /**
     * 未指明的异常
     */
    UNSPECIFIED(500, "网络异常，请稍后再试。"),

    BIZ_ERR(10000000,"业务异常"),

    HIS_NOT_RETURN(10000001,"HIS没有返回任何数据，请联系负责人！"),

    PARSE_XML_ERR(10000002,"解析返回的xml出错了。"),
    OPERATION_TYPE_ERR(10000003,"请求参数：操作类型operationType无效"),


    ;
    /**
     * 错误码
     */
    private Integer code;

    /**
     * 描述
     */
    private  String desc;

    /**
     * @param code        错误码
     * @param desc 描述
     */
    BizEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc){
        this.desc = desc;
    }
}
