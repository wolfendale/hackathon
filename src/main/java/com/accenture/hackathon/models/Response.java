package com.accenture.hackathon.models;

public class Response<T> {

    public Response(T data) {
        this.data = data;
        this.error = null;
    }

    public Response(Exception error) {
        this.error = new ErrorModel(error.getMessage());
    }

    public boolean failed() {
        return this.error != null;
    }

    public T getData() {
        return data;
    }

    private ErrorModel error;
    private T data;
}
