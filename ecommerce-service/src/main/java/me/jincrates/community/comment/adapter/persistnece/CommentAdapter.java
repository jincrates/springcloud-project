package me.jincrates.community.comment.adapter.persistnece;

import lombok.RequiredArgsConstructor;
import me.jincrates.community.comment.application.port.CommentPort;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentAdapter implements CommentPort {

    private final CommentRepository commentRepository;

}
