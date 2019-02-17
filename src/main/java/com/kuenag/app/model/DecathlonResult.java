package com.kuenag.app.model;

public class DecathlonResult {

    private String athleteName;
    private double longJump;
    private double shotPut;
    private double highJump;
    private double discusThrow;
    private double poleVault;
    private double javelinThrow;
    private double oneHundredMeters;
    private double fourHundredMeters;
    private double oneHundredTenMetersHurdles;
    private String oneThousandFiveHundredMeters;
    private int totalPoints;
    private String position;

    public String getAthleteName() {
        return athleteName;
    }

    public void setAthleteName(String athleteName) {
        this.athleteName = athleteName;
    }

    public double getLongJump() {
        return longJump;
    }

    public void setLongJump(double longJump) {
        this.longJump = longJump;
    }

    public double getShotPut() {
        return shotPut;
    }

    public void setShotPut(double shotPut) {
        this.shotPut = shotPut;
    }

    public double getHighJump() {
        return highJump;
    }

    public void setHighJump(double highJump) {
        this.highJump = highJump;
    }

    public double getDiscusThrow() {
        return discusThrow;
    }

    public void setDiscusThrow(double discusThrow) {
        this.discusThrow = discusThrow;
    }

    public double getPoleVault() {
        return poleVault;
    }

    public void setPoleVault(double poleVault) {
        this.poleVault = poleVault;
    }

    public double getJavelinThrow() {
        return javelinThrow;
    }

    public void setJavelinThrow(double javelinThrow) {this.javelinThrow = javelinThrow;}

    public double getOneHundredMeters() {
        return oneHundredMeters;
    }

    public void setOneHundredMeters(double oneHundredMeters) {
        this.oneHundredMeters = oneHundredMeters;
    }

    public double getFourHundredMeters() {
        return fourHundredMeters;
    }

    public void setFourHundredMeters(double fourHundredMeters) {
        this.fourHundredMeters = fourHundredMeters;
    }

    public double getOneHundredTenMetersHurdles() {
        return oneHundredTenMetersHurdles;
    }

    public int getTotalPoints() { return totalPoints; }

    public void setTotalPoints(int totalPoints) { this.totalPoints = totalPoints; }

    public String getPosition() { return position; }

    public void setPosition(String position) { this.position = position; }

    public void setOneHundredTenMetersHurdles(double oneHundredTenMetersHurdles) {
        this.oneHundredTenMetersHurdles = oneHundredTenMetersHurdles;
    }

    public String getOneThousandFiveHundredMeters() {
        return oneThousandFiveHundredMeters;
    }

    public void setOneThousandFiveHundredMeters(String oneThousandFiveHundredMeters) {
        this.oneThousandFiveHundredMeters = oneThousandFiveHundredMeters;
    }

}
