package com.google.appinventor.shared.rpc.project;

public class PagedRequest {
    int start;
    int count;

    public PagedRequest(int start, int count) {
        this.start = start;
        this.count = count;
    }

    public PagedRequest() {
    }
}
