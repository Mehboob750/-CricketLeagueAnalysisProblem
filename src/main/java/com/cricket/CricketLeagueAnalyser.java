package com.cricket;

import censusanalyser.CSVBuilderException;
import censusanalyser.CSVBuilderFactory;
import censusanalyser.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class CricketLeagueAnalyser {
    public int loadIPLCSVFile(String csvFilePath) throws CricketLeagueAnalyserException {
        Map<String, IplRunSheetDAO> iplRunsheetMap = new HashMap<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IPLRunsheetCSV> csvIterator = icsvBuilder.getCSVFileIterator(reader, IPLRunsheetCSV.class);
            Iterable<IPLRunsheetCSV> csvIterable = () -> csvIterator;
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .forEach(iplRunsheetCSV -> iplRunsheetMap.put(iplRunsheetCSV.player, new IplRunSheetDAO(iplRunsheetCSV)));
            return iplRunsheetMap.size();
        } catch (IOException | CSVBuilderException e) {
            throw new CricketLeagueAnalyserException(e.getMessage(),CricketLeagueAnalyserException.ExceptionType.CSV_FILE_PROBLEM);
        }
    }
}
