package com.kuenag.app.domain;

import com.kuenag.app.model.CompetitionParams;
import com.kuenag.app.model.DecathlonResult;
import com.kuenag.app.utils.Constants;

import java.util.StringTokenizer;

/**
 * Static Class that contains the decathlon formula operations
 *
 * @author Alvaro Andres Cruz Burbano
 */
public class DecathlonFormula {

    /**
     * Lambda expression to calculate the formula in track event
     */
    private static Calculator evaluateResultTrackEvent = (formValues) -> (int) (formValues.getParameterA() * (Math.pow(Math.abs((formValues.getParameterB() - formValues.getAthleteResult())), formValues.getParameterC())));

    public static int evaluateDecathlonFormula(DecathlonResult athleteResults){
        double result =
                        evaluateParametersByCompetitionType(new CompetitionParams(athleteResults.getOneHundredMeters(),Constants.A_ONE_HUNDRED_METERS,Constants.B_ONE_HUNDRED_METERS,Constants.C_ONE_HUNDRED_METERS)) +
                        evaluateParametersByCompetitionType(new CompetitionParams(calculateTotalCentimetresForJumpingCompetition(athleteResults.getLongJump()),Constants.A_LONG_JUMP,Constants.B_LONG_JUMP,Constants.C_LONG_JUMP)) +
                        evaluateParametersByCompetitionType(new CompetitionParams(athleteResults.getShotPut(),Constants.A_SHOT_PUT,Constants.B_SHOT_PUT,Constants.C_SHOT_PUT)) +
                        evaluateParametersByCompetitionType(new CompetitionParams(calculateTotalCentimetresForJumpingCompetition(athleteResults.getHighJump()),Constants.A_HIGH_JUMP,Constants.B_HIGH_JUMP,Constants.C_HIGH_JUMP)) +
                        evaluateParametersByCompetitionType(new CompetitionParams(athleteResults.getFourHundredMeters(),Constants.A_FOUR_HUNDRED_METERS,Constants.B_FOUR_HUNDRED_METERS,Constants.C_FOUR_HUNDRED_METERS)) +
                        evaluateParametersByCompetitionType(new CompetitionParams(athleteResults.getOneHundredTenMetersHurdles(),Constants.A_ONE_HUNDRED_METERS_HURDLES,Constants.B_ONE_HUNDRED_METERS_HURDLES,Constants.C_ONE_HUNDRED_METERS_HURDLES)) +
                        evaluateParametersByCompetitionType(new CompetitionParams(athleteResults.getDiscusThrow(),Constants.A_DISCUS_THROW,Constants.B_DISCUS_THROW,Constants.C_DISCUS_THROW)) +
                        evaluateParametersByCompetitionType(new CompetitionParams(calculateTotalCentimetresForJumpingCompetition(athleteResults.getPoleVault()),Constants.A_POLE_VAULT,Constants.B_POLE_VAULT,Constants.C_POLE_VAULT)) +
                        evaluateParametersByCompetitionType(new CompetitionParams(athleteResults.getJavelinThrow(),Constants.A_JAVELIN_THROW,Constants.B_JAVELIN_THROW,Constants.C_JAVELIN_THROW)) +
                        evaluateParametersByCompetitionType(new CompetitionParams(calculateTotalSecondsForRunningCompetition(athleteResults.getOneThousandFiveHundredMeters()),Constants.A_ONE_THOUSAND_FIVE_H_METERS,Constants.B_ONE_THOUSAND_FIVE_H_METERS,Constants.C_ONE_THOUSAND_FIVE_H_METERS));
        return (int) result;
    }

    public static double evaluateParametersByCompetitionType(CompetitionParams formValues){
          return evaluateResultTrackEvent.calculatePoints(formValues);
    }

    public static double calculateTotalSecondsForRunningCompetition(String timeValue){
        StringTokenizer timeTokens = new StringTokenizer(timeValue,".");
        return Integer.valueOf(timeTokens.nextToken())* Constants.SECONDS_UNIT +
                Double.valueOf(timeTokens.nextToken()+"."+timeTokens.nextToken());
    }

    public static double calculateTotalCentimetresForJumpingCompetition(double value){
        return value*Constants.CENTIMETRES_UNIT;
    }
}
