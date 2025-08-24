package org.example;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Notifiable> users = Users.getUsers();

        users.forEach(e->{
            System.out.println(e);
            System.out.println("Статус последней отправки: " +
                    (e.getSendStatus() == null ? User.historyIsEmpty : e.getSendStatus()) + "\n");
            e.sendMessage("Hello, " + e.getName() + "!");
            System.out.println("История сообщений абоненту " + e.getName() + ":");
            System.out.println();
            System.out.println(e.getHistory());
        });

        users.forEach(e->{
            System.out.println(e);
            System.out.println("Статус последней отправки: " +
                    (e.getSendStatus() == null ? User.historyIsEmpty : e.getSendStatus()) + "\n");
            e.sendMessage("Good news, " + e.getName() + "!");
            System.out.println(e.getHistory());
        });

    }

}
