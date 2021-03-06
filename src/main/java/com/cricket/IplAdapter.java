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

public abstract class IplAdapter {
    public abstract Map<String, IplDAO> loadIplData(String... csvFilePath) throws CricketLeagueAnalyserException;

    <E> Map<String, IplDAO> loadIplData(Class<E> iplCsvClass, String... csvFilePath) throws CricketLeagueAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath[0]))) {
            Map<String, IplDAO> iplMap = new HashMap<>();
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
            return iplMap;
        } catch (IOException | CSVBuilderException e) {
            throw new CricketLeagueAnalyserException(e.getMessage(), CricketLeagueAnalyserException.ExceptionType.CSV_FILE_PROBLEM);
        }
    }
}
