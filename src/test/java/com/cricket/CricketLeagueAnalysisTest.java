package com.cricket;

import org.junit.Assert;
import org.junit.Test;

public class CricketLeagueAnalysisTest {
    public final String IPL_2019_RUNS_FILE_PATH="./src/test/resources/IPL2019FactsheetMostRuns.csv";

    @Test
    public void  givenIpl2019MostRunsCSVFile_ReturnsCorrectRecords() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            int numberOfRecords = cricketLeagueAnalyser.loadIPLCSVFile(IPL_2019_RUNS_FILE_PATH);
            Assert.assertEquals(100,numberOfRecords);
        } catch (CricketLeagueAnalyserException e) { }
    }
    
}
