package com.kuenag.app.model;

public class CompetitionParams {

    private double athleteResult;
    private double parameterA;
    private double parameterB;
    private double parameterC;

    public CompetitionParams(double athleteResult, double parameterA, double parameterB, double parameterC) {
        this.athleteResult = athleteResult;
        this.parameterA = parameterA;
        this.parameterB = parameterB;
        this.parameterC = parameterC;
    }

    public double getAthleteResult() {
        return athleteResult;
    }

    public void setAthleteResult(double athleteResult) {
        this.athleteResult = athleteResult;
    }

    public double getParameterA() {
        return parameterA;
    }

    public void setParameterA(double parameterA) {
        this.parameterA = parameterA;
    }

    public double getParameterB() {
        return parameterB;
    }

    public void setParameterB(double parameterB) {
        this.parameterB = parameterB;
    }

    public double getParameterC() {
        return parameterC;
    }

    public void setParameterC(double parameterC) {
        this.parameterC = parameterC;
    }
}
