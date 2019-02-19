package com.kuenag.app.domain;

import com.kuenag.app.model.CompetitionParams;

/**
 * Functional Interface declaration  for Decathlon math form to calculate results
 *
 * @author Alvaro Andres Cruz Burbano
 */

@FunctionalInterface
public interface Calculator {

    int calculatePoints(CompetitionParams decathlonValues);
}
