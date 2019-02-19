package com.kuenag.app.service;

import com.kuenag.app.model.DecathlonResult;

import java.util.List;

/**
 * This Class implements a simple contract
 * Is a service that  returns a SourceReadable (contract)
 * that could be from file or a different origin
 *
 * @author Alvaro Andres Cruz Burbano
 */

public class ReadDecathlonResults implements ReadData {

    SourceReadable readFromFile;

    @Override
    public List<DecathlonResult> readDecathlonResultsFromFile() {
        readFromFile = new ReadFromFile();
        return readFromFile.readItems();
    }
}
