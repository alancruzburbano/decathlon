package com.kuenag.app.service;

import com.kuenag.app.model.DecathlonResult;

import java.util.List;

/**
 * Contract with implemented methods to read contact list
 * This will be used by the client
 *
 * @author Alvaro Andres Cruz Burbano
 */
public interface ReadData {
    List<DecathlonResult> readDecathlonResultsFromFile();
}
