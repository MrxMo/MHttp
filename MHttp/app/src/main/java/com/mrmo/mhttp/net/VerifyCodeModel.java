package com.mrmo.mhttp.net;

/**
 * Created by moguangjian on 2017/3/14.
 */

public class VerifyCodeModel {
    @Override
    public String toString() {
        return "VerifyCodeModel{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    /**
     * status : 1
     * msg : 发送成功
     * data : {"code":366957}
     */

    private int status;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        @Override
        public String toString() {
            return "DataBean{" +
                    "code=" + code +
                    '}';
        }

        /**
         * code : 366957
         */

        private int code;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }
}
