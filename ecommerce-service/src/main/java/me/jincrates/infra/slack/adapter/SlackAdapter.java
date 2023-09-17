package me.jincrates.infra.slack.adapter;

import lombok.extern.slf4j.Slf4j;
import me.jincrates.infra.slack.application.SlackPort;
import org.springframework.stereotype.Component;

@Slf4j
@Component
class SlackAdapter implements SlackPort {

    @Override
    public void send() {
        log.info("Slack Send Message");
    }
}
