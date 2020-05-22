package com.cricket;

import java.util.Map;

public class BowlingAdapter extends IplAdapter {
    @Override
    public Map<String, IplDAO> loadIplData(String... csvFilePath)throws CricketLeagueAnalyserException {
        return super.loadIplData(IPLWicketSheetCSV.class,csvFilePath[0]);
    }
}
