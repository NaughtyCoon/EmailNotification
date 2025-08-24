package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс содержит образцы исходных данных для разработки и тестирования. Для запуска рабочего процесса следует
 * создать коллекцию вида List<Notifiable> и загрузить в неё коллекцию, созданную методом getUsers() настоящего
 * класса. Все модификации количества и свойств абонентов следует производить только здесь, до запуска программы.
 */
public class Users {

    /**
     * Коллекция абонентов для стартовой загрузки
     */
    static List<Notifiable> users = new ArrayList<>();

    /**
     * Метод для создания стартовой коллекции абонентов, хранилище исходных данных для разработки и тестирования.
     * @return возвращает рабочую коллекцию абонентов с загруженными первичными данными.
     */
    static List<Notifiable> getUsers() {

        users.clear();

        List<Contacts> contacts = new ArrayList<>();
        contacts.add(new Email("jenny18@mails.com"));
        contacts.add(new Sms("+1(915)555-23-87"));
        contacts.add(new Push("ZX-Spectrum"));

        users.add(new User("Jenny", contacts));
        contacts.clear();

        contacts.add(new Email("Julia@myMail.com"));
        contacts.add(new Sms("+1(922)555-11-48"));
        contacts.add(new Push("Siemens A35"));
        contacts.add(new Push("Sura-PC8000"));

        users.add(new User("Julia", contacts));
        contacts.clear();

        return users;

    }

}
