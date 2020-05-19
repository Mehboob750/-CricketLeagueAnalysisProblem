package com.cricket;

public class CricketLeagueAnalyserException extends Exception {
    public enum ExceptionType {
        CSV_FILE_PROBLEM;
        public static final ExceptionType UNABLE_TO_PARSE =null ;
        public static final ExceptionType DATA_NOT_FOUND =null ;
    }

     ExceptionType type;

    public CricketLeagueAnalyserException(String message,ExceptionType type) {
        super(message);
        this.type=type;
    }

}
