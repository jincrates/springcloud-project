package me.jincrates.msa.coffeekiosk.spring.temp.api.service.mail;

import lombok.RequiredArgsConstructor;
import me.jincrates.msa.coffeekiosk.spring.infra.mail.MailSendClient;
import me.jincrates.msa.coffeekiosk.spring.temp.domain.history.mail.MailSendHistory;
import me.jincrates.msa.coffeekiosk.spring.temp.domain.history.mail.MailSendHistoryRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MailService {

    private final MailSendClient mailSendClient;
    private final MailSendHistoryRepository mailSendHistoryRepository;

    public boolean sendMail(String fromEmail, String toEmail, String subject, String content) {

        boolean result = mailSendClient.sendEmail(fromEmail, toEmail, subject, content);

        if (result) {
            mailSendHistoryRepository.save(
                    MailSendHistory.builder()
                            .fromEmail(fromEmail)
                            .toEmail(toEmail)
                            .subject(subject)
                            .content(content)
                            .build()
            );

            return true;
        }

        return false;
    }
}
