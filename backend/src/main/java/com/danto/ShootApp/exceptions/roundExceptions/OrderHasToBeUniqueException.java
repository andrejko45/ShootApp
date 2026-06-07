package com.danto.ShootApp.exceptions.roundExceptions;

public class OrderHasToBeUniqueException extends RuntimeException{

    public OrderHasToBeUniqueException(String message) {
      super(message);
    }

}
