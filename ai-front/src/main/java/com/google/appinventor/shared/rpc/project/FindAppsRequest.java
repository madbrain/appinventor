package com.google.appinventor.shared.rpc.project;

public class FindAppsRequest extends PagedRequest {
    String keywords;

    public FindAppsRequest(String keywords, int start, int count) {
        super(start, count);
        this.keywords = keywords;
    }

    public FindAppsRequest() {
        super();
    }
}
