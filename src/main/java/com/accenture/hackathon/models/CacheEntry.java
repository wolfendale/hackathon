package com.accenture.hackathon.models;

import java.util.Date;

public class CacheEntry<T> {

    public CacheEntry(T data) {
        this.timestamp = new Date().getTime();
        this.data = data;
    }

    final private long timestamp;
    final private T data;

    public T getData() {
        return data;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
