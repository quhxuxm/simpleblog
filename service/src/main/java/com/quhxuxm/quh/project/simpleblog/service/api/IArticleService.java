package com.quhxuxm.quh.project.simpleblog.service.api;

import java.util.Set;

public interface IArticleService {
    void createArticleInAnthology(String title, String content,
            Set<String> tags, Long authorId, Long anthologyId);
}
