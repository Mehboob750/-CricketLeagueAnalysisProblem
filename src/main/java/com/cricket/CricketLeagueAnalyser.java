package com.cricket;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CricketLeagueAnalyser {
    Map<String, IplDAO> iplMap =null;
    Map<Enum.Fields,Comparator<IplDAO>> sortingMap=null;
    public CricketLeagueAnalyser() {
        this.iplMap = new HashMap<String, IplDAO>();
        this.sortingMap=new HashMap<>();
        this.sortingMap.put(Enum.Fields.BATTING_AVERAGE,Comparator.comparing(average->average.battingAverage));
        this.sortingMap.put(Enum.Fields.STRIKE_RATE,Comparator.comparing(average->average.strikeRate));
        this.sortingMap.put(Enum.Fields.SIXES,Comparator.comparing(average->average.sixes));
        this.sortingMap.put(Enum.Fields.FOURS,Comparator.comparing(average->average.fours));
        this.sortingMap.put(Enum.Fields.SIXES_AND_FOURS,Comparator.comparing(average->average.sixesAndFours));
        this.sortingMap.put(Enum.Fields.STRIKERATE_WITH_SIXES_AND_FOURS,Comparator.comparing(average->average.strikeRateWithSixesAndFours));
        this.sortingMap.put(Enum.Fields.AVERAGE_WITH_STRIKERATE,Comparator.comparing(average->average.averageWithStrikingRate));
        this.sortingMap.put(Enum.Fields.RUNS_WITH_AVERAGE,Comparator.comparing(average->average.runsWithAverages));
        this.sortingMap.put(Enum.Fields.BOWLING_AVERAGE,Comparator.comparing(average->average.bowlingAverage));
        this.sortingMap.put(Enum.Fields.BOWLING_STRIKERATE,Comparator.comparing(average->average.bowlingStrikeRate));
        this.sortingMap.put(Enum.Fields.BOWLING_AVERAGE_WITH_STRIKERATE,Comparator.comparing(average->average.bowlingAverageWithStrikeRate));
        this.sortingMap.put(Enum.Fields.ECONOMY_RATE,Comparator.comparing(average->average.economyRate));
        this.sortingMap.put(Enum.Fields.STRIKERATE_WITH_SIXES_AND_FOURS,Comparator.comparing(average->average.strikeRateWith5wAnd4w));
        Comparator<IplDAO>comparatorComparatorWickets=Comparator.comparing(average->average.wickets);
        this.sortingMap.put(Enum.Fields.AVERAGE_WITH_WICKETS,comparatorComparatorWickets.thenComparing(average->average.bowlingAverage));
        Comparator<IplDAO>comparatorBattingAverage=Comparator.comparing(average->average.battingAverage);
        this.sortingMap.put(Enum.Fields.BOWLING_AVERAGE_AND_BATTING_AVERAGE,comparatorBattingAverage.thenComparing(average->average.bowlingAverage));
        Comparator<IplDAO>comparatorBattingRuns=Comparator.comparing(average->average.battingRuns);
        this.sortingMap.put(Enum.Fields.BATTING_RUNS_AND_WICKETS,comparatorBattingRuns.thenComparing(average->average.wickets));
    }

    public enum Cricket{Batting,Bowling};

    public int loadIPLCSVFile(Cricket cricket, String... csvFilePath) throws CricketLeagueAnalyserException {
        iplMap=new IplAdapterFactory().loadIplData(cricket,csvFilePath);
        return iplMap.size();
    }

    public String getSortedIPLData(Enum.Fields field) throws CricketLeagueAnalyserException {
        if (iplMap == null || iplMap.size() == 0) {
            throw new CricketLeagueAnalyserException("Data Not Found", CricketLeagueAnalyserException.ExceptionType.DATA_NOT_FOUND);
        }
            ArrayList sortedData = iplMap.values()
                    .stream()
                    .sorted(sortingMap.get(field))
                    .collect(Collectors.toCollection(ArrayList::new));
            String sortedDataInJson = new Gson().toJson(sortedData);
            return sortedDataInJson;
    }

}

