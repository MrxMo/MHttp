package com.mrmo.mhttplib;

/**
 * Created by moguangjian on 2017/3/12.
 */

public interface MHttpCode {

    /**未知异常**/
    int M_HTTP_CODE_UNKNOWN = -1000;

    /**http请求数据异常**/
    int M_HTTP_CODE_RESPONSE_DATA_EXCEPTION = -1001;

    /**json解析后，model为空**/
    int M_HTTP_CODE_HANDLE_DATA_NULL = -1002;
}
