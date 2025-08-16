package org.example;

public class Email extends Senders {

private String address;

    public Email(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
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
