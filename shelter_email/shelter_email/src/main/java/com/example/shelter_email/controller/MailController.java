package com.example.shelter_email.controller;

import com.example.shelter_email.dto.MailRequestDTO;
import com.example.shelter_email.dto.MailResponseDTO;
import com.example.shelter_email.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/mail/v1")
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    @PostMapping("sync")
    public ResponseEntity<MailResponseDTO> sendSyncMail(@RequestBody MailRequestDTO mailRequestDTO) {
        return new ResponseEntity<>(
                mailService.sendMail(mailRequestDTO),
                HttpStatus.OK
        );
    }
}