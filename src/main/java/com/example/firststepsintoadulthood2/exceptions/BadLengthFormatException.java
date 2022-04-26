package com.example.firststepsintoadulthood2.exceptions;

public class BadLengthFormatException extends  Exception{

    public BadLengthFormatException(String field, int MAX_LENGTH, int MIN_LENGTH) {

        super("The " + field + " field must contain between " + MIN_LENGTH + " and " + MAX_LENGTH + " characters.");



    }
}
