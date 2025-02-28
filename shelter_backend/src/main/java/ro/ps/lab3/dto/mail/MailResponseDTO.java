package ro.ps.lab3.dto.mail;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MailResponseDTO {

    private String from;
    private String to;
    private SendingStatus status;
}
