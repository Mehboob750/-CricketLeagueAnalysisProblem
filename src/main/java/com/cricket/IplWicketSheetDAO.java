package com.cricket;

public class IplWicketSheetDAO {
    public String player;
    public int match;
    public int innings;
    public double over;
    public int runs;
    public int wickets;
    public double bestBowlingIn;
    public double average;
    public double economyRate;
    public double strikeRate;
    public int fourWickets;
    public int fiveWickets;

    public IplWicketSheetDAO() {
    }

    public IplWicketSheetDAO(IPLWicketSheetCSV iplWicketSheetCSV) {
        this.player = iplWicketSheetCSV.player;
        this.match = iplWicketSheetCSV.match;
        this.innings = iplWicketSheetCSV.innings;
        this.over = iplWicketSheetCSV.over;
        this.runs = iplWicketSheetCSV.runs;
        this.wickets = iplWicketSheetCSV.wickets;
        this.bestBowlingIn = iplWicketSheetCSV.bestBowlingIn;
        this.average = iplWicketSheetCSV.average;
        this.economyRate = iplWicketSheetCSV.economyRate;
        this.strikeRate = iplWicketSheetCSV.strikeRate;
        this.fourWickets = iplWicketSheetCSV.fourWickets;
        this.fiveWickets = iplWicketSheetCSV.fiveWickets;
    }
}
