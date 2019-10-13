package com.google.appinventor.shared.rpc.project;

public class StoreModerationActionRequest {
    long reportId;
    long emailId;
    String moderatorId;
    int actionType;
    String moderatorName;
    String emailPreview;

    public StoreModerationActionRequest(long reportId, long emailId, String moderatorId, int actionType, String moderatorName, String emailPreview) {
        this.reportId = reportId;
        this.emailId = emailId;
        this.moderatorId = moderatorId;
        this.actionType = actionType;
        this.moderatorName = moderatorName;
        this.emailPreview = emailPreview;
    }

    public StoreModerationActionRequest() {

    }
}
