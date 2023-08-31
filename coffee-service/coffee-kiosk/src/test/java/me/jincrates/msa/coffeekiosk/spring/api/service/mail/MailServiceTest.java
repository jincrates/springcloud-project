package me.jincrates.msa.coffeekiosk.spring.api.service.mail;

import me.jincrates.msa.coffeekiosk.spring.infra.mail.MailSendClient;
import me.jincrates.msa.coffeekiosk.spring.temp.api.service.mail.MailService;
import me.jincrates.msa.coffeekiosk.spring.temp.domain.history.mail.MailSendHistory;
import me.jincrates.msa.coffeekiosk.spring.temp.domain.history.mail.MailSendHistoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MailServiceTest {

    @Mock
    private MailSendClient mailSendClient;

    @Mock
    private MailSendHistoryRepository mailSendHistoryRepository;

    @InjectMocks
    private MailService mailService;

    @Test
    @DisplayName("메일을 전송합니다.")
    void sendMail() {
        // given
        /**
         * 어노테이션으로 선언
         */
        //MailSendClient mailSendClient = mock(MailSendClient.class);
        //MailSendHistoryRepository mailSendHistoryRepository = mock(MailSendHistoryRepository.class);
        //MailService mailService = new MailService(mailSendClient, mailSendHistoryRepository);

        // stubbing: Mock object 행위를 결정
        //Mockito.when(mailSendClient.sendEmail(anyString(), anyString(), anyString(), anyString()))
        //    .thenReturn(true);

        // stubbing 자체가 BDDMockito를 사용하는 것이 자연스럽다.
        BDDMockito.given(
                        mailSendClient.sendEmail(anyString(), anyString(), anyString(), anyString()))
                .willReturn(true);

        // when
        boolean result = mailService.sendMail("", "", "", "");

        // then
        assertThat(result).isTrue();
        verify(mailSendHistoryRepository, times(1))
                .save(any(MailSendHistory.class));
    }
}