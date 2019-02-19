package com.kuenag.app.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * This is the result model, to be transformed into a xml file
 */


@XmlRootElement(name = "decathlonResults")
@XmlAccessorType(XmlAccessType.FIELD)
public class Results {

    private List<DecathlonResult> results;

    public Results(){}

    public Results(List<DecathlonResult> results) {
        this.results = results;
    }

    public List<DecathlonResult> getResults() {
        return results;
    }

}
