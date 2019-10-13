package com.google.appinventor.shared.rpc.project;

public class SendEmailRequest {
    String senderId;
    String receiverId;
    String receiverEmail;
    String title;
    String body;

    public SendEmailRequest(String senderId, String receiverId, String receiverEmail, String title, String body) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.receiverEmail = receiverEmail;
        this.title = title;
        this.body = body;
    }

    public SendEmailRequest() {

    }
}
