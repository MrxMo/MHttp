package com.mrmo.mhttp.net;

/**
 * Created by moguangjian on 2017/3/12.
 */

public class HttpResultModel<T> {

    /**
     * status : 1
     * msg : 发送成功
     * data : {"code":273144}
     */

    private int status;
    private String msg;
    private T data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
