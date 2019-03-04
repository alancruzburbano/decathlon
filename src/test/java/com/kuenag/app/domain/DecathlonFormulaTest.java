package com.kuenag.app.domain;

import com.kuenag.app.model.CompetitionParams;
import com.kuenag.app.model.DecathlonResult;
import com.kuenag.app.utils.Constants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DecathlonFormulaTest {

    DecathlonResult resultTest;

    @Before
    public void init(){
        resultTest = new DecathlonResult("John Smith",5.0,9.22,1.50,21.6,2.6,35.81,12.57,60.39,16.43,"5.25.72",4207);
    }

    @Test
    public void evaluateDecathlonFormula() {
        int expected = 4207;
        int actual = DecathlonFormula.evaluateDecathlonFormula(resultTest);
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void evaluateParametersByCompetitionType() {
        CompetitionParams competitionObjectTest = new CompetitionParams(resultTest.getOneHundredMeters(),Constants.A_ONE_HUNDRED_METERS,Constants.B_ONE_HUNDRED_METERS,Constants.C_ONE_HUNDRED_METERS);
        double expected = 543.0;
        double actual = DecathlonFormula.evaluateParametersByCompetitionType(competitionObjectTest);
        Assert.assertEquals(expected,actual,0);
    }

    @Test
    public void calculateTotalSecondsForRunningCompetition() {
        double expected = 325.72;
        double actual = DecathlonFormula.calculateTotalSecondsForRunningCompetition("5.25.72");
        Assert.assertEquals(expected,actual,0);
    }

    @Test
    public void calculateTotalCentimetresForJumpingCompetition() {
        double expected = 1240.0;
        double actual = DecathlonFormula.calculateTotalCentimetresForJumpingCompetition(12.4);
        Assert.assertEquals(expected,actual,0);
    }
}