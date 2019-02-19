package com.kuenag.app.model;

public class TieAcumulator {
    int indexPosition;
    int resultCompetition;

    public TieAcumulator(int indexPosition, int resultCompetition) {
        this.indexPosition = indexPosition;
        this.resultCompetition = resultCompetition;
    }

    public int getIndexPosition() {
        return indexPosition;
    }

}
