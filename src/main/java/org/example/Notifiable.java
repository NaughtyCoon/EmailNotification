package org.example;

public interface Notifiable {

    /**
     * Запрос имени абонента.
     * @return возвращает String, содержащую имя абонента.
     */
    String getName();

    /**
     * Запрос статуса отправки сообщения.
     * @return возвращает String статус отправки sent или failed.
     */
    String getSendStatus();

    /**
     * Устанавливает статус отправки сообщения, sent или failed.
     * @param sendStatus String, вариант устанавливаемого статуса - sent или failed.
     */
    void setSendStatus(String sendStatus);

    /**
     * Запрос истории отправок сообщений абоненту.
     * @return возвращает String, историю отправки сообщений конкретного абонента.
     */
    String getHistory();

    /**
     * Метод позволяет загрузить историю отправок сообщений абоненту в его объект User.
     * @param history история отправок сообщений.
     */
    void setHistory(String history);

    /**
     * Метод отправляет сообщение через выбранный контакт (объект из коллекции контактов).
     * @param message String текст сообщения.
     */
    void sendMessage(String message);

}
