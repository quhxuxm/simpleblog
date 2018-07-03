package com.quhxuxm.quh.project.simpleblog.repository.view;

import com.quhxuxm.quh.project.simpleblog.domain.view.AnthologyDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface IAnthologyViewMapper {
    List<AnthologyDetail> findDetailsByAuthorAndTagsOrderByPublishDate(
            @Param("authorId")
                    long authorId,
            @Param("tags")
                    Set<String> tags,
            @Param("start")
                    int start,
            @Param("pageSize")
                    int pageSize,
            @Param("isDesc")
                    boolean isDesc);

    List<AnthologyDetail> findDetailsByAuthorAndTagsOrderByFollowupNumber(
            @Param("authorId")
                    long authorId,
            @Param("tags")
                    Set<String> tags,
            @Param("start")
                    int start,
            @Param("pageSize")
                    int pageSize,
            @Param("isDesc")
                    boolean isDesc);

    List<AnthologyDetail> findDetailsByAuthorAndTagsOrderByArticleNumber(
            @Param("authorId")
                    long authorId,
            @Param("tags")
                    Set<String> tags,
            @Param("start")
                    int start,
            @Param("pageSize")
                    int pageSize,
            @Param("isDesc")
                    boolean isDesc);

    List<AnthologyDetail> findDetailsByAuthorAndTagsOrderByCommentNumber(
            @Param("authorId")
                    long authorId,
            @Param("tags")
                    Set<String> tags,
            @Param("start")
                    int start,
            @Param("pageSize")
                    int pageSize,
            @Param("isDesc")
                    boolean isDesc);

    List<AnthologyDetail> findDetailsByAuthorAndTagsOrderByBookmarkNumber(
            @Param("authorId")
                    long authorId,
            @Param("tags")
                    Set<String> tags,
            @Param("start")
                    int start,
            @Param("pageSize")
                    int pageSize,
            @Param("isDesc")
                    boolean isDesc);

    List<AnthologyDetail> findDetailsByAuthorAndTagsOrderByPraiseNumber(
            @Param("authorId")
                    long authorId,
            @Param("tags")
                    Set<String> tags,
            @Param("start")
                    int start,
            @Param("pageSize")
                    int pageSize,
            @Param("isDesc")
                    boolean isDesc);

    List<AnthologyDetail> findDetailsByTagsOrderByPublishDate(
            @Param("start")
                    int start,
            @Param("tags")
                    Set<String> tags,
            @Param("pageSize")
                    int pageSize,
            @Param("isDesc")
                    boolean isDesc);

    List<AnthologyDetail> findDetailsByTagsOrderByFollowupNumber(
            @Param("start")
                    int start,
            @Param("tags")
                    Set<String> tags,
            @Param("pageSize")
                    int pageSize,
            @Param("isDesc")
                    boolean isDesc);

    List<AnthologyDetail> findDetailsByTagsOrderByArticleNumber(
            @Param("start")
                    int start,
            @Param("tags")
                    Set<String> tags,
            @Param("pageSize")
                    int pageSize,
            @Param("isDesc")
                    boolean isDesc);

    List<AnthologyDetail> findDetailsByTagsOrderByCommentNumber(
            @Param("start")
                    int start,
            @Param("tags")
                    Set<String> tags,
            @Param("pageSize")
                    int pageSize,
            @Param("isDesc")
                    boolean isDesc);

    List<AnthologyDetail> findDetailsByTagsOrderByBookmarkNumber(
            @Param("start")
                    int start,
            @Param("tags")
                    Set<String> tags,
            @Param("pageSize")
                    int pageSize,
            @Param("isDesc")
                    boolean isDesc);

    List<AnthologyDetail> findDetailsByTagsOrderByPraiseNumber(
            @Param("start")
                    int start,
            @Param("tags")
                    Set<String> tags,
            @Param("pageSize")
                    int pageSize,
            @Param("isDesc")
                    boolean isDesc);

    AnthologyDetail findOneDetailById(
            @Param("id")
                    long id);
}
