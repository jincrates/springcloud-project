package me.jincrates.community.like.application.port;

import me.jincrates.community.like.domain.Like;

public interface LikePort {

    Like saveLike(Like like);
}
