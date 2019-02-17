package com.kuenag.app.utils;

import com.kuenag.app.model.DecathlonResult;

import java.util.StringTokenizer;

@FunctionalInterface
public interface ObjectBuilder {
    public DecathlonResult mapToItem(StringTokenizer st);
}
