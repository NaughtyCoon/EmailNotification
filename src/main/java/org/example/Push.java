package org.example;

public class Push extends Senders {

    private String deviceId;

    public Push(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceId() {
        return deviceId;
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
        if (this.deviceId.length() < 8) {
            throw new IllegalArgumentException("Неправильный id устройства! Ожидается строка не менее 8 символов.");
        }
    }

}
