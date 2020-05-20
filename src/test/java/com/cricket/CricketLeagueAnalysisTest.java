package com.cricket;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CricketLeagueAnalysisTest {
    private static final String IPL_2019_RUNS_FILE_PATH="./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IPL2019FactsheetMostRuns.csv";
    private static final String INCORRECT_FILE_TYPE_PATH ="./src/main/resources/IPL2019FactsheetMostRuns.MP4";
    private static final String IPL_2019_WICKETS_FILE_PATH="./src/test/resources/IPL2019FactsheetMostWkts.csv";

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
            IPLRunSheetCSV[] iplRunSheetCSV = new Gson().fromJson(sortedIPLData, IPLRunSheetCSV[].class);
            Assert.assertEquals("MS Dhoni", iplRunSheetCSV[iplRunSheetCSV.length-1].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019MostRunsCSVFile_ShouldReturnPlayerName_WhoHasMinimumAverageBattingScore() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSVFile(IPL_2019_RUNS_FILE_PATH);
            String sortedIPLData=cricketLeagueAnalyser.getBattingAverageWiseSorted();
            IPLRunSheetCSV[] iplRunSheetCSV = new Gson().fromJson(sortedIPLData, IPLRunSheetCSV[].class);
            Assert.assertEquals("Ishant Sharma", iplRunSheetCSV[0].player);
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
            IPLRunSheetCSV[] iplRunSheetCSV = new Gson().fromJson(sortedIPLData, IPLRunSheetCSV[].class);
            Assert.assertEquals("Ishant Sharma", iplRunSheetCSV[iplRunSheetCSV.length-1].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019MostRunsCSVFile_ShouldReturnPlayerName_WhoHasMinimumStrikingRate() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSVFile(IPL_2019_RUNS_FILE_PATH);
            String sortedIPLData=cricketLeagueAnalyser.getStrikingRateWiseSorted();
            IPLRunSheetCSV[] iplRunSheetCSV = new Gson().fromJson(sortedIPLData, IPLRunSheetCSV[].class);
            Assert.assertEquals("Bhuvneshwar Kumar", iplRunSheetCSV[0].player);
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
            IPLRunSheetCSV[] iplRunSheetCSV = new Gson().fromJson(sortedIPLData, IPLRunSheetCSV[].class);
            Assert.assertEquals("Andre Russell", iplRunSheetCSV[iplRunSheetCSV.length-1].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019MostRunsCSVFile_ShouldReturnPlayerName_WhoHitMinimumSixes() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSVFile(IPL_2019_RUNS_FILE_PATH);
            String sortedIPLData=cricketLeagueAnalyser.getSixesWiseSorted();
            IPLRunSheetCSV[] iplRunSheetCSV = new Gson().fromJson(sortedIPLData, IPLRunSheetCSV[].class);
            Assert.assertEquals("Kuldeep Yadav", iplRunSheetCSV[0].player);
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
            IPLRunSheetCSV[] iplRunSheetCSV = new Gson().fromJson(sortedIPLData, IPLRunSheetCSV[].class);
            Assert.assertEquals("Shikhar Dhawan", iplRunSheetCSV[iplRunSheetCSV.length-1].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019MostRunsCSVFile_ShouldReturnPlayerName_WhoHitMinimumFours() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSVFile(IPL_2019_RUNS_FILE_PATH);
            String sortedIPLData=cricketLeagueAnalyser.getFoursWiseSorted();
            IPLRunSheetCSV[] iplRunSheetCSV = new Gson().fromJson(sortedIPLData, IPLRunSheetCSV[].class);
            Assert.assertEquals("Tim Southee", iplRunSheetCSV[0].player);
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
            IPLRunSheetCSV[] iplRunSheetCSV = new Gson().fromJson(sortedIPLData, IPLRunSheetCSV[].class);
            Assert.assertEquals("Andre Russell", iplRunSheetCSV[iplRunSheetCSV.length-1].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019MostRunsCSVFile_ShouldReturnPlayerName_WhoHitMinimumSixesAndFours() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSVFile(IPL_2019_RUNS_FILE_PATH);
            String sortedIPLData=cricketLeagueAnalyser.getSixAndFoursWiseSorted();
            IPLRunSheetCSV[] iplRunSheetCSV = new Gson().fromJson(sortedIPLData, IPLRunSheetCSV[].class);
            Assert.assertEquals("Kuldeep Yadav", iplRunSheetCSV[0].player);
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
            IPLRunSheetCSV[] iplRunSheetCSV = new Gson().fromJson(sortedIPLData, IPLRunSheetCSV[].class);
            Assert.assertEquals("Andre Russell", iplRunSheetCSV[iplRunSheetCSV.length-1].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019MostRunsCSVFile_ShouldReturnPlayerName_WhoHadGreatAverageWithBestStrikingRate() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSVFile(IPL_2019_RUNS_FILE_PATH);
            String sortedIPLData=cricketLeagueAnalyser.getSortedByAverageWithStrikingRate();
            IPLRunSheetCSV[] iplRunSheetCSV = new Gson().fromJson(sortedIPLData, IPLRunSheetCSV[].class);
            Assert.assertEquals("Andre Russell", iplRunSheetCSV[iplRunSheetCSV.length-1].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019MostRunsCSVFile_ShouldReturnPlayerName_WhoHadLowAverageWithStrikingRate() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSVFile(IPL_2019_RUNS_FILE_PATH);
            String sortedIPLData=cricketLeagueAnalyser.getSortedByAverageWithStrikingRate();
            IPLRunSheetCSV[] iplRunSheetCSV = new Gson().fromJson(sortedIPLData, IPLRunSheetCSV[].class);
            Assert.assertEquals("Ishant Sharma", iplRunSheetCSV[0].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019MostRunsCSVFile_ShouldReturnPlayerName_WhoHitMaximumRunsWithBestAverages() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSVFile(IPL_2019_RUNS_FILE_PATH);
            String sortedIPLData=cricketLeagueAnalyser.getSortedByRunsWithAverages();
            IPLRunSheetCSV[] iplRunSheetCSV = new Gson().fromJson(sortedIPLData, IPLRunSheetCSV[].class);
            Assert.assertEquals("David Warner", iplRunSheetCSV[iplRunSheetCSV.length-1].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019MostRunsCSVFile_ShouldReturnPlayerName_WhoHitMinimumRunsWithAverages() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSVFile(IPL_2019_RUNS_FILE_PATH);
            String sortedIPLData=cricketLeagueAnalyser.getSortedByRunsWithAverages();
            IPLRunSheetCSV[] iplRunSheetCSV = new Gson().fromJson(sortedIPLData, IPLRunSheetCSV[].class);
            Assert.assertEquals("Ishant Sharma", iplRunSheetCSV[0].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void  givenIpl2019MostWicketsCSVFile_ReturnsCorrectRecords() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            int numberOfRecords = cricketLeagueAnalyser.loadIPLWicketsCSVFile(IPL_2019_WICKETS_FILE_PATH);
            Assert.assertEquals(99,numberOfRecords);
        } catch (CricketLeagueAnalyserException e) { }
    }

    @Test
    public void givenIpl2019MostWicketsCSVFile_WithWrongFile_ShouldThrowException() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CricketLeagueAnalyserException.class);
            cricketLeagueAnalyser.loadIPLWicketsCSVFile(WRONG_CSV_FILE_PATH);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.CSV_FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenIpl2019MostWicketsCSVFile_WithTypeIncorrect_ShouldThrowException() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CricketLeagueAnalyserException.class);
            cricketLeagueAnalyser.loadIPLWicketsCSVFile(INCORRECT_FILE_TYPE_PATH);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.CSV_FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenIpl2019MostWicketsCSVFile_WithIncorrectDelimeter_ShouldThrowException() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLWicketsCSVFile(IPL_2019_WICKETS_FILE_PATH);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.CSV_FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenIpl2019MostWicketsCSVFile_WithIncorrectHeader_ShouldThrowException() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLWicketsCSVFile(IPL_2019_WICKETS_FILE_PATH);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.CSV_FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenIpl2019MostWicketsCSVFile_ShouldReturnPlayerName_WhoHasTopBowlingAverage() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLWicketsCSVFile(IPL_2019_WICKETS_FILE_PATH);
            String sortedIPLData=cricketLeagueAnalyser.getSortedByBowlingAverage();
            IPLWicketSheetCSV[] iplWicketSheetCSV = new Gson().fromJson(sortedIPLData, IPLWicketSheetCSV[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplWicketSheetCSV[iplWicketSheetCSV.length-1].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIpl2019MostWicketsCSVFile_ShouldReturnPlayerName_WhoHasLowBowlingAverage() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLWicketsCSVFile(IPL_2019_WICKETS_FILE_PATH);
            String sortedIPLData=cricketLeagueAnalyser.getSortedByBowlingAverage();
            IPLWicketSheetCSV[] iplWicketSheetCSV = new Gson().fromJson(sortedIPLData, IPLWicketSheetCSV[].class);
            Assert.assertEquals("Suresh Raina", iplWicketSheetCSV[0].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIpl2019MostWicketsCSVFile_ShouldReturnPlayerName_WhoHasTopBowlingStrikeRate() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLWicketsCSVFile(IPL_2019_WICKETS_FILE_PATH);
            String sortedIPLData=cricketLeagueAnalyser.getSortedByBowlingStrikeRate();
            IPLWicketSheetCSV[] iplWicketSheetCSV = new Gson().fromJson(sortedIPLData, IPLWicketSheetCSV[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplWicketSheetCSV[iplWicketSheetCSV.length-1].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIpl2019MostWicketsCSVFile_ShouldReturnPlayerName_WhoHasLowBowlingStrikeRate() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLWicketsCSVFile(IPL_2019_WICKETS_FILE_PATH);
            String sortedIPLData=cricketLeagueAnalyser.getSortedByBowlingStrikeRate();
            IPLWicketSheetCSV[] iplWicketSheetCSV = new Gson().fromJson(sortedIPLData, IPLWicketSheetCSV[].class);
            Assert.assertEquals("Suresh Raina", iplWicketSheetCSV[0].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIpl2019MostWicketsCSVFile_ShouldReturnBowlerName_WhoHasBestEconomyRate() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLWicketsCSVFile(IPL_2019_WICKETS_FILE_PATH);
            String sortedIPLData=cricketLeagueAnalyser.getSortedByEconomyRate();
            IPLWicketSheetCSV[] iplWicketSheetCSV = new Gson().fromJson(sortedIPLData, IPLWicketSheetCSV[].class);
            Assert.assertEquals("Ben Cutting", iplWicketSheetCSV[iplWicketSheetCSV.length-1].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIpl2019MostWicketsCSVFile_ShouldReturnBowlerName_WhoHasLowEconomyRate() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLWicketsCSVFile(IPL_2019_WICKETS_FILE_PATH);
            String sortedIPLData=cricketLeagueAnalyser.getSortedByEconomyRate();
            IPLWicketSheetCSV[] iplWicketSheetCSV = new Gson().fromJson(sortedIPLData, IPLWicketSheetCSV[].class);
            Assert.assertEquals("Shivam Dube", iplWicketSheetCSV[0].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIpl2019MostWicketsCSVFile_ShouldReturnBowlerName_WhoHasBestStrikingRateWith5wAnd4w() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLWicketsCSVFile(IPL_2019_WICKETS_FILE_PATH);
            String sortedIPLData=cricketLeagueAnalyser.getSortedByStrikingRateWith5wand4w();
            IPLWicketSheetCSV[] iplWicketSheetCSV = new Gson().fromJson(sortedIPLData, IPLWicketSheetCSV[].class);
            Assert.assertEquals("Mayank Markande", iplWicketSheetCSV[iplWicketSheetCSV.length-1].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIpl2019MostWicketsCSVFile_ShouldReturnBowlerName_WhoHaslowStrikingRateWith5wAnd4w() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLWicketsCSVFile(IPL_2019_WICKETS_FILE_PATH);
            String sortedIPLData=cricketLeagueAnalyser.getSortedByStrikingRateWith5wand4w();
            IPLWicketSheetCSV[] iplWicketSheetCSV = new Gson().fromJson(sortedIPLData, IPLWicketSheetCSV[].class);
            Assert.assertEquals("Umesh Yadav", iplWicketSheetCSV[0].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }
}
