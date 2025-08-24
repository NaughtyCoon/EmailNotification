package org.example;

import java.time.LocalDateTime;

public class Sms extends Contacts {

    private String mobile;
    public static final String contactType = "Сотовый";
    private User user;

    public Sms(String mobile) {
        this.mobile = mobile;
        validate();
    }

    private void validate() {

        String errMsg = "Неправильный номер телефона! Ожидается строка, содержащая " +
                "от 10 до 15 цифровых символов.";

        generalCheck(mobile, errMsg);

        mobile = mobile.replaceAll("[^0-9]", "");

        if (mobile.length() < 10 || mobile.length() > 15) {
            throw new IllegalArgumentException(errMsg);
        }

    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String getContact() {
        return mobile;
    }

    @Override
    public String getContactType() {
        return contactType;
    }

    @Override
    public void sendMessage(String message) {

        user.setSendStatus(checkSendStatus());
        if (!canSendNow()) user.setSendStatus("Failed");

        updateHistory(user, this.getClass().getSimpleName(), mobile, message);

    }

    @Override
    public String describe() {
        return this.getClass().getSimpleName() + ": " + mobile;
    }

    @Override
    public boolean canSendNow() {

        int currentHour = LocalDateTime.now().getHour();

        return (currentHour > 7 && currentHour < 22);

    }

}
