package org.loose.fis.sre.exceptions;

public class RentIncompleteException extends Exception{

    public RentIncompleteException() {
        super(String.format("Not enough data of the destination!"));
    }

}

