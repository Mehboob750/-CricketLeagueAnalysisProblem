package com.cricket;

public class IplDAO {

    public String player;
    public int innings;
    public int match;
    public int runs;
    public double average;
    public double strikeRate;
    public int fours;
    public int sixes;
    public double sixesAndFours;
    public double strikeRateWithSixesAndFours;
    public double averageWithStrikingRate;
    public double runsWithAverages;

    public double over;
    public int wickets;
    public double bestBowlingIn;
    public double economyRate;
    public int fourWickets;
    public int fiveWickets;

    public IplDAO() {
    }

    public IplDAO(IPLRunSheetCSV iplRunsheetCSV) {
        this.player = iplRunsheetCSV.player;
        this.runs = iplRunsheetCSV.runs;
        this.average=iplRunsheetCSV.average;
        this.strikeRate = iplRunsheetCSV.strikeRate;
        this.fours = iplRunsheetCSV.fours;
        this.sixes = iplRunsheetCSV.sixes;
        this.sixesAndFours=iplRunsheetCSV.sixes*iplRunsheetCSV.fours;
        this.strikeRateWithSixesAndFours=iplRunsheetCSV.strikeRate*iplRunsheetCSV.sixes*iplRunsheetCSV.fours;
        this.averageWithStrikingRate=iplRunsheetCSV.average*iplRunsheetCSV.strikeRate;
        this.runsWithAverages=iplRunsheetCSV.runs*iplRunsheetCSV.average;
    }

    public IplDAO(IPLWicketSheetCSV iplWicketSheetCSV) {
        this.player = iplWicketSheetCSV.player;
        this.match = iplWicketSheetCSV.match;
        this.innings=iplWicketSheetCSV.innings;
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
