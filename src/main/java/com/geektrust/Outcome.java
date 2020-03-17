package com.geektrust;

public enum Outcome {
    WIN("WINS"),
    LOSS("LOSES");

    private final String message;
    Outcome(String message){
        this.message = message;
    }

    String value(){
        return message;
    }
}
