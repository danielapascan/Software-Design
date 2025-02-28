package ro.ps.lab3.dto.mail;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MailRequestDTO {

    private String from;
    private String to;
    private String subject;
    private String body;
}