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

    public void setIndexPosition(int indexPosition) {
        this.indexPosition = indexPosition;
    }

    public int getResultCompetition() {
        return resultCompetition;
    }

    public void setResultCompetition(int resultCompetition) {
        this.resultCompetition = resultCompetition;
    }
}
