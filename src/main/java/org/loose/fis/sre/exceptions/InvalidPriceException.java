package org.loose.fis.sre.exceptions;



public class InvalidPriceException extends Exception{

    public InvalidPriceException() {
        super(String.format("The price is invalid!"));
    }
}
