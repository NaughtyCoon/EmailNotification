package org.example;

public class Push extends Contacts {

    private final String deviceId;
    public static final String contactType = "Устройство";
    private User user;
    private boolean isUserOnline = false;

    public Push(String deviceId) {
        this.deviceId = deviceId;
        validate();
    }

    private void validate() {

        String errMsg = "Неправильный id устройства! Ожидается строка не менее 8 символов.";

        generalCheck(deviceId, errMsg);

        if (deviceId.length() < 8) {
            throw new IllegalArgumentException(errMsg);
        }

    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String getContact() {
        return deviceId;
    }

    @Override
    public String getContactType() {
        return contactType;
    }

    @Override
    public void sendMessage(String message) {

        user.setSendStatus(checkSendStatus());
        if (!canSendNow()) user.setSendStatus("Failed");

        updateHistory(user, this.getClass().getSimpleName(), deviceId, message);

    }

    @Override
    public String describe() {
        return this.getClass().getSimpleName() + ": " + deviceId;
    }

    @Override
    public boolean canSendNow() {

        isUserOnline = Math.random() > 0.5;
        // Выделено в отдельную переменную, чтобы показать имитацию определения
        // статуса пользователя: в сети или нет.

        return isUserOnline;

    }

}
