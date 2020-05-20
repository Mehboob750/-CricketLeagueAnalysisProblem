package com.cricket;

import com.opencsv.bean.CsvBindByName;

public class IPLRunSheetCSV {
    @CsvBindByName(column = "PLAYER",required = true)
    public String player;

    @CsvBindByName(column = "Mat")
    public int match;

    @CsvBindByName(column = "Inns")
    public int innings;

    @CsvBindByName(column = "NO")
    public int notOut;

    @CsvBindByName(column = "Runs")
    public int runs;

    @CsvBindByName(column = "SR")
    public double strikeRate;

    @CsvBindByName(column = "Avg")
    public double average;

    @CsvBindByName(column = "100")
    public int centuries;

    @CsvBindByName(column = "50")
    public int halfCenturies;

    @CsvBindByName(column = "4s")
    public int fours;

    @CsvBindByName(column = "6s")
    public int sixes;

    @Override
    public String toString() {
        return "IPLRunsheetCSV{" +
                "player='" + player + '\'' +
                ", match=" + match +
                ", innings=" + innings +
                ", notOut=" + notOut +
                ", runs=" + runs +
                ", strikeRate=" + strikeRate +
                ", average=" + average +
                ", centuries=" + centuries +
                ", halfCenturies=" + halfCenturies +
                ", fours=" + fours +
                ", sixes=" + sixes +
                '}';
    }
}
