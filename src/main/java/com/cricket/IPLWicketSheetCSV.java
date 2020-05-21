package com.cricket;

import com.opencsv.bean.CsvBindByName;

public class IPLWicketSheetCSV {
    @CsvBindByName(column = "PLAYER",required = true)
    public String player;

    @CsvBindByName(column = "Mat")
    public int match;

    @CsvBindByName(column = "Inns")
    public int innings;

    @CsvBindByName(column = "Ov")
    public double over;

    @CsvBindByName(column = "Runs")
    public int runs;

    @CsvBindByName(column = "Wkts")
    public int wickets;

    @CsvBindByName(column = "BBI")
    public double bestBowlingIn;

    @CsvBindByName(column = "Avg",required = true)
    public double average;

    @CsvBindByName(column = "Econ")
    public double economyRate;

    @CsvBindByName(column = "SR")
    public double strikeRate;

    @CsvBindByName(column = "4w")
    public int fourWickets;

    @CsvBindByName(column = "5w")
    public int fiveWickets;

    @Override
    public String toString() {
        return "IPLWicketSheetCSV{" +
                "player='" + player + '\'' +
                ", match=" + match +
                ", innings=" + innings +
                ", over=" + over +
                ", runs=" + runs +
                ", wickets=" + wickets +
                ", bestBowlingIn=" + bestBowlingIn +
                ", average=" + average +
                ", economyRate=" + economyRate +
                ", strikeRate=" + strikeRate +
                ", fourWickets=" + fourWickets +
                ", fiveWickets=" + fiveWickets +
                '}';
    }
}
