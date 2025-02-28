package com.example.shelter_email.service;

import com.example.shelter_email.dto.MailRequestDTO;
import com.example.shelter_email.dto.MailResponseDTO;

public interface MailService {

    MailResponseDTO sendMail(MailRequestDTO mailRequestDTO);
}
