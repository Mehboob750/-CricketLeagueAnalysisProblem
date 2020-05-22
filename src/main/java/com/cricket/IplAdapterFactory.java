package com.cricket;

import java.util.Map;

public class IplAdapterFactory {

    public Map<String, IplDAO> loadIplData(CricketLeagueAnalyser.Cricket cricket, String... csvFilePath) throws CricketLeagueAnalyserException {
        if (cricket.equals(CricketLeagueAnalyser.Cricket.Batting))
            return new BattingAdapter().loadIplData(csvFilePath);
        else if (cricket.equals(CricketLeagueAnalyser.Cricket.Bowling))
            return new BowlingAdapter().loadIplData(csvFilePath);
        throw new CricketLeagueAnalyserException(CricketLeagueAnalyserException.ExceptionType.INVALID_FIELD, "Invalid Field");
    }
}
