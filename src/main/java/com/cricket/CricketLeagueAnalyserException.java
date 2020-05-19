package com.cricket;

public class CricketLeagueAnalyserException extends Exception {
    public enum ExceptionType {
       DATA_NOT_FOUND;
        public static final String CSV_FILE_PROBLEM = null;
    }

    public ExceptionType type;

    public CricketLeagueAnalyserException(ExceptionType type,String message) {
        super(message);
        this.type=type;
    }

    public CricketLeagueAnalyserException(String message,String name) {
        super(message);
        this.type=ExceptionType.valueOf(name);
    }
}
