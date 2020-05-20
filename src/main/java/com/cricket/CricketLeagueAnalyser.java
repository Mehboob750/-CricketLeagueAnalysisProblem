package com.cricket;

import censusanalyser.*;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CricketLeagueAnalyser {
    Map<String, IplRunSheetDAO> iplRunSheetMap =null;
    public int loadIPLCSVFile(String csvFilePath) throws CricketLeagueAnalyserException {
        iplRunSheetMap = new HashMap<String, IplRunSheetDAO>();
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IPLRunsheetCSV> csvIterator = icsvBuilder.getCSVFileIterator(reader, IPLRunsheetCSV.class);
            Iterable<IPLRunsheetCSV> csvIterable = () -> csvIterator;
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .forEach(iplRunsheetCSV -> iplRunSheetMap.put(iplRunsheetCSV.player, new IplRunSheetDAO(iplRunsheetCSV)));
            return iplRunSheetMap.size();
        } catch (IOException | CSVBuilderException e) {
            throw new CricketLeagueAnalyserException(e.getMessage(),CricketLeagueAnalyserException.ExceptionType.CSV_FILE_PROBLEM);
        }
    }

    public int loadIPLWicketsCSVFile(String csvFilePath) throws CricketLeagueAnalyserException {
         Map<String, IplWicketSheetDAO> iplWicketSheetMap = new HashMap<String, IplWicketSheetDAO>();
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IPLWicketSheetCSV> csvIterator = icsvBuilder.getCSVFileIterator(reader, IPLWicketSheetCSV.class);
            Iterable<IPLWicketSheetCSV> csvIterable = () -> csvIterator;
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .forEach(iplWicketSheetCSV  -> iplWicketSheetMap.put(iplWicketSheetCSV.player, new IplWicketSheetDAO(iplWicketSheetCSV)));
            return iplWicketSheetMap.size();
        } catch (IOException | CSVBuilderException e) {
            throw new CricketLeagueAnalyserException(e.getMessage(),CricketLeagueAnalyserException.ExceptionType.CSV_FILE_PROBLEM);
        }
    }

    public String getBattingAverageWiseSorted() throws CricketLeagueAnalyserException {
        Comparator<IplRunSheetDAO> iplCSVComparator =Comparator.comparing(average->average.average);
        return getSortedIPLData(iplCSVComparator);
    }

    public String getStrikingRateWiseSorted() throws CricketLeagueAnalyserException {
        Comparator<IplRunSheetDAO> iplCSVComparator =Comparator.comparing(average->average.strikeRate);
        return getSortedIPLData(iplCSVComparator);
    }

    public String getSixesWiseSorted() throws CricketLeagueAnalyserException {
        Comparator<IplRunSheetDAO> iplCSVComparator =Comparator.comparing(average->average.sixes);
        return getSortedIPLData(iplCSVComparator);
    }

    public String getFoursWiseSorted() throws CricketLeagueAnalyserException {
        Comparator<IplRunSheetDAO> iplCSVComparator =Comparator.comparing(average->average.fours);
        return getSortedIPLData(iplCSVComparator);
    }

    public String getSixAndFoursWiseSorted() throws CricketLeagueAnalyserException {
        Comparator<IplRunSheetDAO> iplCSVComparatorForSixesAndFours =Comparator.comparing(average->average.sixesAndFours);
        return getSortedIPLData(iplCSVComparatorForSixesAndFours);
    }

    public String getSortedByStrikingRateWithMaximumSixesAndFours() throws CricketLeagueAnalyserException {
        Comparator<IplRunSheetDAO> iplCSVComparatorStrikeRateWithSixAndFours =Comparator.comparing(average->average.strikeRateWithSixesAndFours);
        return getSortedIPLData(iplCSVComparatorStrikeRateWithSixAndFours);
    }

    public String getSortedByAverageWithStrikingRate() throws CricketLeagueAnalyserException {
        Comparator<IplRunSheetDAO> iplCSVComparatorAverageWithStrikeRate =Comparator.comparing(average->average.averageWithStrikingRate);
        return getSortedIPLData(iplCSVComparatorAverageWithStrikeRate);
    }

    public String getSortedByRunsWithAverages() throws CricketLeagueAnalyserException {
        Comparator<IplRunSheetDAO> iplCSVComparatorRunsWithAverages=Comparator.comparing(average->average.runsWithAverages);
        return getSortedIPLData(iplCSVComparatorRunsWithAverages);
    }

    public String getSortedIPLData(Comparator<IplRunSheetDAO> iplCSVComparator) throws CricketLeagueAnalyserException {
        if (iplRunSheetMap == null || iplRunSheetMap.size() == 0) {
            throw new CricketLeagueAnalyserException("Data Not Found", CricketLeagueAnalyserException.ExceptionType.DATA_NOT_FOUND);
        }
            List sortedData = iplRunSheetMap.values()
                    .stream()
                    .sorted(iplCSVComparator)
                    .collect(Collectors.toList());
            String sortedDataInJson = new Gson().toJson(sortedData);
            return sortedDataInJson;
    }

}

