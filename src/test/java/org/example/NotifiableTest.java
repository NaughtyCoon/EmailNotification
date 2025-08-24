package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NotifiableTest {

    private List<Notifiable> users;

    @BeforeEach
    void beforeEach() {
        users = Users.getUsers();
    }

    @Test
    public void userConstructor_whenNameIsNullOrEmpty_thenThrowIllegalArgumentExceptionErrNoUserName() {

        List<Contacts> contacts = new ArrayList<>();
        contacts.add(new Email("jenny18@mails.com"));
        contacts.add(new Sms("+1(915)555-23-87"));
        contacts.add(new Push("ZX-Spectrum"));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Notifiable user = new User(null, contacts);
        });
        assertEquals(User.errNoUserName, exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            Notifiable user = new User("", contacts);
        });
        assertEquals(User.errNoUserName, exception.getMessage());

    }

    @Test
    public void userConstructor_whenContactListIsNullOrEmpty_thenThrowIllegalArgumentExceptionErrNoContactRegistered() {

        List<Contacts> contacts = new ArrayList<>();
        String name = "Jenny";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Notifiable user = new User(name, contacts);
        });
        assertEquals(User.errNoContactRegistered, exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            Notifiable user = new User(name, null);
        });
        assertEquals(User.errNoContactRegistered, exception.getMessage());

    }

    @Test
    void getSendStatus_whenHistoryIsEmpty_thenReturnNull() {

        users.get(0).setSendStatus("sEnt");

        assertNull(users.get(0).getSendStatus());

    }

    @Test
    void getSendStatus_whenSendStatusSetsEnt_thenReturnSendStatusValueSent() {

        users.get(0).setHistory("Текстовая заглушка, чтобы история не была пустой.\n");

        users.get(0).setSendStatus("sEnt");

        assertEquals("sent", users.get(0).getSendStatus());

    }

    @Test
    void setSendStatus_whenSendStatusIsValid_thenSetSendStatusToReceivedArgument() {

        users.get(0).setHistory("Текстовая заглушка, чтобы история не была пустой.\n");

        users.get(0).setSendStatus("SENt");

        assertEquals("sent", users.get(0).getSendStatus());

        users.get(0).setSendStatus("FaIleD");

        assertEquals("failed", users.get(0).getSendStatus());

    }

    @Test
    void setSendStatus_whenSendStatusIsInvalid_thenThrowIllegalArgumentExceptionErrInvalidSendStatus() {

        users.get(0).setHistory("Текстовая заглушка, чтобы история не была пустой.\n");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> users.get(0).setSendStatus(null));
        assertEquals(User.errInvalidSendStatus, exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> users.get(0).setSendStatus("Бяка"));
        assertEquals(User.errInvalidSendStatus, exception.getMessage());

    }

    @Test
    void getHistory_whenHistoryExists_thenReturnHistoryStringContainingMessage() {

        String message = "Давайте что-нибудь отправим!";
        users.get(0).sendMessage(message);

        String history = users.get(0).getHistory();

        assertTrue(history.contains(message));

    }

    @Test
    void getHistory_whenNoMessagesSent_thenReturnEmptyString() {

        String history = users.get(0).getHistory();

        System.out.println(history);

        assertTrue(history.isEmpty());

    }

    @Test
    void sendMessage_whenMessageIsValid_thenSendMessage() {

        String message = "Давайте что-нибудь отправим!";

        users.get(0).sendMessage(message);
        String history = users.get(0).getHistory();

        assertTrue(history.contains(message));

    }

    @Test
    void sendMessage_whenMessageIsInvalid_thenThrowIllegalArgumentExceptionErrInvalidMessage() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> users.get(0).sendMessage(null));
        assertEquals(User.errInvalidMessage, exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class,
                () -> users.get(0).sendMessage(""));
        assertEquals(User.errInvalidMessage, exception.getMessage());

    }

}
