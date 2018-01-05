package com.lbi.util;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

public enum HttpclientSingleton {

    HC();

    private HttpclientSingleton() {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(300);
        cm.setDefaultMaxPerRoute(300);

        /* keep this blcok for further tuning.
        RequestConfig requestConfig = RequestConfig
                .copy(RequestConfig.DEFAULT)
                .setConnectionRequestTimeout(1000)
                .build();

        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(cm)
                .setRetryHandler(new DefaultHttpRequestRetryHandler(3, true))
                .build();
         */
        CloseableHttpClient httpClient = HttpClients.createMinimal(cm);

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
        factory.setReadTimeout(1000);
        this.requestFactory = factory;
    }

    private final ClientHttpRequestFactory requestFactory;

    public ClientHttpRequestFactory get() {
        return requestFactory;
    }
}
