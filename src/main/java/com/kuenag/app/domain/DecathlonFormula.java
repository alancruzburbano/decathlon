package com.kuenag.app.domain;

import com.kuenag.app.model.CompetitionParams;
import com.kuenag.app.model.DecathlonResult;
import com.kuenag.app.utils.Constants;

import java.util.StringTokenizer;

public class DecathlonFormula {

    private static Calculator evaluateResultTrackEvent = (formValues) -> {
        int result = (int) (formValues.getParameterA() * (Math.pow(Math.abs((formValues.getParameterB() - formValues.getAthleteResult())), formValues.getParameterC())));
        if (result > 0)
            return result;
        else
            return 0;
    };

    public static int evaluateDecathlonFormula(DecathlonResult athleteResults){
        double result =
                evaluateParametersByCompetitionType(new CompetitionParams(athleteResults.getOneHundredMeters(),Constants.A_ONE_HUNDRED_METERS,Constants.B_ONE_HUNDRED_METERS,Constants.C_ONE_HUNDRED_METERS)) +
                        evaluateParametersByCompetitionType(new CompetitionParams(getTotalCentimetresForJumping(athleteResults.getLongJump()),Constants.A_LONG_JUMP,Constants.B_LONG_JUMP,Constants.C_LONG_JUMP)) +
                        evaluateParametersByCompetitionType(new CompetitionParams(athleteResults.getShotPut(),Constants.A_SHOT_PUT,Constants.B_SHOT_PUT,Constants.C_SHOT_PUT)) +
                        evaluateParametersByCompetitionType(new CompetitionParams(getTotalCentimetresForJumping(athleteResults.getHighJump()),Constants.A_HIGH_JUMP,Constants.B_HIGH_JUMP,Constants.C_HIGH_JUMP)) +
                        evaluateParametersByCompetitionType(new CompetitionParams(athleteResults.getFourHundredMeters(),Constants.A_FOUR_HUNDRED_METERS,Constants.B_FOUR_HUNDRED_METERS,Constants.C_FOUR_HUNDRED_METERS)) +
                        evaluateParametersByCompetitionType(new CompetitionParams(athleteResults.getOneHundredTenMetersHurdles(),Constants.A_ONE_HUNDRED_METERS_HURDLES,Constants.B_ONE_HUNDRED_METERS_HURDLES,Constants.C_ONE_HUNDRED_METERS_HURDLES)) +
                        evaluateParametersByCompetitionType(new CompetitionParams(athleteResults.getDiscusThrow(),Constants.A_DISCUS_THROW,Constants.B_DISCUS_THROW,Constants.C_DISCUS_THROW)) +
                        evaluateParametersByCompetitionType(new CompetitionParams(getTotalCentimetresForJumping(athleteResults.getPoleVault()),Constants.A_POLE_VAULT,Constants.B_POLE_VAULT,Constants.C_POLE_VAULT)) +
                        evaluateParametersByCompetitionType(new CompetitionParams(athleteResults.getJavelinThrow(),Constants.A_JAVELIN_THROW,Constants.B_JAVELIN_THROW,Constants.C_JAVELIN_THROW)) +
                        evaluateParametersByCompetitionType(new CompetitionParams(getTotalSecondsForRunning(athleteResults.getOneThousandFiveHundredMeters()),Constants.A_ONE_THOUSAND_FIVE_H_METERS,Constants.B_ONE_THOUSAND_FIVE_H_METERS,Constants.C_ONE_THOUSAND_FIVE_H_METERS));
        return (int) result;
    }

    private static double evaluateParametersByCompetitionType(CompetitionParams formValues){
          return evaluateResultTrackEvent.calculatePoints(formValues);
    }

    private static double getTotalSecondsForRunning(String timeValue){
        StringTokenizer timeTokens = new StringTokenizer(timeValue,".");
        return Integer.valueOf(timeTokens.nextToken())* Constants.SECONDS_UNIT +
                Double.valueOf(timeTokens.nextToken()+"."+timeTokens.nextToken());
    }

    private static double getTotalCentimetresForJumping(double value){
        return value*Constants.CENTIMETRES_UNIT;
    }
}
