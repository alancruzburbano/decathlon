package com.kuenag.app.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "decathlonResults")
@XmlAccessorType(XmlAccessType.FIELD)
public class Results {

    private List<DecathlonResult> results;

    public Results(){}

    public Results(List<DecathlonResult> results) {
        this.results = results;
    }

}
