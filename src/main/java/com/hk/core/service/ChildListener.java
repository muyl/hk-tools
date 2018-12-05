package com.hk.core.service;

import java.util.List;

public interface ChildListener {

    void childChanged(String path, List<String> children);

}