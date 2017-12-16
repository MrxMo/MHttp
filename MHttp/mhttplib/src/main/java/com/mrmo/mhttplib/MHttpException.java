package com.mrmo.mhttplib;

/**
 * 自定义http异常
 *
 * @author moguangjian
 * @date 2017/3/12
 */

public class MHttpException extends RuntimeException {

    /**
     * 请求状态码
     **/
    private int code;

    /**
     * 简单信息描述
     **/
    private String msg;

    /**
     * 详细描述
     **/
    private String description;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "MHttpException{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
