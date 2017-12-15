package com.mrmo.mhttplib.config;

/**
 * <p>网络请求默认错误提示配置。</p>
 *
 * @author moguangjian
 * @date 2017/12/15
 */
public interface HttpErrorHintAble {

    /**
     * @param statusCode 错误状态码
     * @param hint       提示语
     */
    void onShow(int statusCode, String hint);

    /**
     * 是否屏蔽默认提示
     */
    boolean isShowDefaultHint();
}
