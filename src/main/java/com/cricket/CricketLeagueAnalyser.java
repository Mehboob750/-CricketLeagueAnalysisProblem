package com.cricket;

import com.google.gson.Gson;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CricketLeagueAnalyser {
    Map<String, IplDAO> iplMap = new HashMap<>();

    public int loadIPLCSVFile(String... csvFilePath) throws CricketLeagueAnalyserException {
        iplMap=new IplLoader().loadIplData(IPLRunSheetCSV.class,csvFilePath);
        return iplMap.size();
    }

    public String getBattingAverageWiseSorted() throws CricketLeagueAnalyserException {
        Comparator<IplDAO> iplCSVComparator =Comparator.comparing(average->average.battingAverage);
        return getSortedIPLData(iplCSVComparator);
    }

    public String getStrikingRateWiseSorted() throws CricketLeagueAnalyserException {
        Comparator<IplDAO> iplCSVComparator =Comparator.comparing(average->average.strikeRate);
        return getSortedIPLData(iplCSVComparator);
    }

    public String getSixesWiseSorted() throws CricketLeagueAnalyserException {
        Comparator<IplDAO> iplCSVComparator =Comparator.comparing(average->average.sixes);
        return getSortedIPLData(iplCSVComparator);
    }

    public String getFoursWiseSorted() throws CricketLeagueAnalyserException {
        Comparator<IplDAO> iplCSVComparator =Comparator.comparing(average->average.fours);
        return getSortedIPLData(iplCSVComparator);
    }

    public String getSixAndFoursWiseSorted() throws CricketLeagueAnalyserException {
        Comparator<IplDAO> iplCSVComparatorForSixesAndFours =Comparator.comparing(average->average.sixesAndFours);
        return getSortedIPLData(iplCSVComparatorForSixesAndFours);
    }

    public String getSortedByStrikingRateWithMaximumSixesAndFours() throws CricketLeagueAnalyserException {
        Comparator<IplDAO> iplCSVComparatorStrikeRateWithSixAndFours =Comparator.comparing(average->average.strikeRateWithSixesAndFours);
        return getSortedIPLData(iplCSVComparatorStrikeRateWithSixAndFours);
    }

    public String getSortedByAverageWithStrikingRate() throws CricketLeagueAnalyserException {
        Comparator<IplDAO> iplCSVComparatorAverageWithStrikeRate =Comparator.comparing(average->average.averageWithStrikingRate);
        return getSortedIPLData(iplCSVComparatorAverageWithStrikeRate);
    }

    public String getSortedByRunsWithAverages() throws CricketLeagueAnalyserException {
        Comparator<IplDAO> iplCSVComparatorRunsWithAverages=Comparator.comparing(average->average.runsWithAverages);
        return getSortedIPLData(iplCSVComparatorRunsWithAverages);
    }

    public String getSortedByBowlingAverage() throws CricketLeagueAnalyserException {
        Comparator<IplDAO> iplCSVComparatorAverage=Comparator.comparing(average->average.bowlingAverage);
        return getSortedIPLData(iplCSVComparatorAverage);
    }

    public String getSortedByBowlingStrikeRate() throws CricketLeagueAnalyserException {
        Comparator<IplDAO> iplCSVComparatorStrikeRate=Comparator.comparing(average->average.strikeRate);
        return getSortedIPLData(iplCSVComparatorStrikeRate);
    }

    public String getSortedByEconomyRate() throws CricketLeagueAnalyserException {
        Comparator<IplDAO> iplCSVComparatorEconomyRate=Comparator.comparing(average->average.economyRate);
        return getSortedIPLData(iplCSVComparatorEconomyRate);
    }

    public String getSortedByStrikingRateWith5wand4w() throws CricketLeagueAnalyserException {
        Comparator<IplDAO> iplCSVComparatorStrikingRateWith5wAnd4w=Comparator.comparing(average->average.strikeRateWith5wAnd4w);
        return getSortedIPLData(iplCSVComparatorStrikingRateWith5wAnd4w);
    }

    public String getSortedByAveragesWithStrikingRate() throws CricketLeagueAnalyserException {
        Comparator<IplDAO>iplCSVComparatorAverage=Comparator.comparing(average->average.bowlingAverage);
        Comparator<IplDAO>iplCSVComparatorStrikeRate=iplCSVComparatorAverage.thenComparing(average->average.strikeRate);
        return getSortedIPLData(iplCSVComparatorStrikeRate);
    }

    public String getSortedByWicketsWithBowlingAverage() throws CricketLeagueAnalyserException {
        Comparator<IplDAO>iplCSVComparatorWickets=Comparator.comparing(average->average.wickets);
        Comparator<IplDAO>iplCSVComparatorAverage=iplCSVComparatorWickets.thenComparing(average->average.bowlingAverage);
        return getSortedIPLData(iplCSVComparatorAverage);
    }

    public String getSortedByBattingAndBowlingAverage() throws CricketLeagueAnalyserException {
        Comparator<IplDAO> iplCSVComparatorBattingAverage=Comparator.comparing(average->average.battingAverage);
        Comparator<IplDAO> iplCSVComparatorBowlingAverage=iplCSVComparatorBattingAverage.thenComparing(average->average.bowlingAverage);
        return getSortedIPLData(iplCSVComparatorBowlingAverage);
    }

    public String getSortedIPLData(Comparator<IplDAO> iplCSVComparator) throws CricketLeagueAnalyserException {
        if (iplMap == null || iplMap.size() == 0) {
            throw new CricketLeagueAnalyserException("Data Not Found", CricketLeagueAnalyserException.ExceptionType.DATA_NOT_FOUND);
        }
            List sortedData = iplMap.values()
                    .stream()
                    .sorted(iplCSVComparator)
                    .collect(Collectors.toList());
            String sortedDataInJson = new Gson().toJson(sortedData);
            return sortedDataInJson;
    }

}

