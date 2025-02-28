package ro.ps.lab3.jms;

import ro.ps.lab3.dto.mail.SendingStatus;

public interface MessageSender<Request> {

    SendingStatus sendMessage(Request request);
}