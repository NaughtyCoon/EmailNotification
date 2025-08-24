package org.example;

public class Email extends Contacts {

    private final String address;
    private final String contactType = "E-mail";
    private User user;

    public Email(String address) {
        this.address = address;
        validate();
    }

    private void validate() {

        String errMsg = "Некорректный адрес электронной почты! Должен содержать 1 символ \"@\".";

        generalCheck(address, errMsg);

        char target = '@';
        int count = 0;

        for (int i = 0; i < address.length(); i++) {
            if (address.charAt(i) == target) {
                count++;
            }
        }

        if (count != 1) {
            throw new IllegalArgumentException(errMsg);
        }

    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String getContact() {
        return address;
    }

    @Override
    public String getContactType() {
        return contactType;
    }

    @Override
    public void sendMessage(String message) {

        user.setSendStatus(checkSendStatus());
        if (!canSendNow()) user.setSendStatus("Failed");

        updateHistory(user, this.getClass().getSimpleName(), address, message);

    }

    @Override
    public String describe() {
        return this.getClass().getSimpleName() + ": " + address;
    }

    @Override
    public boolean canSendNow() {
        return true;
    }

}
