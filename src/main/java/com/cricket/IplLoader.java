package com.cricket;

import com.opencsv.CSVBuilderException;
import com.opencsv.CSVBuilderFactory;
import com.opencsv.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class IplLoader {
    Map<String, IplDAO> iplMap = new HashMap<>();
    public <E> Map<String, IplDAO> loadIplData(Class<E> iplCsvClass, String... csvFilePath) throws CricketLeagueAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath[0]))) {
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
            if(csvFilePath.length==1)
                return iplMap;
            this.loadIPLWicketsCSVFile(iplMap, csvFilePath[1]);
            return iplMap;
        } catch (IOException | CSVBuilderException e) {
            throw new CricketLeagueAnalyserException(e.getMessage(), CricketLeagueAnalyserException.ExceptionType.CSV_FILE_PROBLEM);
        }
    }

    public int loadIPLWicketsCSVFile(Map<String, IplDAO> iplMap, String csvFilePath) throws CricketLeagueAnalyserException {
        this.iplMap =this.loadIplData(IPLWicketSheetCSV.class,csvFilePath);
        return this.iplMap.size();
    }
}
