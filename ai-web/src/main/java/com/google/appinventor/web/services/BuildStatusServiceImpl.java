package com.google.appinventor.web.services;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class BuildStatusServiceImpl implements BuildStatusService {

    private Map<String, Integer> statuses = new ConcurrentHashMap<>();

    @Override
    public int getBuildStatus(String userId, long projectId) {
        String cacheKey = userId + projectId;
        Integer ival = statuses.get(cacheKey);
        if (ival == null) {
            return 50;
        } else {
            return ival;
        }
    }

    @Override
    public void storeBuildStatus(String userId, long projectId, int progress) {
        String cacheKey = userId + projectId;
        statuses.put(cacheKey, progress);
    }

}
