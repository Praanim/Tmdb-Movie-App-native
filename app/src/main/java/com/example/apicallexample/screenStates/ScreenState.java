package com.example.apicallexample.screenStates;

public class ScreenState<T>{

    private final Status status;
    private final T data;

    private final String message;


    public ScreenState(Status status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public Status getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }


    public enum Status{
        LOADING,
        SUCCESS,
        ERROR
    }


}
