package org.example;

public class User {

    private String name;
    private Email email;
    private Sms sms;
    private Push push;

    public User(String name, String emailAddress, String mobileNum, String deviceId) {
        this.name = name;
        this.email = new Email(emailAddress);
        this.sms = new Sms(mobileNum);
        this.push = new Push(deviceId);
    }

    @Override
    public String toString() {

        return "\nАбонент:\t\t" + name +
                "\nE-mail:\t\t" + email.getAddress() +
                "\nСот.тел:\t\t" + sms.getMobile() +
                "\nУстройство:\t\t" + push.getDeviceId();

    }

}
