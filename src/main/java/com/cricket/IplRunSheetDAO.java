package com.cricket;

public class IplRunSheetDAO {
    public String player;
    public int match;
    public int innings;
    public int notOut;
    public int runs;
    public double average;
    public double strikeRate;
    public int centuries;
    public int halfCenturies;
    public int fours;
    public int sixes;
    public double sixesAndFours;
    public double strikeRateWithSixesAndFours;
    public double averageWithStrikingRate;
    public double runsWithAverages;



    public IplRunSheetDAO() {
    }

    public IplRunSheetDAO(IPLRunsheetCSV iplRunsheetCSV) {
        this.player = iplRunsheetCSV.player;
        this.match = iplRunsheetCSV.match;
        this.innings = iplRunsheetCSV.innings;
        this.notOut = iplRunsheetCSV.notOut;
        this.runs = iplRunsheetCSV.runs;
        this.average=iplRunsheetCSV.average;
        this.strikeRate = iplRunsheetCSV.strikeRate;
        this.centuries = iplRunsheetCSV.centuries;
        this.halfCenturies = iplRunsheetCSV.halfCenturies;
        this.fours = iplRunsheetCSV.fours;
        this.sixes = iplRunsheetCSV.sixes;
        this.sixesAndFours=iplRunsheetCSV.sixes*iplRunsheetCSV.fours;
        this.strikeRateWithSixesAndFours=iplRunsheetCSV.strikeRate*iplRunsheetCSV.sixes*iplRunsheetCSV.fours;
        this.averageWithStrikingRate=iplRunsheetCSV.average*iplRunsheetCSV.strikeRate;
        this.runsWithAverages=iplRunsheetCSV.runs*iplRunsheetCSV.average;
    }
}
