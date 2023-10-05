package me.jincrates.community.comment.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.jincrates.community.comment.application.port.CommentPort;
import me.jincrates.community.comment.application.port.CommentUseCase;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService implements CommentUseCase {

    private final CommentPort commentPort;
}
