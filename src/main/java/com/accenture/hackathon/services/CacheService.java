package com.accenture.hackathon.services;

import com.accenture.hackathon.exceptions.CacheTimeoutException;
import com.accenture.hackathon.models.CacheEntry;
import com.accenture.hackathon.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import twitter4j.Status;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Scope("singleton")
public class CacheService {

    public CacheService() {
        this.timeout = 15 * 60 * 1000;
    }

    final private long timeout;

    private Map<String, CacheEntry<List<Status>>> statusCache = new HashMap<>();

    public Response<CacheEntry<List<Status>>> getStatus(String key) {
        final CacheEntry<List<Status>> entry = statusCache.get(key);
        if (entry == null || new Date().getTime() > entry.getTimestamp() + timeout) {
            return new Response<>(
                new CacheTimeoutException()
            );
        } else {
            return new Response<>(entry);
        }
    }

    public CacheService putStatus(String key, List<Status> value) {
        statusCache.put(key, new CacheEntry<>(value));
        return this;
    }
}
