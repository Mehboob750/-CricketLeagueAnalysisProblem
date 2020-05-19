package com.cricket;

public class CricketLeagueAnalyserException extends Exception {
    public enum ExceptionType {
        CSV_FILE_PROBLEM;
    }

     ExceptionType type;

    public CricketLeagueAnalyserException(String message,ExceptionType type) {
        super(message);
        this.type=type;
    }

}
