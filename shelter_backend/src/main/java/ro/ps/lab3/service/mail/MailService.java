package ro.ps.lab3.service.mail;

import ro.ps.lab3.dto.mail.MailRequestDTO;
import ro.ps.lab3.dto.mail.MailResponseDTO;

public interface MailService {

    MailResponseDTO sendMail(MailRequestDTO mailRequestDTO);
}