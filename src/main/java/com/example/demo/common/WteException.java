package com.example.demo.common;

public class WteException extends RuntimeException{

    public WteException() {
    }

    public WteException(String message) {
        super(message);
    }

    /**
     * 抛出异常
     * @param message
     */
    public static void fail(String message) {
        throw new WteException(message);
    }
}
