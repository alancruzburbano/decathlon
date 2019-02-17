package com.kuenag.app.service;

import com.kuenag.app.model.DecathlonResult;

import java.util.List;

/**
 * Contract with common abstraction to read information
 *
 * @author Alvaro Andres Cruz Burbano
 */
public interface SourceReadable {
    List<DecathlonResult> readItems();
}
