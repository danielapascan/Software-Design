package ro.ps.lab3.util;

import lombok.experimental.UtilityClass;
import ro.ps.lab3.dto.mail.MailRequestDTO;
import ro.ps.lab3.dto.mail.MailResponseDTO;
import ro.ps.lab3.dto.mail.SendingStatus;

@UtilityClass
public class MailUtils {

    public MailResponseDTO getMailResponseDTO(MailRequestDTO mailRequestDTO, SendingStatus status) {
        return MailResponseDTO.builder()
                .from(mailRequestDTO.getFrom())
                .to(mailRequestDTO.getTo())
                .status(status)
                .build();
    }
}
