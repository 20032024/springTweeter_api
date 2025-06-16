package com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.security.services.Interfaces;

import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.PostreComment;
import java.util.List;

public interface IPostreCommentService {
    PostreComment createPostreComment(PostreComment postreComment);
    List<PostreComment> getCommentsByPostre(Long postreId);
    void deletePostreComment(Long id);
}