package com.cricket;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CricketLeagueAnalysisTest {
    private static final String IPL_2019_RUNS_FILE_PATH="./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IPL2019FactsheetMostRuns.csv";
    private static final String INCORRECT_FILE_TYPE_PATH ="./src/main/resources/IPL2019FactsheetMostRuns.MP4";


    @Test
    public void  givenIpl2019MostRunsCSVFile_ReturnsCorrectRecords() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            int numberOfRecords = cricketLeagueAnalyser.loadIPLCSVFile(IPL_2019_RUNS_FILE_PATH);
            Assert.assertEquals(100,numberOfRecords);
        } catch (CricketLeagueAnalyserException e) { }
    }

    @Test
    public void givenIpl2019MostRunsCSVFile_WithWrongFile_ShouldThrowException() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CricketLeagueAnalyserException.class);
            cricketLeagueAnalyser.loadIPLCSVFile(WRONG_CSV_FILE_PATH);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.CSV_FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenIpl2019MostRunsCSVFile_WithTypeIncorrect_ShouldThrowException() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CricketLeagueAnalyserException.class);
            cricketLeagueAnalyser.loadIPLCSVFile(INCORRECT_FILE_TYPE_PATH);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.CSV_FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenIpl2019MostRunsCSVFile_WithIncorrectDelimeter_ShouldThrowException() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSVFile(IPL_2019_RUNS_FILE_PATH);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.CSV_FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenIpl2019MostRunsCSVFile_WithIncorrectHeader_ShouldThrowException() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSVFile(IPL_2019_RUNS_FILE_PATH);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.CSV_FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenIPL2019MostRunsCSVFile_ShouldReturnPlayerName_WhoHasMaximumAverageBattingScore() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSVFile(IPL_2019_RUNS_FILE_PATH);
            String sortedIPLData=cricketLeagueAnalyser.getBattingAverageWiseSorted();
            IPLRunsheetCSV[] iplRunsheetCSV = new Gson().fromJson(sortedIPLData, IPLRunsheetCSV[].class);
            Assert.assertEquals("MS Dhoni", iplRunsheetCSV[iplRunsheetCSV.length-1].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019MostRunsCSVFile_ShouldReturnPlayerName_WhoHasMaximumStrikingRate() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSVFile(IPL_2019_RUNS_FILE_PATH);
            String sortedIPLData=cricketLeagueAnalyser.getStrikingRateWiseSorted();
            IPLRunsheetCSV[] iplRunsheetCSV = new Gson().fromJson(sortedIPLData, IPLRunsheetCSV[].class);
            Assert.assertEquals("Ishant Sharma", iplRunsheetCSV[iplRunsheetCSV.length-1].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019MostRunsCSVFile_ShouldReturnPlayerName_WhoHitMaximumSixes() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSVFile(IPL_2019_RUNS_FILE_PATH);
            String sortedIPLData=cricketLeagueAnalyser.getSixesWiseSorted();
            IPLRunsheetCSV[] iplRunsheetCSV = new Gson().fromJson(sortedIPLData, IPLRunsheetCSV[].class);
            Assert.assertEquals("Andre Russell", iplRunsheetCSV[iplRunsheetCSV.length-1].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019MostRunsCSVFile_ShouldReturnPlayerName_WhoHitMaximumFours() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSVFile(IPL_2019_RUNS_FILE_PATH);
            String sortedIPLData=cricketLeagueAnalyser.getFoursWiseSorted();
            IPLRunsheetCSV[] iplRunsheetCSV = new Gson().fromJson(sortedIPLData, IPLRunsheetCSV[].class);
            Assert.assertEquals("Shikhar Dhawan", iplRunsheetCSV[iplRunsheetCSV.length-1].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019MostRunsCSVFile_ShouldReturnPlayerName_WhoHitMaximumSixesAndFours() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSVFile(IPL_2019_RUNS_FILE_PATH);
            String sortedIPLData=cricketLeagueAnalyser.getSixAndFoursWiseSorted();
            IPLRunsheetCSV[] iplRunsheetCSV = new Gson().fromJson(sortedIPLData, IPLRunsheetCSV[].class);
            Assert.assertEquals("Andre Russell", iplRunsheetCSV[iplRunsheetCSV.length-1].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019MostRunsCSVFile_ShouldReturnPlayerName_WhoHadBestStrikingRateWithMaximumSixAndFours() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSVFile(IPL_2019_RUNS_FILE_PATH);
            String sortedIPLData=cricketLeagueAnalyser.getSortedByStrikingRateWithMaximumSixesAndFours();
            IPLRunsheetCSV[] iplRunsheetCSV = new Gson().fromJson(sortedIPLData, IPLRunsheetCSV[].class);
            Assert.assertEquals("Andre Russell", iplRunsheetCSV[iplRunsheetCSV.length-1].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

}
