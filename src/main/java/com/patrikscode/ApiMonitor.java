package com.patrikscode;

import java.util.ArrayList;

public class ApiMonitor {

    public String validateStatusCode(int code) {
        if (code == 200) return "Success";
        if (code == 201) return "Created";
        if (code == 204) return "No Content";
        if (code == 400) return "Bad Request";
        if (code == 401) return "Unauthorized";
        if (code == 403) return "Forbidden";
        if (code == 404) return "Not Found";
        if (code == 500) return "Internal Server Error";
        return "Unknown";
    }

    ArrayList<String> endpoints = new ArrayList<>();
    ArrayList<Long> times = new ArrayList<>();

    public void addResponseTime(String endpoint, long ms) {
        endpoints.add(endpoint);
        times.add(ms);
    }

    public double getAverage() {
        long total = 0;
        for (long t : times) total += t;
        return (double) total / times.size();
    }

    public String getFastest() {
        int index = 0;
        for (int i = 1; i < times.size(); i++) {
            if (times.get(i) < times.get(index)) index = i;
        }
        return endpoints.get(index) + " (" + times.get(index) + "ms)";
    }

    public String getSlowest() {
        int index = 0;
        for (int i = 1; i < times.size(); i++) {
            if (times.get(i) > times.get(index)) index = i;
        }
        return endpoints.get(index) + " (" + times.get(index) + "ms)";
    }

}