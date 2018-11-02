package com.wb.weibao.utils.update;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 * @author sqq
 * @version V1.0.0
 * @date 2017/5/5 09:39
 */
public class NullHostNameVerifier implements HostnameVerifier {

    @Override
    public boolean verify(String hostname, SSLSession session) {
        return true;

    }
}
