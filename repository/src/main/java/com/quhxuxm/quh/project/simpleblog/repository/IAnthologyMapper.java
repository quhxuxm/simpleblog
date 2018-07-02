package com.quhxuxm.quh.project.simpleblog.repository;
import com.quhxuxm.quh.project.simpleblog.domain.pojo.Anthology;
import com.quhxuxm.quh.project.simpleblog.domain.view.AnthologyDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAnthologyMapper {
    List<AnthologyDetail> getAuthorAnthologiesOrderByPublishDate(@Param("authorId") long authorId, @Param("start")
            int start, @Param("pageSize") int pageSize, @Param("isDesc") boolean isDesc);

    List<AnthologyDetail> getAuthorAnthologiesOrderByFollowupNumber(@Param("authorId") long authorId, @Param("start")
            int start, @Param("pageSize") int pageSize, @Param("isDesc") boolean isDesc);

    List<AnthologyDetail> getAuthorAnthologiesOrderByArticleNumber(@Param("authorId") long authorId, @Param("start")
            int start, @Param("pageSize") int pageSize, @Param("isDesc") boolean isDesc);

    List<AnthologyDetail> getAuthorAnthologiesOrderByCommentNumber(@Param("authorId") long authorId, @Param("start")
            int start, @Param("pageSize") int pageSize, @Param("isDesc") boolean isDesc);

    List<AnthologyDetail> getAuthorAnthologiesOrderByBookmarkNumber(@Param("authorId") long authorId, @Param("start")
            int start, @Param("pageSize") int pageSize, @Param("isDesc") boolean isDesc);

    List<AnthologyDetail> getAuthorAnthologiesOrderByPraiseNumber(@Param("authorId") long authorId, @Param("start")
            int start, @Param("pageSize") int pageSize, @Param("isDesc") boolean isDesc);

    List<AnthologyDetail> getAllAnthologiesOrderByPublishDate(@Param("start") int start, @Param("pageSize") int
            pageSize, @Param("isDesc") boolean isDesc);

    List<AnthologyDetail> getAllAnthologiesOrderByFollowupNumber(@Param("start") int start, @Param("pageSize") int
            pageSize, @Param("isDesc") boolean isDesc);

    List<AnthologyDetail> getAllAnthologiesOrderByArticleNumber(@Param("start") int start, @Param("pageSize") int
            pageSize, @Param("isDesc") boolean isDesc);

    List<AnthologyDetail> getAllAnthologiesOrderByCommentNumber(@Param("start") int start, @Param("pageSize") int
            pageSize, @Param("isDesc") boolean isDesc);

    List<AnthologyDetail> getAllAnthologiesOrderByBookmarkNumber(@Param("start") int start, @Param("pageSize") int
            pageSize, @Param("isDesc") boolean isDesc);

    List<AnthologyDetail> getAllAnthologiesOrderByPraiseNumber(@Param("start") int start, @Param("pageSize") int
            pageSize, @Param("isDesc") boolean isDesc);

    Anthology getOnePojo(@Param("id") long id);

    AnthologyDetail getOneDetailView(@Param("id") long id);

    void update(Anthology anthology);

    void create(Anthology anthology);
}
