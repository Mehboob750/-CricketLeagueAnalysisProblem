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
    public Map<String, IplDAO> iplMap = new HashMap<String, IplDAO>();

    public int loadIPLCSVFile(String csvFilePath) throws CricketLeagueAnalyserException {
        return this.loadIplData(csvFilePath,IPLRunSheetCSV.class);
    }

    public int loadIPLWicketsCSVFile(String csvFilePath) throws CricketLeagueAnalyserException {
        return this.loadIplData(csvFilePath,IPLWicketSheetCSV.class);
    }

    private  <E> int loadIplData(String csvFilePath,Class<E> iplCsvClass) throws CricketLeagueAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<E> csvIterator = icsvBuilder.getCSVFileIterator(reader, iplCsvClass);
            Iterable<E> csvIterable = () -> csvIterator;
            if (iplCsvClass.getName().equals("com.cricket.IPLRunSheetCSV")) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(IPLRunSheetCSV.class::cast)
                        .forEach(iplRunSheetCSV -> iplMap.put(iplRunSheetCSV.player, new IplDAO(iplRunSheetCSV)));
            } else if (iplCsvClass.getName().equals("com.cricket.IPLWicketSheetCSV")) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(IPLWicketSheetCSV.class::cast)
                        .forEach(iplWicketSheetCSV -> iplMap.put(iplWicketSheetCSV.player, new IplDAO(iplWicketSheetCSV)));
            }
            return iplMap.size();
        } catch (IOException | CSVBuilderException e) {
            throw new CricketLeagueAnalyserException(e.getMessage(), CricketLeagueAnalyserException.ExceptionType.CSV_FILE_PROBLEM);
        }
    }

        public String getBattingAverageWiseSorted() throws CricketLeagueAnalyserException {
        Comparator<IplDAO> iplCSVComparator =Comparator.comparing(average->average.average);
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

