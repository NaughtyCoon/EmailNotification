package org.example;

public abstract class Senders {

    public Senders() {
        validate();
    }

    public abstract void sendMessage(String message);

    public abstract String getStatus();

    public abstract void validate();

}
