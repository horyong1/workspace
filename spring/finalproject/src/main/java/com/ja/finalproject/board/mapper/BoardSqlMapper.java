package com.ja.finalproject.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ja.finalproject.dto.ArticleImageDto;
import com.ja.finalproject.dto.CommentDto;
import com.ja.finalproject.dto.MailAuthDto;
import com.ja.finalproject.dto.PostLikeDto;
import com.ja.finalproject.dto.articleDto;

@Mapper
public interface BoardSqlMapper {
    void createBoard(articleDto articleDto);
    List<articleDto> findAll(
        @Param("searchType")String searchType,
        @Param("searchWord")String searchWord,
        @Param("pageIndex")int pageIndex);

    public int getTotalArticleCount(
        @Param("searchType")String searchType,
        @Param("searchWord")String searchWord
    );

    articleDto selectFindByNoContent(int id);
    void increaseReadCount(int id);
    void deleteById(int id);
    void update(articleDto articleDto);

    // 이미지 
    void createArticleImage(ArticleImageDto articleImageDto);
    List<ArticleImageDto> findImageByArticleId(int articleId);

      // 좋아요 
    void createLike(PostLikeDto postLikeDto);
    void deleteLike(PostLikeDto postLikeDto);
    int getTotalLikeCount(int article_id);
    int getMyLikeCount(PostLikeDto postLikeDto);

    // 댓글
    void createComment(CommentDto commentDto);
    void deleteCommentById(int id);
    void updateCommentById(CommentDto commentDto);
    List<CommentDto> selectCommentByArticleId(int article_id);
}
