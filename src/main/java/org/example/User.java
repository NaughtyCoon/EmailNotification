package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * На основе данного класса создаются объекты абонентов, т.е. структуры данных, ассоциированных с конкретным
 * адресатом уведомлений (имя, номер телефона, электронный адрес, история сообщений etc). Абонентов может быть много,
 * для их обработки они должны объединяться в коллекцию типа List<Notifiable>.
 */
public class User implements Notifiable {

    // Тексты исключений сведены в один фрагмент кода ниже, чтобы можно было централизованно исправить.
    static final String historyIsEmpty = "Для данного пользователя отправок ещё не было.";
    static final String errNoUserName = "Абонент не должен быть анонимным!";
    static final String errNoContactRegistered = "Для абонента должен быть указан хотя бы один контакт!";
    static final String errInvalidSendStatus = "Статус отправки может принимать только значения \"sent\" и \"failed\"";
    static final String errInvalidMessage = "Сообщение должно содержать текст.";

    /**
     * Имя абонента
     */
    private final String name;

    /**
     * История уведомлений абонента в виде бесконечно нарастающей переменной типа String. В Историю последовательно
     * вносятся отправки всех типов в едином формате.
     */
    private String history = "";

    /**
     * Статус крайней отправки - sent, если удачно; failed - неудачно, null - изначально
     */
    private String sendStatus;

    // В этой коллекции соберём все контакты конкретного абонента:
    private List<Contacts> contacts;

    /**
     * Конструктор класса User. Стартовая загрузка полей класса и проверка корректности их содержимого.
     * @param name имя абонента
     * @param contacts коллекция контактов абонента
     */
    public User(String name, List<Contacts> contacts) {

        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException(errNoUserName);
        } else {
            this.name = name;
        }

        if (contacts == null || contacts.isEmpty()) throw new IllegalArgumentException(errNoContactRegistered);

        // Контакты абонента, полученные при его создании, загрузим в эту коллекцию:
        this.contacts = new ArrayList<>(contacts);

        // Закрепление объектов коллекции контактов за данным абонентом, чтобы из них было возможным
        // обращение к его свойствам, общим для всей коллекции (онлайн-статус, история сообщений и т.п.)
        for (Contacts e : contacts) {
            e.setUser(this);
        }

    }

    /**
     * Запрос истории отправок абоненту.
     * @return возвращает переменную типа String, содержащую всю обобщённую историю отправок абоненту на все
     * его контакты.
     */
    public String getHistory() {
        return history;
    }

    /**
     * Метод для формирования истории сообщений абоненту.
     * @param history строка, содержащая историю.
     */
    @Override
    public void setHistory(String history) {
        this.history = history;
    }

    @Override
    public String getSendStatus() {

        if (history.isEmpty()) return null;

        return sendStatus;

    }

    @Override
    public void setSendStatus(String sendStatus) {

        if (sendStatus == null || sendStatus.isEmpty()) {
            throw new IllegalArgumentException(errInvalidSendStatus);
        }

        sendStatus = sendStatus.toLowerCase();
        if (sendStatus.equals("sent") || sendStatus.equals("failed")) {
            this.sendStatus = sendStatus;
        } else {
            throw new IllegalArgumentException(errInvalidSendStatus);
        }

    }

    @Override
    public void sendMessage(String message) {

        if (message == null || message.isEmpty()) {
            throw new IllegalArgumentException(errInvalidMessage);
        }

        System.out.println("Отправляем сообщение для абонента " + name + ":");
        System.out.println();

        contacts.forEach(e->{
            System.out.print("\t" + e.describe() + " - ");
            e.sendMessage(message);
            System.out.println(getSendStatus());
        });
        System.out.println();

    }

    @Override
    public String getName(){
        return name;
    }

    /**
     * Метод определяет формат вывода описания объекта User (Абонент).
     * @return возвращает String, в которой отформатированы и сведены в единое
     * описание свойства объекта User такие, как имя абонента и все способы связи с ним.
     */
    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder("\nАбонент: " + name + "\n\n");

        contacts.forEach(e -> builder.append("\t")
                .append(e.getContactType())
                .append(": ")
                .append(".".repeat(20 - e.getContactType().length()))
                .append(" ")
                .append(e.getContact())
                .append("\n"));

        return builder.toString();

    }

}
