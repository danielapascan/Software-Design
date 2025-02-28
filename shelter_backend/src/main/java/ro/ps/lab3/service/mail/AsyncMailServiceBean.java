package ro.ps.lab3.service.mail;

import com.fasterxml.jackson.databind.ObjectMapper;
import ro.ps.lab3.dto.mail.MailRequestDTO;
import ro.ps.lab3.dto.mail.MailResponseDTO;
import ro.ps.lab3.dto.mail.SendingStatus;
import ro.ps.lab3.jms.JmsMessageSenderBase;
import ro.ps.lab3.util.MailUtils;
import org.springframework.jms.core.JmsTemplate;

public class AsyncMailServiceBean extends JmsMessageSenderBase<MailRequestDTO> implements MailService {

    public AsyncMailServiceBean(String destination, JmsTemplate jmsTemplate, ObjectMapper objectMapper) {
        super(destination, jmsTemplate, objectMapper);
    }

    @Override
    public MailResponseDTO sendMail(MailRequestDTO mailRequestDTO) {
        SendingStatus status = sendMessage(mailRequestDTO);

        return MailUtils.getMailResponseDTO(mailRequestDTO, status);
    }
}
