package com.kuenag.app.model;

/**
 * This class is the model of the parameters to send to the formula
 */
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

    public double getParameterA() {
        return parameterA;
    }

    public double getParameterB() {
        return parameterB;
    }

    public double getParameterC() {
        return parameterC;
    }
}
