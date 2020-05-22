package com.cricket;

import java.util.Map;

public class BattingAdapter extends IplAdapter {
    @Override
    public Map<String, IplDAO> loadIplData(String... csvFilePath)throws CricketLeagueAnalyserException {
        return super.loadIplData(IPLRunSheetCSV.class,csvFilePath[0]);
    }
}
