package com.cricket;

public class IplDAO {
    public String player;
    public int innings;
    public int match;
    public int battingRuns;
    public double battingAverage;
    public double strikeRate;
    public int fours;
    public int sixes;
    public double sixesAndFours;
    public double strikeRateWithSixesAndFours;
    public double averageWithStrikingRate;
    public double runsWithAverages;

    public double bowlingAverage;
    public double over;
    public int wickets;
    public double bowlingStrikeRate;
    public double bestBowlingIn;
    public double economyRate;
    public int fourWickets;
    public int fiveWickets;
    public double bowlingAverageWithStrikeRate;
    public double strikeRateWith5wAnd4w;
    public double wicketsWithBowlerAverage;

    public IplDAO() {
    }

    public IplDAO(IPLRunSheetCSV iplRunsheetCSV) {
        this.player = iplRunsheetCSV.player;
        this.battingRuns = iplRunsheetCSV.runs;
        this.battingAverage=iplRunsheetCSV.average;
        this.strikeRate = iplRunsheetCSV.strikeRate;
        this.fours = iplRunsheetCSV.fours;
        this.sixes = iplRunsheetCSV.sixes;
        this.sixesAndFours=iplRunsheetCSV.sixes*iplRunsheetCSV.fours;
        this.strikeRateWithSixesAndFours=iplRunsheetCSV.strikeRate+(iplRunsheetCSV.sixes*iplRunsheetCSV.fours);
        this.averageWithStrikingRate=iplRunsheetCSV.average*iplRunsheetCSV.strikeRate;
        this.runsWithAverages=iplRunsheetCSV.runs*iplRunsheetCSV.average;
    }

    public IplDAO(IPLWicketSheetCSV iplWicketSheetCSV) {
        this.player = iplWicketSheetCSV.player;
        this.match = iplWicketSheetCSV.match;
        this.innings=iplWicketSheetCSV.innings;
        this.over = iplWicketSheetCSV.over;
        this.wickets = iplWicketSheetCSV.wickets;
        this.bestBowlingIn = iplWicketSheetCSV.bestBowlingIn;
        this.bowlingAverage = iplWicketSheetCSV.average;
        this.economyRate = iplWicketSheetCSV.economyRate;
        this.bowlingStrikeRate =iplWicketSheetCSV.strikeRate;
        this.fourWickets = iplWicketSheetCSV.fourWickets;
        this.fiveWickets = iplWicketSheetCSV.fiveWickets;
        this.bowlingAverageWithStrikeRate=iplWicketSheetCSV.average*iplWicketSheetCSV.strikeRate;
        this.strikeRateWith5wAnd4w=iplWicketSheetCSV.strikeRate*iplWicketSheetCSV.fourWickets*iplWicketSheetCSV.fiveWickets;
        this.wicketsWithBowlerAverage=iplWicketSheetCSV.wickets*iplWicketSheetCSV.average;
    }
}
