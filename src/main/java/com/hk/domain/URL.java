package com.hk.domain;

import java.io.Serializable;

public final class URL implements Serializable {

    private final String host;

    private final int port;


    protected URL() {
        this.host = null;
        this.port = 0;
    }

    public URL(String host, int port) {
        this.host = host;
        this.port = port;
    }


    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }
}
