package com.hk.core.domain;

import java.io.Serializable;

public final class URL implements Serializable {

    private final String host;


    protected URL() {
        this.host = null;
    }

    public URL(String host) {
        this.host = host;
    }


    public String getHost() {
        return host;
    }


}
