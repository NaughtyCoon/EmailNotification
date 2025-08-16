package org.example;

public class Sms extends Senders {

    private String mobile;

    public Sms(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

    @Override
    public void sendMessage(String message) {

    }

    @Override
    public String getStatus() {
        return "";
    }

    @Override
    public void validate() {

    }

}
