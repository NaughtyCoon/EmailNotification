package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Contacts {

    /**
     * Метод предоставляет объекту дочернего класса класса Contact ссылку на объект класса User -
     * собственника коллекции контактов - для обеспечения возможности модификации его полей.
     * @param user ссылка на объект класса User, хозяина коллекции контактов.
     */
    public abstract void setUser(User user);

    /**
     * Метод возвращает идентификатор контакта, например, адрес для электронной почты или номер телефона для sms.
     * @return возвращает String, идентификатор контакта.
     */
    public abstract String getContact();

    /**
     * Метод возвращает физический тип контакта, как-то "сотовый" для уведомления по SMS, "устройство" для
     * уведомления типа push, etc.
     * @return возвращает String, тип контакта.
     */
    public abstract String getContactType();

    /**
     * Метод отправляет сообщение пользователю соответствующим контакту образом.
     * @param message сообщение, переменная типа String.
     */
    public abstract void sendMessage(String message);

    /**
     * Метод имитирует успешность/неуспешность доставки сообщения абоненту.
     * @return String "sent" в случае успешной доставки, и String "failed" в случае сбоя.
     */
    public String checkSendStatus() {
        return Math.random() > 0.1 ? "sent" : "failed";
    }

    /**
     * Генерируется реквизит контакта.
     * @return возвращает переменную String, содержащую класс контакта и его идентификатор, например:
     * "Email: somebody@anyserver.mail"
     */
    public abstract String describe();

    /**
     * Проверяется возможность отправки сообщения абоненту в текущий момент времени.
     * @return возвращает true, если отправка разрешена, и false, если нет.
     */
    public abstract boolean canSendNow();

    /**
     * Запрос текущего времени и даты.
     * @return возвращает текущее время и дату в виде переменной String в формате "yyyy-MM-dd HH:mm".
     */
    public String getTimeStamp() {

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        return now.format(formatter);

    }

    /**
     * Метод осуществляет часть валидации дочерних классов, одинаковую для них всех.
     * @param contactId идентификационный код контакта (номер, адрес, ID устройства и т.п.).
     * @param errMsg текст сообщения для вывода в случае ошибки.
     */
    public void generalCheck(String contactId, String errMsg) {
        if (contactId == null || contactId.isEmpty()) throw new IllegalArgumentException(errMsg);
    }

    /**
     * Метод формирует запись в истории сообщений в едином формате для всех способов отправки.
     * @param user        ссылка на объект User, с которым связан объект контакта, вызывающий данный метод.
     * @param contactType String тип контакта, как то: E-mail, сотовый, устройство с приложением-клиентом и т.п.
     * @param message     String текст сообщения.
     */
    public void updateHistory(User user, String contactType, String contactId, String message) {

        StringBuilder str = new StringBuilder();

        user.setHistory(str
                .append(user.getHistory())
                .append(describe())
                .append(" ".repeat(27 - contactType.length() - contactId.length()))
                .append("- [")
                .append(getTimeStamp())
                .append("] ")
                .toString()
        );
        user.setHistory(str
                .append(user.getSendStatus())
                .append(user.getSendStatus().equals("sent") ? ":   \"" : ": \"")
                .append(message)
                .append("\"\n")
                .toString()
        );

    }

}
