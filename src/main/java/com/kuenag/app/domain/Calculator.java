package com.kuenag.app.domain;

import com.kuenag.app.model.CompetitionParams;

@FunctionalInterface
public interface Calculator {

    public int calculatePoints(CompetitionParams decathlonValues);
}
