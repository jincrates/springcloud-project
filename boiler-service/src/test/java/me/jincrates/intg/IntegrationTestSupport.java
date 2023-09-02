package me.jincrates.intg;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
public abstract class IntegrationTestSupport {

//    @MockBean
//    protected MailSendClient mailSendClient;
}
