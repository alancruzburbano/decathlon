package com.kuenag.app.service;

import com.kuenag.app.model.DecathlonResult;

import java.util.StringTokenizer;

@FunctionalInterface
public interface ObjectBuilder {
    DecathlonResult mapToItem(StringTokenizer st);
}
