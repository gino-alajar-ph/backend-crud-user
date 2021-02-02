package com.gla.demo.core.exception;

public class MissingRequiredArgException  extends RuntimeException{

    private static final long serialVersionUID = -467638851161244306L;

    public MissingRequiredArgException(String message){
        super(message);
    }
}
