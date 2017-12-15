package com.mrmo.mhttplib.config;

import android.support.annotation.Nullable;

/**
 * <p>框架配置信息。</p>
 *
 * @author moguangjian
 * @date 2017/12/15
 */
public class MHttpConfig {

    public static MHttpConfig mhttpConfig;

    private HttpErrorHintAble httpErrorHintAble;

    private MHttpConfig() {
    }

    public static MHttpConfig getInstance() {
        if (mhttpConfig == null) {
            synchronized (MHttpConfig.class) {
                if (mhttpConfig == null) {
                    mhttpConfig = new MHttpConfig();
                }
            }
        }

        return mhttpConfig;
    }


    public MHttpConfig setHttpErrorHintAble(@Nullable HttpErrorHintAble httpErrorHintAble) {
        this.httpErrorHintAble = null;
        this.httpErrorHintAble = httpErrorHintAble;
        return this;
    }

    @Nullable
    public HttpErrorHintAble getHttpErrorHintAble() {
        return httpErrorHintAble;
    }

}
